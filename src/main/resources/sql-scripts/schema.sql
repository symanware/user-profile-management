CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  role_type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE app_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES app_user (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE address (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type varchar(255) NOT NULL,
  line1 varchar(255) NOT NULL,
  line2 varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  zipcode varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_address (
    user_id bigint(20) NOT NULL,
    address_id bigint(20) NOT NULL,
    CONSTRAINT FK860n2jvi8ivhui0rl0esws7o FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT FKa68596081fvovjhkek5m97n3y FOREIGN KEY (address_id) REFERENCES address (id)
);