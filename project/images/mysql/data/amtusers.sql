DROP SCHEMA IF EXISTS amtusers;
CREATE SCHEMA amtusers;
USE amtusers;

--
-- Table structure for table `users`
--

CREATE TABLE users (
  name VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  username VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY  (username)
);
