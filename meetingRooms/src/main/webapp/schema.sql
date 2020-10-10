connect 'jdbc:derby:c:/database/meetingRoomsDB;create=true;user=admin;password=admin';

// log table

DROP TABLE LOG;
CREATE TABLE LOG (

	user_id varchar (50),
	
	last_login_time timestamp,
	
	user_file_path varchar (500) default null,
	
	foreign key (user_id) references USERS (user_id)
);


// user table

DROP TABLE USERS;
CREATE TABLE USERS (
	
	user_id varchar (50) primary key,

	password varchar (50) not null,
	name varchar (50) not null,
	email varchar (50) not null,
	phone varchar (12) not null,

	credits int default 0,
	role varchar (10) default 'member'
);


// amenities table

DROP TABLE AMENITIES;
CREATE TABLE AMENITIES (
	
	id int primary key generated always as identity (start with 1000, increment by 1),
	
	name varchar (30) not null,
	credit int default 0
	
);

INSERT INTO AMENITIES (name, credit) VALUES ('Projector', 5), ('WiFi Connection', 10), ('Conference Call Facility', 15), ('White Board', 5), ('Water Dispenser', 5), ('TV', 10), ('Coffee Machine', 10);


// room_amenities table

DROP TABLE ROOM_AMENITIES;
CREATE TABLE ROOM_AMENITIES (
	
	meeting_room_id varchar (30),
	amenity_id int,
	
	foreign key (meeting_room_id) references MEETING_ROOM (unique_name),
	foreign key (amenity_id) references AMENITIES (id)
);


// meeting_room table

DROP TABLE MEETING_ROOM;
CREATE TABLE MEETING_ROOM (
	
	unique_name varchar (30) primary key,
	
	seating_capacity int default 0,
	per_hour_cost int default 0,
	total_meetings_conducted int default 0,
	created_by varchar (50),
	
	foreign key (created_by) references USERS (user_id)
);


// feedback table

DROP TABLE FEEDBACK;
CREATE TABLE FEEDBACK (

	id int primary key generated always as identity (start with 1000, increment by 1),
	
	meeting_room_id varchar (30),
	user_id varchar (50),
	rating int default 0,
	
	foreign key (user_id) references USERS (user_id),
	foreign key (meeting_room_id) references MEETING_ROOM (unique_name)
);


// booking_info table

DROP TABLE BOOKING_INFO;
CREATE TABLE BOOKING_INFO (

	unique_id int primary key generated always as identity (start with 1000, increment by 1),
	
	meeting_room_id varchar (30),
	booked_by varchar (50),
	
	booking_date date not null,
	start_time time not null,
	end_time time not null,
	
	foreign key (meeting_room_id) references MEETING_ROOM (unique_name),
	foreign key (booked_by) references USERS (user_id)
);


// meeting table

DROP TABLE MEETING;
CREATE TABLE MEETING (

	id int primary key generated always as identity (start with 1000, increment by 1),
	
	title varchar (50) not null,
	organized_by varchar (50),
	
	meeting_date date not null,
	start_time time not null,
	end_time time not null,
	meeting_room_id varchar (30),
	
	meeting_type_id int,
	
	foreign key (meeting_room_id) references MEETING_ROOM (unique_name),
	foreign key (meeting_type_id) references MEETING_TYPES (id),
	foreign key (organized_by) references USERS (user_id)
);


// meeting_types table
DROP TABLE MEETING_TYPES;
CREATE TABLE MEETING_TYPES (

	id int primary key generated always as identity (start with 1000, increment by 1),
	
	meeting_type varchar (50) not null,
	mandatory_amenities varchar (100) not null
);

INSERT INTO MEETING_TYPES (meeting_type, mandatory_amenities) VALUES ('Classroom Training', '1000,1003'), ('Online Training', '1000,1001'), ('Conference Call', '1002'), ('Business', '1000');



// attendees table
DROP TABLE ATTENDEES;
CREATE TABLE ATTENDEES  (

	meeting_id int,
	user_id varchar (50),
	
	foreign key (meeting_id) references MEETING (id),
	foreign key (user_id) references USERS (user_id)
);


//credits renewal table
DROP TABLE CREDIT_RENEWAL;
CREATE TABLE CREDIT_RENEWAL (

	user_id varchar (50),
	
	next_Renewal_Date timestamp,
	
	foreign key (user_id) references USERS (user_id)

);
