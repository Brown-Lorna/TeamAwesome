-- phpMyAdmin SQL Dump
-- version 4.0.10.10
-- http://www.phpmyadmin.net
--
-- Host: 127.10.145.2:3306
-- Generation Time: Dec 01, 2015 at 02:14 AM
-- Server version: 5.5.45
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ancestor`
--
CREATE DATABASE ancestor;

USE ancestor;

-- --------------------------------------------------------

--
-- Table structure for table `childOf`
--

CREATE TABLE IF NOT EXISTS `childOf` (
  `child_id` int(11) NOT NULL,
  `mother_id` int(11) DEFAULT NULL,
  `father_id` int(11) DEFAULT NULL,
  UNIQUE KEY `child_id` (`child_id`),
  KEY `mother_id` (`mother_id`,`father_id`),
  KEY `father_id` (`father_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `childOf`
--

INSERT INTO `childOf` (`child_id`, `mother_id`, `father_id`) VALUES
(2, NULL, 4),
(3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `first_name`, `last_name`, `birthday`) VALUES
(1, 'John', 'Smith', '1915-05-12'),
(2, 'Suzan', 'Williams', '1920-04-20'),
(3, 'Robert', 'Smith', '1935-11-18'),
(4, 'Paul', 'Williams', '1890-11-14');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `childOf`
--
ALTER TABLE `childOf`
  ADD CONSTRAINT `childOf_ibfk_1` FOREIGN KEY (`child_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `childOf_ibfk_2` FOREIGN KEY (`mother_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `childOf_ibfk_3` FOREIGN KEY (`father_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
