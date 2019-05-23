create table users
(
    user_id serial not null
        constraint users_pk
            primary key,
    login varchar(255) not null,
    password varchar(255) not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    active integer
);

create table roles
(
    role varchar(255) not null,
    role_id serial not null
        constraint roles_pk
            primary key
);


create table user_role
(
    user_id integer not null
        constraint user_user___fk
            references users,
    role_id integer not null
        constraint user_role___fk
            references roles
);

alter table user_role
    add constraint user_role_pk
        primary key (user_id, role_id);

create table persistent_logins
(
    username varchar(64) not null,
    series varchar(64) not null
        constraint persistent_logins_pkey
            primary key,
    token varchar(64) not null,
    last_used timestamp not null
);


create function update_changetimestamp_column() returns trigger
    language plpgsql
as $$
BEGIN
    NEW.changetimestamp = now();
    RETURN NEW;
END;
$$;


create function update_modified_column() returns trigger
    language plpgsql
as $$
BEGIN
    IF row(NEW.*) IS DISTINCT FROM row(OLD.*) THEN
        NEW.last_used = now();
        RETURN NEW;
    ELSE
        RETURN OLD;
    END IF;
END;
$$;


create trigger bu_persistent_logins
    before update
    on persistent_logins
    for each row
execute procedure update_modified_column();

