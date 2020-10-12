connect 'jdbc:derby:c:/database/meetingRoomsDB;create=true;user=admin;password=admin';


CREATE TABLE USERS (

user_id varchar (50) primary key,

password varchar (50) not null,
name varchar (50) not null,
email varchar (50) not null,
phone varchar (12) not null,

credits int default 0,
role varchar (10) default 'member'
);


insert into users values ( '5000', 'd969eef6ecad3c29a3a629280', 'Madhur Dave', 'abc@email.com', '1245956315', 0, 'member' );

insert into users values ( '5001', 'd969eef6ecad3c29a3a629280', 'Ishani Mishra', 'def@email.com', '5695213457', 0, 'member' );

insert into users values ( '5002', 'd969eef6ecad3c29a3a629280', 'Vaneet Kant', 'ghi@email.com', '1478523690', 0, 'member' );

insert into users values ( '5003', 'd969eef6ecad3c29a3a629280', 'Akash Som', 'jkl@email.com', '1596358625', 0, 'member' );

insert into users values ( '5004', 'd969eef6ecad3c29a3a629280', 'Tanvi Khatri', 'mno@email.com', '6478123458', 0, 'member' );


insert into users values ( '5005', 'd969eef6ecad3c29a3a629280', 'Shakti Hora', 'pqr@email.com', '2589631474', 0, 'admin' );

insert into users values ( '5006', 'd969eef6ecad3c29a3a629280', 'Yash Din', 'stu@email.com', '6497316482', 0, 'admin' );

insert into users values ( '5007', 'd969eef6ecad3c29a3a629280', 'Rishima Iyer', 'vwx@email.com', '5286479123', 0, 'admin' );

insert into users values ( '5008', 'd969eef6ecad3c29a3a629280', 'Indrajit Mandal', 'yza@email.com', '4589657841', 0, 'admin' );

insert into users values ( '5009', 'd969eef6ecad3c29a3a629280', 'Sarita Shere', 'asq@email.com', '3214578963', 0, 'admin' );


insert into users values ( '5010', 'd969eef6ecad3c29a3a629280', 'Mitra Batta', 'asd@email.com', '2356478596', 2000, 'manager' );

insert into users values ( '5011', 'd969eef6ecad3c29a3a629280', 'Kalyani Bhatti', 'mnn@email.com', '1526475896', 2000, 'manager' );

insert into users values ( '5012', 'd969eef6ecad3c29a3a629280', 'Neel Chad', 'vfe@email.com', '2654789536', 2000, 'manager' );

insert into users values ( '5013', 'd969eef6ecad3c29a3a629280', 'Raghav Sidhu', 'cds@email.com', '5621452325', 2000, 'manager' );

insert into users values ( '5014', 'd969eef6ecad3c29a3a629280', 'Chiranjivi Hegde', 'vde@email.com', '4598751246', 2000, 'manager' );



CREATE TABLE LOG (

user_id varchar (50),

last_login_time timestamp,

user_file_path varchar (500) default null,

foreign key (user_id) references USERS (user_id) ON DELETE CASCADE

 );

insert into log values('5000','2008-01-01 00:00:01',null);

insert into log values('5001','2008-01-02 00:00:01',null);

insert into log values('5002','2008-01-07 00:00:01',null);

insert into log values('5003','2008-01-10 00:00:01',null);

insert into log values('5004','2008-02-01 00:00:01',null);

insert into log values('5005','2008-08-09 00:00:01',null);

insert into log values('5006','2008-01-11 00:00:01',null);

insert into log values('5007','2008-08-01 00:00:01',null);

insert into log values('5008','2008-01-27 00:00:01',null);

insert into log values('5009','2008-11-01 00:00:01',null);

insert into log values('5010','2008-01-07 00:00:01',null);

insert into log values('5010','2010-01-01 00:00:01',null);

insert into log values('5011','2020-01-08 00:00:01',null);

insert into log values('5012','2018-01-01 00:00:01',null);

insert into log values('5013','2017-01-04 00:00:01',null);

insert into log values('5014','2006-01-08 00:00:01',null);


CREATE TABLE AMENITIES (
	
	id int primary key generated always as identity (start with 1000, increment by 1),
	
	name varchar (30) not null,
	credit int default 0
	
);

INSERT INTO AMENITIES (name, credit) VALUES ('Projector', 5), ('WiFi Connection', 10), ('Conference Call Facility', 15), ('White Board', 5), ('Water Dispenser', 5), ('TV', 10), ('Coffee Machine', 10);



CREATE TABLE MEETING_ROOM (

unique_name varchar (30) primary key,

seating_capacity int default 0,
per_hour_cost int default 0,
total_meetings_conducted int default 0,
created_by varchar (50),
foreign key (created_by) references USERS (user_id) ON DELETE CASCADE
);

insert into meeting_room values ( 'Meeting Room 1', 5, 20, 12, '5005');

insert into meeting_room values ( 'Meeting Room 2', 20, 50, 7, '5009');

insert into meeting_room values ( 'Meeting Room 3', 7, 25, 23, '5007');

insert into meeting_room values ( 'Meeting Room 4', 10, 30, 11, '5008');

insert into meeting_room values ( 'Meeting Room 5', 3, 45, 5, '5006');

insert into meeting_room values ( 'Meeting Room 6', 4, 35, 1, '5006');

insert into meeting_room values ( 'Meeting Room 7', 9, 40, 6, '5005');

insert into meeting_room values ( 'Meeting Room 8', 30, 45, 9, '5009');


CREATE TABLE ROOM_AMENITIES (

meeting_room_id varchar (30),
amenity_id int,

foreign key (meeting_room_id) references MEETING_ROOM (unique_name) ON DELETE CASCADE,
foreign key (amenity_id) references AMENITIES (id) ON DELETE CASCADE
);

insert into room_amenities values ( 'Meeting Room 1', 1002 );
insert into room_amenities values ( 'Meeting Room 1', 1004 );

insert into room_amenities values ( 'Meeting Room 2', 1002 );
insert into room_amenities values ( 'Meeting Room 2', 1004 );
insert into room_amenities values ( 'Meeting Room 2', 1005 );

insert into room_amenities values ( 'Meeting Room 3', 1003 );
insert into room_amenities values ( 'Meeting Room 3', 1000 );
insert into room_amenities values ( 'Meeting Room 3', 1004 );

insert into room_amenities values ( 'Meeting Room 4', 1003 );
insert into room_amenities values ( 'Meeting Room 4', 1000 );
insert into room_amenities values ( 'Meeting Room 4', 1006 );

insert into room_amenities values ( 'Meeting Room 5', 1000 );
insert into room_amenities values ( 'Meeting Room 5', 1001 );
insert into room_amenities values ( 'Meeting Room 5', 1002 );
insert into room_amenities values ( 'Meeting Room 5', 1004 );
insert into room_amenities values ( 'Meeting Room 5', 1006 );

insert into room_amenities values ( 'Meeting Room 6', 1000 );
insert into room_amenities values ( 'Meeting Room 6', 1002 );
insert into room_amenities values ( 'Meeting Room 6', 1005 );
insert into room_amenities values ( 'Meeting Room 6', 1005 );

insert into room_amenities values ( 'Meeting Room 7', 1001 );
insert into room_amenities values ( 'Meeting Room 7', 1000 );
insert into room_amenities values ( 'Meeting Room 7', 1003 );
insert into room_amenities values ( 'Meeting Room 7', 1004 );
insert into room_amenities values ( 'Meeting Room 7', 1006 );


insert into room_amenities values ( 'Meeting Room 7', 1001 );
insert into room_amenities values ( 'Meeting Room 7', 1000 );
insert into room_amenities values ( 'Meeting Room 7', 1003 );
insert into room_amenities values ( 'Meeting Room 7', 1004 );


insert into room_amenities values ( 'Meeting Room 1', 1000 );
insert into room_amenities values ( 'Meeting Room 1', 1001 );
insert into room_amenities values ( 'Meeting Room 1', 1003 );
insert into room_amenities values ( 'Meeting Room 1', 1005 );
insert into room_amenities values ( 'Meeting Room 1', 1006 );
insert into room_amenities values ( 'Meeting Room 2', 1000 );
insert into room_amenities values ( 'Meeting Room 2', 1001 );
insert into room_amenities values ( 'Meeting Room 2', 1003 );
insert into room_amenities values ( 'Meeting Room 2', 1006 );
insert into room_amenities values ( 'Meeting Room 3', 1001 );
insert into room_amenities values ( 'Meeting Room 3', 1002 );
insert into room_amenities values ( 'Meeting Room 3', 1005 );
insert into room_amenities values ( 'Meeting Room 3', 1006 );


CREATE TABLE FEEDBACK (

id int primary key generated always as identity (start with 1000, increment by 1),

meeting_room_id varchar (30),
user_id varchar (50),
rating int default 0,

foreign key (user_id) references USERS (user_id) ON DELETE CASCADE,
foreign key (meeting_room_id) references MEETING_ROOM (unique_name) ON DELETE CASCADE
);

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 1', '5000', 5 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 2', '5000', 4 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 3', '5000', 2 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 4', '5000', 3 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 5', '5000', 1 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 6', '5000', 4 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 7', '5000', 4 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 8', '5000', 3 );



insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 1', '5001', 3 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 2', '5001', 4 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 3', '5001', 4 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 4', '5001', 4 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 5', '5001', 5 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 6', '5001', 5 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 7', '5001', 1 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 8', '5001', 3 );



insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 1', '5002', 2 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 2', '5002', 4 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 3', '5002', 2 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 4', '5002', 2 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 5', '5002', 5 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 6', '5002', 5 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 7', '5002', 5 );

insert into feedback ( meeting_room_id, user_id, rating ) values ( 'Meeting Room 8', '5002', 4 );


CREATE TABLE BOOKING_INFO (

unique_id int primary key generated always as identity (start with 1000, increment by 1),

meeting_room_id varchar (30),
booked_by varchar (50),

booking_date date not null,
start_time time not null,
end_time time not null,

foreign key (meeting_room_id) references MEETING_ROOM (unique_name) ON DELETE CASCADE,
foreign key (booked_by) references USERS (user_id) ON DELETE CASCADE
);


insert into booking_info(meeting_room_id,booked_by,booking_date,start_time,end_time) values('Meeting Room 1','5010','2020-10-05','06:00:00','07:00:00');

insert into booking_info(meeting_room_id,booked_by,booking_date,start_time,end_time) values('Meeting Room 2','5011','2020-10-05','03:30:00','04:00:00');

insert into booking_info(meeting_room_id,booked_by,booking_date,start_time,end_time) values('Meeting Room 3','5014','2020-10-05','08:00:00','09:00:00');

insert into booking_info(meeting_room_id,booked_by,booking_date,start_time,end_time) values('Meeting Room 4','5012','2020-10-05','04:15:00','05:00:00');


CREATE TABLE MEETING_TYPES (

	id int primary key generated always as identity (start with 1000, increment by 1),
	
	meeting_type varchar (50) not null,
	mandatory_amenities varchar (100) not null
);

INSERT INTO MEETING_TYPES (meeting_type, mandatory_amenities) VALUES ('Classroom Training', '1000,1003'), ('Online Training', '1000,1001'), ('Conference Call', '1002'), ('Business', '1000');


CREATE TABLE MEETING (

	id int primary key generated always as identity (start with 1000, increment by 1),
	
	title varchar (50) not null,
	organized_by varchar (50),
	
	meeting_date date not null,
	start_time time not null,
	end_time time not null,
	meeting_room_id varchar (30),
	
	meeting_type_id int,
	
	foreign key (meeting_room_id) references MEETING_ROOM (unique_name) ON DELETE CASCADE,
	foreign key (meeting_type_id) references MEETING_TYPES (id) ON DELETE CASCADE,
	foreign key (organized_by) references USERS (user_id) ON DELETE CASCADE
);

INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'first meeting', '5010' , '2020-10-05', '01:15', '03:00','Meeting Room 1' , 1000 );
INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'second meeting', '5014' , '2020-10-05', '03:30', '04:00','Meeting Room 1' , 1000 );
INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'third meeting', '5010' , '2020-10-05', '08:00', '09:00','Meeting Room 1' , 1000 );


INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'first meeting', '5011' , '2020-10-05', '4:15', '05:00','Meeting Room 3' , 1000 );
INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'second meeting', '5012' , '2020-10-05', '19:30', '21:00','Meeting Room 3' , 1000 );
INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'third meeting', '5013' , '2020-10-05', '22:00', '23:00','Meeting Room 3' , 1000 );


INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'first meeting', '5010' , '2020-10-05', '06:15', '07:00','Meeting Room 2' , 1000 );
INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'second meeting', '5011' , '2020-10-05', '10:30', '11:00','Meeting Room 2' , 1000 );
INSERT INTO MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) VALUES ( 'third meeting', '5012' , '2020-10-05', '08:00', '09:00','Meeting Room 2' , 1000 );


CREATE TABLE ATTENDEES  (

	meeting_id int,
	user_id varchar (50),
	
	foreign key (meeting_id) references MEETING (id) ON DELETE CASCADE,
	foreign key (user_id) references USERS (user_id) ON DELETE CASCADE
);

insert into attendees values(1000,'5000');

insert into attendees values(1001,'5002');

insert into attendees values(1002,'5009');

insert into attendees values(1003,'5003');

insert into attendees values(1004,'5008');

insert into attendees values(1006,'5010');

insert into attendees values(1007,'5011');

insert into attendees values(1008,'5011');


CREATE TABLE CREDIT_RENEWAL (

	user_id varchar (50),
	
	next_Renewal_Date timestamp,
	
	foreign key (user_id) references USERS (user_id) ON DELETE CASCADE

);

  
insert into credit_renewal values ('5010', '2020-10-05 06:00:00');

insert into credit_renewal values ('5011', '2020-10-05 06:00:00');

insert into credit_renewal values ('5012', '2020-10-05 06:00:00');

insert into credit_renewal values ('5013', '2020-10-05 06:00:00');

insert into credit_renewal values ('5014', '2020-10-05 06:00:00');
















