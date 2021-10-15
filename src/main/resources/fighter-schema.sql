drop table if exists fighter CASCADE;
create table fighter
 (
 	id integer primary key auto_increment,
 	name varchar(255),
 	armour varchar(255),
 	weapon varchar(255),
    health Integer(3),
    defence Integer(3),
    meleeskill Integer(3)
 );