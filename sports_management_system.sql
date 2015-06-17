-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2015 at 09:05 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sports_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `game_details`
--

CREATE TABLE IF NOT EXISTS `game_details` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `game_name` varchar(45) NOT NULL,
  `no_of_players` int(11) NOT NULL,
  `game_duration` int(11) NOT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `game_details`
--

INSERT INTO `game_details` (`game_id`, `game_name`, `no_of_players`, `game_duration`) VALUES
(1, 'Basketball', 5, 40),
(2, 'Football', 10, 105),
(3, 'Hockey', 22, 60),
(4, 'Volleyball', 10, 45);

-- --------------------------------------------------------

--
-- Table structure for table `resource_details`
--

CREATE TABLE IF NOT EXISTS `resource_details` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(45) NOT NULL,
  `resource_quantity` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `resource_game_details`
--

CREATE TABLE IF NOT EXISTS `resource_game_details` (
  `game_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`game_id`,`resource_id`),
  KEY `fk_resource_game_details_resource_details1_idx` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student_game_details`
--

CREATE TABLE IF NOT EXISTS `student_game_details` (
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`game_id`),
  KEY `fk_student_game_details_game_details1_idx` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student_game_details`
--

INSERT INTO `student_game_details` (`user_id`, `game_id`) VALUES
(1006, 1),
(1007, 1),
(1008, 1),
(1009, 1),
(1012, 1),
(1006, 2),
(1007, 2),
(1008, 2),
(1006, 3),
(1009, 3),
(1008, 4);

-- --------------------------------------------------------

--
-- Table structure for table `student_tournament_details`
--

CREATE TABLE IF NOT EXISTS `student_tournament_details` (
  `user_id` int(11) NOT NULL,
  `tournament_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`tournament_id`),
  KEY `fk_student_tournament_details_tournament_details1_idx` (`tournament_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tournament_details`
--

CREATE TABLE IF NOT EXISTS `tournament_details` (
  `tournament_id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_name` varchar(45) NOT NULL,
  `tournament_venue` varchar(45) NOT NULL,
  `tournament_start_date` date NOT NULL,
  `tournament_end_date` date NOT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY (`tournament_id`,`game_id`),
  KEY `fk_tournament_details_game_details1_idx` (`game_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tournament_details`
--

INSERT INTO `tournament_details` (`tournament_id`, `tournament_name`, `tournament_venue`, `tournament_start_date`, `tournament_end_date`, `game_id`) VALUES
(2, 'The Rose Cup', 'Vadodara', '2015-03-25', '2015-03-27', 4),
(3, 'Wildwest Championship', 'Pune', '2015-03-25', '2015-03-31', 3),
(4, 'Gold Cup', 'Hyderabad', '2015-03-25', '2015-03-25', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE IF NOT EXISTS `user_details` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_first_name` varchar(45) NOT NULL,
  `user_last_name` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_phno` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_details_user_role1_idx` (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1013 ;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`user_id`, `user_first_name`, `user_last_name`, `user_email`, `user_password`, `user_phno`, `role_id`) VALUES
(1001, 'Manali', 'Sarkar', 'manali.sarkar93@gmail.com', 'manali', '9475014587', 1),
(1006, 'Udit', 'Verma', 'uditverma28@gmail.com', 'udit', '7458102457', 3),
(1007, 'Nidhi', 'Pandya', 'nidhipandya11@gmail.com', 'nidhi', '9571475014', 3),
(1008, 'Manisha', 'Shanbhag', 'manisha@gmail.com', 'manisha', '9875014571', 3),
(1009, 'Krushna', 'Soni', 'krushna@gmail.com', 'krushna', '9687501427', 3),
(1010, 'Ankita', 'Brijwasi', 'ankita@gmail.com', 'ankita', '9574125740', 2),
(1011, 'Kriti', 'Jain', 'kriti@gmail.com', 'kriti', '9875420145', 2),
(1012, 'Divya', 'Khanani', 'divya@gmail.com', 'divya', '9685475014', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`role_id`, `role_name`) VALUES
(1, 'administrator'),
(2, 'coordinator'),
(3, 'student');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `resource_game_details`
--
ALTER TABLE `resource_game_details`
  ADD CONSTRAINT `fk_resource_game_details_game_details1` FOREIGN KEY (`game_id`) REFERENCES `game_details` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_resource_game_details_resource_details1` FOREIGN KEY (`resource_id`) REFERENCES `resource_details` (`resource_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `student_game_details`
--
ALTER TABLE `student_game_details`
  ADD CONSTRAINT `fk_student_game_details_game_details1` FOREIGN KEY (`game_id`) REFERENCES `game_details` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_student_game_details_user_details` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `student_tournament_details`
--
ALTER TABLE `student_tournament_details`
  ADD CONSTRAINT `fk_student_tournament_details_tournament_details1` FOREIGN KEY (`tournament_id`) REFERENCES `tournament_details` (`tournament_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_student_tournament_details_user_details1` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tournament_details`
--
ALTER TABLE `tournament_details`
  ADD CONSTRAINT `fk_tournament_details_game_details1` FOREIGN KEY (`game_id`) REFERENCES `game_details` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_details`
--
ALTER TABLE `user_details`
  ADD CONSTRAINT `fk_user_details_user_role1` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
