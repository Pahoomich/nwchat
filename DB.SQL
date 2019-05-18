-- we don't know how to generate root <with-no-name> (class Root) :(
create table users
(
	user_id serial not null
		constraint users_pk
			primary key,
	login varchar(255) not null,
	password varchar(255) not null,
	firstname varchar(255) not null,
	lastname varchar(255) not null
);

alter table users owner to admin;

create table roles
(
	role varchar(255) not null,
	role_id serial not null
		constraint roles_pk
			primary key
);

alter table roles owner to admin;

create table user_role
(
	user_role_id serial not null
		constraint user_role_pk
			primary key,
	user_id integer not null
		constraint user_user___fk
			references users,
	role_id integer not null
		constraint user_role___fk
			references roles
);

alter table user_role owner to admin;

create table persistent_logins
(
	username varchar(64) not null,
	series varchar(64) not null
		constraint persistent_logins_pkey
			primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

alter table persistent_logins owner to admin;

create function update_changetimestamp_column() returns trigger
	language plpgsql
as $$
BEGIN
  NEW.changetimestamp = now();
  RETURN NEW;
END;
$$;

alter function update_changetimestamp_column() owner to admin;

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

alter function update_modified_column() owner to admin;

create trigger bu_persistent_logins
	before update
	on persistent_logins
	for each row
	execute procedure update_modified_column();

