create table circle
(
    id   bigint not null
        primary key,
    name text
);

create table writer
(
    id      bigint not null
        primary key,
    name    text,
    penname text,
    title   text
);

create table circle_writers
(
    writer_id bigint not null
        constraint circle_writer_to_writer
            references writer,
    circle_id bigint not null
        constraint circle_writers_to_circle
            references circle
);

create table letter
(
    id        bigint not null
        primary key,
    content   text,
    date      timestamp,
    subject   text,
    writer_id bigint
        constraint letter_to_writer
            references writer
);

create table circle_letters
(
    letter_id bigint not null
        constraint circle_letters_to_letter
            references letter,
    circle_id bigint not null
        constraint circle_letters_to_circle
            references circle
);

