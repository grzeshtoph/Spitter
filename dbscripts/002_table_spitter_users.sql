create table spitter_users (
	user_id int not null default nextval('user_id_seq'),
	username varchar(50) not null,
	password varchar(50) not null,
	full_name varchar(100) not null);