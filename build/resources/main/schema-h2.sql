create table public.album (
    album_id integer primary key,
    album_name character varying,
    artist_name character varying,
    date_released date,
    genre character varying,
    num_tracks integer,
    price numeric
    );

drop sequence public.album_id_seq;

create sequence public.album_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;


create table public.application_user (
    user_id INTEGER PRIMARY KEY,
    username character varying,
    password character varying,
    is_admin boolean
);

drop sequence public.application_user_id_seq;

create sequence public.application_user_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;