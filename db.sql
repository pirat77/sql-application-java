create table mentors
(
    id serial not null
        constraint mentors_pk
            primary key,
    first_name text not null,
    last_name text not null,
    nick_name text not null,
    phone_number text not null,
    email text not null,
    city text not null,
    favourite_number integer
);

alter table mentors
    owner to postgres;

create unique index mentors_id_uindex
	on mentors (id);

create table applicants
(
	id serial not null
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

create unique index applicants_id_uindex
	on applicants (id);