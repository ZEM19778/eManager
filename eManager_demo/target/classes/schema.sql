CREATE SCHEMA IF NOT EXISTS emanager;
create table b_baustelle
(
    b_id          bigint auto_increment
        primary key,
    b_bezeichnung varchar(255) null
);

create table d_dienste
(
    d_id          bigint auto_increment
        primary key,
    d_datum       datetime     null,
    d_dauer       float        null,
    d_mitarbeiter varchar(255) null,
    d_bis         time         null,
    d_von         time         null,
    b_d_id        bigint       null,
    constraint FKl1q45o4xdpyoucrer9o6ai71y
        foreign key (b_d_id) references b_baustelle (b_id)
);

create table hibernate_sequence
(
    next_val bigint null
);

create table m_mitarbeiter
(
    m_id           bigint       not null
        primary key,
    m_passwort     varchar(255) null,
    m_rolle        varchar(255) null,
    m_benutzername varchar(255) null,
    m_vorname      varchar(255) null
);

create table n_nachrichten
(
    n_id        bigint auto_increment
        primary key,
    n_datumzeit varchar(255) not null,
    n_nachricht varchar(255) null,
    n_sender    varchar(255) null
);

create table t_termine
(
    t_id           bigint auto_increment
        primary key,
    t_beginn       time         null,
    t_beschreibung varchar(255) null,
    t_betrifft     varchar(255) null,
    t_datum        date         null,
    t_ende         time         null
);

create table u_urlaub
(
    u_id             bigint auto_increment
        primary key,
    u_beantragt_name varchar(255) null,
    u_beginn         date         null,
    u_beschreibung   varchar(255) null,
    u_ende           date         null,
    u_genehmigt      varchar(255) null
);