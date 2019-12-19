-- we don't know how to generate root <with-no-name> (class Root) :(
create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to admin;

create sequence report_id_seq;

alter sequence report_id_seq owner to admin;

create table role
(
	role varchar(255) not null,
	id serial not null
		constraint roles_pk
			primary key
);

alter table role owner to postgres;

create table users
(
	id serial not null
		constraint users_pk
			primary key,
	login varchar(255) not null,
	password varchar(255) not null,
	firstname varchar(255) not null,
	lastname varchar(255) not null,
	active integer,
	role_id integer
		constraint users_roles_role_id_fk
			references role
);

alter table users owner to postgres;

create table persistent_logins
(
	username varchar(64) not null,
	series varchar(64) not null
		constraint persistent_logins_pkey
			primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

alter table persistent_logins owner to postgres;

create table "order"
(
	id serial not null
		constraint order_pk
			primary key,
	num text,
	text text,
	creator_id integer
		constraint docs_users_user_id_fk
			references users,
	manager_id integer
		constraint orders_users_user_id_fk
			references users,
	at date,
	title text,
	state integer
);

alter table "order" owner to postgres;

create table chat
(
	id integer not null
		constraint chat_pk
			primary key,
	name text
);

alter table chat owner to postgres;

create table chat_message
(
	id integer not null
		constraint chat_massage_pk
			primary key,
	text text,
	chat_id integer
		constraint chat_massage_chat_id_fk
			references chat,
	author_id integer
		constraint chat_massage_users_user_id_fk
			references users,
	date_create timestamp,
	parent_message_id integer
		constraint chat_message_chat_message_id_fk
			references chat_message,
	response_to_message_id integer
		constraint chat_message_chat_message_id_fk_2
			references chat_message
);

alter table chat_message owner to postgres;

create table chat_user
(
	chat_id integer not null
		constraint chat_user_chat_id_fk
			references chat,
	user_id integer not null
		constraint chat_user_users_user_id_fk
			references users,
	constraint chat_user_pk
		primary key (chat_id, user_id)
);

alter table chat_user owner to postgres;

create table report
(
	id integer default nextval('report_id_seq'::regclass) not null
		constraint reports_pk
			primary key,
	num text,
	text text,
	creator_id integer
		constraint reports_users_user_id_fk
			references users,
	order_id integer
		constraint reports_docs_id_fk
			references "order",
	at date,
	state integer
);

alter table report owner to postgres;

create table check_list_item
(
	id integer not null
		constraint check_list_items_pk
			primary key,
	order_id integer
		constraint check_lists_docs_id_fk
			references "order",
	name text,
	date_start_work date,
	date_end_work date,
	report_id integer
		constraint check_list_items_reports_id_fk
			references report
);

alter table check_list_item owner to postgres;

create table check_list_item_chat
(
	chat_id integer not null
		constraint check_list_item_chat_chat_id_fk
			references chat,
	check_list_item_id integer not null
		constraint check_list_item_chat_check_list_items_id_fk
			references check_list_item,
	constraint check_list_item_chat_pk
		primary key (chat_id, check_list_item_id)
);

alter table check_list_item_chat owner to postgres;

