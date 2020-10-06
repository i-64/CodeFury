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

select m.unique_name, m.seating_capacity, m.total_meetings_conducted, f.rating from meeting_room m inner join ( select meeting_room_id, SUM(rating)/COUNT(rating) as rating from feedback group by meeting_room_id ) f on m.unique_name = f.meeting_room_id;