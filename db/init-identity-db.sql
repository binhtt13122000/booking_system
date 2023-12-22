CREATE TABLE public.users
(
    id         serial       NOT NULL,
    email      varchar(255) NOT NULL,
    "password" varchar(255) NOT NULL,
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT users_pk PRIMARY KEY (id)
);