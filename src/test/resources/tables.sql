CREATE TABLE test.student
(
    id      int auto_increment
        primary key,
    surname varchar(200)                null,
    name    varchar(200)                null,
    `group` varchar(200)                null,
    date    timestamp(3)                null,
    status  enum ('1', '0') default '1' null
);

INSERT INTO test.student (`surname`, `name`, `group`, `date`, `status`) VALUES ('Тестовый', 'Пользователь1', 'КТ-21', '2022-11-23 00:00:00.000', '1');
INSERT INTO test.student (`surname`, `name`, `group`, `date`, `status`) VALUES ('Тестовый', 'Пользователь2', 'КТ-21', '2022-11-23 00:00:00.000', '1');

CREATE TABLE test.phone
(
    id         int auto_increment
        primary key,
    id_student int         null,
    phone      varchar(45) null,
    constraint fk7
        foreign key (id_student) references student (id)
);

create index fk5_idx
    on phone (id_student);

INSERT INTO test.phone (`id_student`, `phone`) VALUES ('1', '1111111');
INSERT INTO test.phone (`id_student`, `phone`) VALUES ('2', '2222222');

CREATE TABLE test.term
(
    id       int auto_increment
        primary key,
    term     varchar(200)                null,
    duration varchar(200)                null,
    status   enum ('1', '0') default '1' null
);

INSERT INTO test.term (`term`, `duration`, `status`) VALUES ('Семестр 1', '10 недель', '1');
INSERT INTO test.term (`term`, `duration`, `status`) VALUES ('Семестр 2', '12 недель', '1');

CREATE TABLE test.discipline
(
    id         int auto_increment
        primary key,
    discipline varchar(200)                null,
    status     enum ('1', '0') default '1' null
);

INSERT INTO test.discipline (`discipline`, `status`) VALUES ('Английский язык', '1');
INSERT INTO test.discipline (`discipline`, `status`) VALUES ('Немецкий язык', '1');

CREATE TABLE test.term_discipline
(
    id            int auto_increment
        primary key,
    id_term       int null,
    id_discipline int null,
    constraint fk1
        foreign key (id_term) references term (id),
    constraint fk2
        foreign key (id_discipline) references discipline (id)
);

create index fk1_idx
    on term_discipline (id_term);

create index fk2_idx
    on term_discipline (id_discipline);

INSERT INTO test.term_discipline (`id_term`, `id_discipline`) VALUES ('1', '1');
INSERT INTO test.term_discipline (`id_term`, `id_discipline`) VALUES ('1', '2');
INSERT INTO test.term_discipline (`id_term`, `id_discipline`) VALUES ('2', '1');