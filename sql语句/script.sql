create table user
(
    id         int                                not null
        primary key,
    username   varchar(50)                        null,
    password   varchar(50)                        null,
    deleted    int                                not null,
    updatetime datetime default CURRENT_TIMESTAMP not null,
    constraint user_pk2
        unique (username)
);

create table family
(
    Family_Id      int                                not null
        primary key,
    id             int                                null,
    Family_Name    varchar(50)                        not null,
    Family_Content varchar(50)                        null,
    Family_Time    varchar(50)                        null,
    deleted        int                                not null,
    updatetime     datetime default CURRENT_TIMESTAMP not null,
    constraint family_pk2
        unique (Family_Name),
    constraint family_user_id_fk
        foreign key (id) references user (id)
);

create definer = root@`%` trigger BB
    after update
    on family
    for each row
BEGIN
IF NEW.deleted = 1 THEN
UPDATE members SET deleted = 1 WHERE Family_Id = OLD.Family_Id;
END IF;
END;

create table members
(
    Members_Id       int                                not null comment '成员id'
        primary key,
    Family_Id        int                                not null comment '家谱id',
    Members_Name     varchar(50)                        not null comment '成员名称',
    Members_Sex      varchar(50)                        not null comment '成员性别',
    Members_Birthday varchar(50)                        not null comment '出生日期',
    Members_Death    varchar(50)                        null comment '死亡日期',
    deleted          int                                not null,
    updatetime       datetime default CURRENT_TIMESTAMP not null,
    constraint members_pk2
        unique (Members_Name),
    constraint members_family_Family_Id_fk
        foreign key (Family_Id) references family (Family_Id)
);

create definer = root@`%` trigger CC
    after update
    on members
    for each row
BEGIN
IF NEW.deleted = 1 THEN
UPDATE things SET deleted = 1 WHERE Members_Id = OLD.Members_Id;
END IF;
END;

create definer = root@`%` trigger DD
    after update
    on members
    for each row
BEGIN
IF NEW.deleted = 1 THEN
UPDATE relation SET deleted = 1 WHERE Members_Id1 = OLD.Members_Id OR Members_Id2 = OLD.Members_Id;
END IF;
END;

create table relation
(
    Members_Id1   int                                not null comment '成员名称1',
    Members_Id2   int                                not null comment '成员名称2',
    Relation_Type varchar(50)                        not null comment '关系类型',
    deleted       int                                not null,
    updatetime    datetime default CURRENT_TIMESTAMP not null,
    constraint relation_members_Members_Id_fk
        foreign key (Members_Id1) references members (Members_Id),
    constraint relation_members_Members_Id_fk2
        foreign key (Members_Id2) references members (Members_Id)
);

create table things
(
    Members_Id       int                                not null comment '成员id',
    Things_Time      varchar(50)                        null comment '事件时间',
    Things_Type      varchar(50)                        null comment '事件类型',
    Things_Content   varchar(200)                       null comment '事件信息',
    Things_Accessory varchar(200)                       null comment '事件附件',
    deleted          int                                not null,
    updatetime       datetime default CURRENT_TIMESTAMP not null,
    constraint things_members_Members_Id_fk
        foreign key (Members_Id) references members (Members_Id)
);

create definer = root@`%` trigger AA
    after update
    on user
    for each row
BEGIN
IF NEW.deleted = 1 THEN
UPDATE family SET deleted = 1 WHERE id = OLD.id;
END IF;
END;


