-- auto-generated definition
create table studens
(
  id       serial      not null
    constraint studens_pk
    primary key,
  name     varchar(20) not null,
  surname  varchar(40) not null,
  group_id integer     not null
    constraint studens_group_id_fk
    references courses,
  age      integer,
  contact  text
);

alter table studens
  owner to postgres;

create unique index studens_id_uindex
  on studens (id);

-- auto-generated definition
create table courses
(
  id   serial      not null
    constraint group_pkey
    primary key,
  name varchar(20) not null
);

alter table courses
  owner to postgres;

create unique index courses_name_uindex
  on courses (name);