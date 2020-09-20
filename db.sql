create table mentors
(
    id integer not null
        constraint mentors_pk
            primary key,
    first_name text not null,
    last_name text not null,
    nick_name text not null,
    phone_number text not null,
    email text not null,
    city text not null,
    favourtie_number integer not null
);

alter table mentors
    owner to postgres;

create table applicants
(
	id integer not null
		constraint applicants_pk
			primary key,
	first_name text not null,
	last_name text not null,
	phone_number text not null,
	email text not null,
	application_code integer not null
);

alter table applicants
	owner to postgres;