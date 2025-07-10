USE pets;
CREATE TABLE if not exists household(
  id INT auto_increment primary key,
  adress varchar(200) NOT NULL
); 


CREATE TABLE if not exists person(
id INT auto_increment primary key,
first_name varchar(100) not null,
last_name varchar(100) not null,
id_haushold INT,
foreign key (id_haushold) references haushold(id) on delete cascade
);

CREATE TABLE if not exists pet(
id INT auto_increment primary key,
namePet varchar(100),
id_person INT,
foreign key (id_person) references person(id) on delete cascade
);


