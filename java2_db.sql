DROP DATABASE IF EXISTS java2;
CREATE DATABASE java2;
USE java2;

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `heightInInches` int(11) DEFAULT NULL,
  `weightInPounds` decimal(6,2) DEFAULT NULL,
  `dateOfBirth` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
  
INSERT INTO `person` VALUES ('1', 'George', 'Washington', '74', '175.1', '1732-02-22');
INSERT INTO `person` VALUES ('2', 'John', 'Adams', '67', '149.9', '1735-10-30');
INSERT INTO `person` VALUES ('3', 'Thomas', 'Jefferson', '74', '174.0', '1743-04-13');
INSERT INTO `person` VALUES ('4', 'James', 'Madison', '64', '100.1', '1751-03-16');
INSERT INTO `person` VALUES ('5', 'James', 'Monroe', '72', '189.0', '1758-04-28');
INSERT INTO `person` VALUES ('6', 'John Quincy', 'Adams', '67', '175.1', '1767-07-11');
INSERT INTO `person` VALUES ('7', 'Andrew', 'Jackson', '73', '140.0', '1767-03-15');
INSERT INTO `person` VALUES ('8', 'Martin', 'Van Buren', '66', '164.1', '1782-12-05');
INSERT INTO `person` VALUES ('9', 'William Henry', 'Harrison', '68', '162.1', '1773-02-09');
INSERT INTO `person` VALUES ('10', 'John', 'Tyler', '72', '160.1', '1790-03-29');
INSERT INTO `person` VALUES ('11', 'James K.', 'Polk', '68', '174.0', '1795-11-02');
INSERT INTO `person` VALUES ('12', 'Zachary', 'Taylor', '68', '170.0', '1784-11-24');
INSERT INTO `person` VALUES ('13', 'Millard', 'Fillmore', '69', '174.0', '1800-01-07');
INSERT INTO `person` VALUES ('14', 'Franklin', 'Pierce', '70', '144.0', '1804-11-23');
INSERT INTO `person` VALUES ('15', 'James', 'Buchanan', '72', '198.0', '1791-04-23');
INSERT INTO `person` VALUES ('16', 'Abraham', 'Lincoln', '76', '179.9', '1809-02-12');
INSERT INTO `person` VALUES ('17', 'Andrew', 'Johnson', '70', '174.0', '1808-12-29');
INSERT INTO `person` VALUES ('18', 'Ulysses S.', 'Grant', '68', '156.1', '1822-04-27');
INSERT INTO `person` VALUES ('19', 'Rutherford B.', 'Hayes', '69', '175.1', '1822-10-04');
INSERT INTO `person` VALUES ('20', 'James A.', 'Garfield', '72', '184.1', '1831-11-19');
INSERT INTO `person` VALUES ('21', 'Chester A.', 'Arthur', '74', '224.0', '1829-10-05');
INSERT INTO `person` VALUES ('22', 'Grover', 'Cleveland', '71', '260.0', '1837-03-18');
INSERT INTO `person` VALUES ('23', 'Benjamin', 'Harrison', '66', '160.1', '1833-08-20');
INSERT INTO `person` VALUES ('25', 'William', 'McKinley', '67', '199.1', '1843-01-29');
INSERT INTO `person` VALUES ('26', 'Theodore', 'Roosevelt', '70', '210.1', '1858-10-27');
INSERT INTO `person` VALUES ('27', 'William Howard', 'Taft', '72', '340.0', '1857-09-15');
INSERT INTO `person` VALUES ('28', 'Woodrow', 'Wilson', '71', '170.0', '1856-12-28');
INSERT INTO `person` VALUES ('29', 'Warren G.', 'Harding', '72', '173.1', '1865-11-02');
INSERT INTO `person` VALUES ('30', 'Calvin', 'Coolidge', '70', '148.0', '1872-07-04');
INSERT INTO `person` VALUES ('31', 'Herbert', 'Hoover', '72', '187.0', '1874-08-10');
INSERT INTO `person` VALUES ('32', 'Franklin D.', 'Roosevelt', '74', '188.1', '1882-01-30');
INSERT INTO `person` VALUES ('33', 'Harry S.', 'Truman', '69', '166.9', '1884-05-08');
INSERT INTO `person` VALUES ('34', 'Dwight D.', 'Eisenhower', '70', '171.1', '1890-10-14');
INSERT INTO `person` VALUES ('35', 'John F.', 'Kennedy', '73', '173.1', '1917-05-29');
INSERT INTO `person` VALUES ('36', 'Lyndon B.', 'Johnson', '76', '200.0', '1908-08-27');
INSERT INTO `person` VALUES ('37', 'Richard', 'Nixon', '72', '175.1', '1913-01-09');
INSERT INTO `person` VALUES ('38', 'Gerald', 'Ford', '72', '190.1', '1913-07-14');
INSERT INTO `person` VALUES ('39', 'Jimmy', 'Carter', '70', '160.1', '1924-10-01');
INSERT INTO `person` VALUES ('40', 'Ronald', 'Reagan', '73', '185.0', '1911-02-06');
INSERT INTO `person` VALUES ('41', 'George H. W.', 'Bush', '74', '196.0', '1924-06-12');
INSERT INTO `person` VALUES ('42', 'Bill', 'Clinton', '74', '223.1', '1946-08-19');
INSERT INTO `person` VALUES ('43', 'George W.', 'Bush', '72', '195.4', '1946-07-06');
INSERT INTO `person` VALUES ('44', 'Barack', 'Obama', '74', '179.9', '1961-08-04');
INSERT INTO `person` VALUES ('45', 'Donald', 'Trump', '75', '239.0', '1946-06-14');
INSERT INTO `person` VALUES ('46', 'Joe', 'Biden', '72', '177.9', '1942-11-20');

DELIMITER $$
CREATE PROCEDURE sp_add_person(
    IN p_firstName varchar(100),
    IN p_lastName varchar(100),
    IN p_heightInInches int(11),
    IN p_weightInPounds decimal(6,2),
    IN p_dateOfBirth datetime
)
BEGIN
    INSERT INTO Person(
        firstName,
        lastName,
        heightInInches,
        weightInPounds,
        dateOfBirth
    )
    VALUES (
        p_firstName,
        p_lastName,
        p_heightInInches,
        p_weightInPounds,
        p_dateOfBirth
    );
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_all_people()
BEGIN
    SELECT id, firstName, lastName, heightInInches, weightInPounds, dateOfBirth
    FROM person;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_person_by_id(
    IN p_id int(11)
) 
BEGIN
    SELECT id, firstName, lastName, heightInInches, weightInPounds, dateOfBirth
    FROM person
    WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_person_by_name(
    IN p_name varchar(100)
) 
BEGIN
    SELECT id, firstName, lastName, heightInInches, weightInPounds, dateOfBirth
    FROM person
    WHERE firstName LIKE CONCAT('%',p_name, '%') OR lastName LIKE CONCAT('%',p_name, '%') OR  CONCAT(firstName," ", lastName) LIKE CONCAT('%',p_name, '%');
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_person_by_date(
    IN p_dateOfBirth datetime
) 
BEGIN
    SELECT id, firstName, lastName, heightInInches, weightInPounds, dateOfBirth
    FROM person
    WHERE dateOfBirth = p_dateOfBirth;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_update_person(
    IN p_id int(11),
    IN p_firstName varchar(100),
    IN p_lastName varchar(100),
    IN p_heightInInches int(11),
    IN p_weightInPounds decimal(6,2),
    IN p_dateOfBirth datetime
)
BEGIN
    UPDATE person
        SET firstName =  p_firstName,
        lastName =  p_lastName,
        heightInInches =  p_heightInInches,
        weightInPounds =  p_weightInPounds,
        dateOfBirth =   p_dateOfBirth
    WHERE id =  p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_delete_person(
    IN p_id int(11)
)
BEGIN
    DELETE FROM person
    WHERE id = p_id;
END$$
DELIMITER ;

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `personId` int(11) NOT NULL,
  `readBook` tinyint DEFAULT 1,
  `numPages` int(11) DEFAULT 1,
  `datePublished` date DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
  
ALTER TABLE `book`
  ADD foreign key (`personId`) references person(`id`);

INSERT INTO `book` VALUES ('1', 'Promises to Keep', '46', '0', '400', '2008-08-28', '5.99');
INSERT INTO `book` VALUES ('2', 'Trump: The Art of the Deal', '45', '0', '384', '2015-10-06', '12.19');

DELIMITER $$
CREATE PROCEDURE sp_add_book(
    IN p_title varchar(100),
    IN p_person_Id int(11),
    IN p_read tinyint,
    IN p_numPages int(11),
    IN p_datePublished date,
    IN p_price decimal(11,2)
)
BEGIN
    INSERT INTO book(
        title,
        personId,
        readBook,
        numPages,
        datePublished,
        price
    )
    VALUES (
        p_title,
        p_person_Id,
        p_read,
        p_numPages,
        p_datePublished,
        p_price
    );
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_all_books()
BEGIN
    SELECT id, title, personId, readBook, numPages, datePublished, price
    FROM book;
END$$
DELIMITER ;
