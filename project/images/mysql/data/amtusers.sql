DROP SCHEMA IF EXISTS amtusers;
CREATE SCHEMA amtusers;
USE amtusers;

--
-- Table structure for table `users`
--

CREATE TABLE users (
  -- users_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY  (username)
);
