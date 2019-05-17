-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 16, 2019 at 09:28 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musicfestivalbookings`
--
CREATE DATABASE IF NOT EXISTS `musicfestivalbookings` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `musicfestivalbookings`;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_agent`
--

DROP TABLE IF EXISTS `tbl_agent`;
CREATE TABLE IF NOT EXISTS `tbl_agent` (
  `AgentID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `PhoneNo` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`AgentID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_agent`
--

INSERT INTO `tbl_agent` (`AgentID`, `Name`, `PhoneNo`, `Email`) VALUES
(17, 'Agent 1', '', ''),
(18, 'Agent 2', '079587478111', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_band`
--

DROP TABLE IF EXISTS `tbl_band`;
CREATE TABLE IF NOT EXISTS `tbl_band` (
  `BandID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Genre` varchar(100) DEFAULT NULL,
  `Image` varchar(500) DEFAULT NULL,
  `Link` varchar(100) DEFAULT NULL,
  `AgentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BandID`),
  KEY `AgentID` (`AgentID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_band`
--

INSERT INTO `tbl_band` (`BandID`, `Name`, `Genre`, `Image`, `Link`, `AgentID`) VALUES
(18, 'Ariana Grande', 'Pop', 'ariana.jpg', 'arianagrande.com', 17),
(19, 'Justin Bieber', 'Pop', 'bieber.jpg', 'justinbiebermusic.com', 18),
(22, 'SOAD', 'Rock', 'soad.jpg', 'systemofadown.com', 18),
(23, 'Limp Bizkit', 'Rock', 'limpbizkit.jpg', 'limpbizkit.com', 17);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_booking`
--

DROP TABLE IF EXISTS `tbl_booking`;
CREATE TABLE IF NOT EXISTS `tbl_booking` (
  `BookingNo` varchar(100) NOT NULL,
  `DateOfBooking` date DEFAULT NULL,
  `NoOfSeats` int(11) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `EventID` int(10) DEFAULT NULL,
  `Paid` tinyint(1) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `TotalPrice` float DEFAULT NULL,
  PRIMARY KEY (`BookingNo`),
  KEY `CustomerID` (`CustomerID`),
  KEY `EventID` (`EventID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_event`
--

DROP TABLE IF EXISTS `tbl_event`;
CREATE TABLE IF NOT EXISTS `tbl_event` (
  `EventID` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `OrganizerID` int(11) DEFAULT NULL,
  `VenueID` int(11) DEFAULT NULL,
  `DateOfEvent` date DEFAULT NULL,
  `Image` varchar(500) DEFAULT NULL,
  `Duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`EventID`),
  KEY `OrganizerID` (`OrganizerID`),
  KEY `VenueID` (`VenueID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_event`
--

INSERT INTO `tbl_event` (`EventID`, `Name`, `Price`, `OrganizerID`, `VenueID`, `DateOfEvent`, `Image`, `Duration`) VALUES
(11, 'The Rebel Rock Festival', 50, 3, 4, '2019-05-30', 'rock.jpg', 2),
(12, 'Generic Music', 60, 2, 5, '2019-06-14', 'pop.png', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_event_band`
--

DROP TABLE IF EXISTS `tbl_event_band`;
CREATE TABLE IF NOT EXISTS `tbl_event_band` (
  `EventID` int(10) NOT NULL AUTO_INCREMENT,
  `BandID` int(11) NOT NULL,
  PRIMARY KEY (`EventID`,`BandID`),
  KEY `tbl_event_band_ibfk_2` (`BandID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_event_band`
--

INSERT INTO `tbl_event_band` (`EventID`, `BandID`) VALUES
(12, 18),
(12, 19),
(11, 22),
(11, 23);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_invoice`
--

DROP TABLE IF EXISTS `tbl_invoice`;
CREATE TABLE IF NOT EXISTS `tbl_invoice` (
  `InvNo` int(5) UNSIGNED ZEROFILL NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`InvNo`),
  KEY `CustomerID` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(5) DEFAULT NULL,
  `FName` varchar(200) DEFAULT NULL,
  `LName` varchar(200) DEFAULT NULL,
  `Address1` varchar(200) DEFAULT NULL,
  `Address2` varchar(200) DEFAULT NULL,
  `Town` varchar(200) DEFAULT NULL,
  `PostCode` varchar(200) DEFAULT NULL,
  `Username` varchar(200) DEFAULT NULL,
  `Pass` varchar(16) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `PhoneNo` varchar(200) DEFAULT NULL,
  `OrganizationName` varchar(200) DEFAULT NULL,
  `WebAddress` varchar(200) DEFAULT NULL,
  `OrgEmail` varchar(200) DEFAULT NULL,
  `PaymentMethod` varchar(200) DEFAULT NULL,
  `CardNo` bigint(16) DEFAULT NULL,
  `CVVCode` int(11) DEFAULT NULL,
  `Type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`UserID`, `Title`, `FName`, `LName`, `Address1`, `Address2`, `Town`, `PostCode`, `Username`, `Pass`, `Email`, `PhoneNo`, `OrganizationName`, `WebAddress`, `OrgEmail`, `PaymentMethod`, `CardNo`, `CVVCode`, `Type`) VALUES
(1, '', '', '', '', '', '', '', 'admin', 'admin', '', '', '', '', '', '', 1234567891234567, 123, 'admin'),
(2, 'Mr', 'George', 'Lucas', NULL, NULL, NULL, NULL, 'ev_organizer', 'lucasfestival', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'organizer'),
(3, 'Ms', 'Carrie', 'Fisher', NULL, NULL, NULL, NULL, 'leia', 'leiarulez', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'organizer'),
(10, 'Mr.', 'Luke', 'Skywalker', 'Galaxy 2', '', 'Far', 'Far Away', 'Luke', 'notmydad', 'test@gmail.com', '', '', '', '', 'On Booking', 4587124523561452, 123, 'customer'),
(11, 'Mr.', 'Darth', 'Sidious', 'Darkside 20', '', 'Deep', 'In the darkside', 'Palpatine', 'ironic', 'ironic@gmail.com', '', 'Galactic Empire', '', '', 'Monthly Invoice', 5478965841256874, 145, 'organization');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_venue`
--

DROP TABLE IF EXISTS `tbl_venue`;
CREATE TABLE IF NOT EXISTS `tbl_venue` (
  `VenueID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`VenueID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_venue`
--

INSERT INTO `tbl_venue` (`VenueID`, `Name`, `Address`, `Capacity`) VALUES
(4, 'Tattoine', 'in a galaxy', 2000),
(5, 'O2', 'London', 1000);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_band`
--
ALTER TABLE `tbl_band`
  ADD CONSTRAINT `tbl_band_ibfk_1` FOREIGN KEY (`AgentID`) REFERENCES `tbl_agent` (`AgentID`);

--
-- Constraints for table `tbl_booking`
--
ALTER TABLE `tbl_booking`
  ADD CONSTRAINT `tbl_booking_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `tbl_user` (`UserID`),
  ADD CONSTRAINT `tbl_booking_ibfk_3` FOREIGN KEY (`EventID`) REFERENCES `tbl_event` (`EventID`);

--
-- Constraints for table `tbl_event`
--
ALTER TABLE `tbl_event`
  ADD CONSTRAINT `tbl_event_ibfk_1` FOREIGN KEY (`OrganizerID`) REFERENCES `tbl_user` (`UserID`),
  ADD CONSTRAINT `tbl_event_ibfk_2` FOREIGN KEY (`OrganizerID`) REFERENCES `tbl_user` (`UserID`),
  ADD CONSTRAINT `tbl_event_ibfk_3` FOREIGN KEY (`OrganizerID`) REFERENCES `tbl_user` (`UserID`),
  ADD CONSTRAINT `tbl_event_ibfk_4` FOREIGN KEY (`VenueID`) REFERENCES `tbl_venue` (`VenueID`);

--
-- Constraints for table `tbl_event_band`
--
ALTER TABLE `tbl_event_band`
  ADD CONSTRAINT `tbl_event_band_ibfk_1` FOREIGN KEY (`EventID`) REFERENCES `tbl_event` (`EventID`),
  ADD CONSTRAINT `tbl_event_band_ibfk_2` FOREIGN KEY (`BandID`) REFERENCES `tbl_band` (`BandID`);

--
-- Constraints for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  ADD CONSTRAINT `tbl_invoice_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `tbl_user` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
