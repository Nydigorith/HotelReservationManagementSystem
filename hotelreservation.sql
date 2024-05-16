-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2022 at 11:06 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotelreservation`
--

-- --------------------------------------------------------

--
-- Table structure for table `employeeinformation`
--

CREATE TABLE `employeeinformation` (
  `employee_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `attempt` int(11) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employeeinformation`
--

INSERT INTO `employeeinformation` (`employee_id`, `username`, `firstName`, `lastName`, `email`, `address`, `phone`, `password`, `question`, `answer`, `attempt`, `type`) VALUES
(1, 'administrator', 'administrator', '', 'administrator@gmail.com', '', '', '*9F880DA1329B4B497F247AA25727CCDD5F4DD2E0', 'administrator', '*9F880DA1329B4B497F247AA25727CCDD5F4DD2E0', 0, 'Administrator'),
(22, 'hya', 'Hyacinth', 'Chua', 'hyacinth@gmail.com', '', '', '*2B872BEE938166B78CDB8BBBBB731293B4FC9BA9', 'What is your closest sibling’s name?', '*4956FF2222BB833C72B2A9E8DA3B24CFE1F8E26C', 0, 'Staff'),
(23, 'mark', 'Mark', 'Cristobal', 'mark@gmail.com', 'Bocaue', '', '*11BA3C89082889E5643949A336E3B178B0008FDB', 'What street did you grow up on?', '*E6ACCEDB2495496B191ED488F598F04239C85E73', 0, 'Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `guestinformation`
--

CREATE TABLE `guestinformation` (
  `guest_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `attempt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guestinformation`
--

INSERT INTO `guestinformation` (`guest_id`, `username`, `firstName`, `lastName`, `email`, `address`, `phone`, `password`, `question`, `answer`, `attempt`) VALUES
(2, 'angel', 'Angel', 'Lasao', 'angel@mail.com', '', '09546553457', '*6BB99672D067D3D2904C7574E6580FD286DB54AB', 'What is your favorite sports team?', '*FA0BB9CA9329D4F3FD4D91F116560BB5B4E0BADD', 0),
(4, 'cristi', 'Cristina', 'Mauricio', 'cristina@gmail.com', 'Bulacan', '09097878676', '*49A302C6C06306CD0222AE14F249009B301EF011', 'What is your closest sibling’s name?', '*3BE0209DAABDFFAC4E10034C5AE499E1872BCBD0', 0),
(6, 'neil', 'Neil', 'Valentino', 'neil@outlook.com', 'Malolos', '08967979568', '*2C300508D578EC470FAB87006BF69BFCB038617F', 'What is your closest sibling’s name?', '*B424FA14F54120D85DBF00E4B9FF852BFAF0EB5E', 0),
(8, 'daniel', 'Daniel', 'Sarne', 'daniel@aol.com', 'Guiguinto', '09342343333', '*B9BA35C4A6D72DFFF596F9B11C162EECCB767D99', 'What is your closest sibling’s name?', '*3C06A471CB6048FCCCF5DB904D8F5BE49F1C7585', 0),
(10, 'jasper', 'Jasper', 'Roxas', 'jasper@yahoo.com', '', '', '*CCDAD49E70A1CD9A8503610C7CF5895D5E2651C9', 'What is your favorite sports team?', '*B2ACB9267346C0315976AB4125C3DB823160C85F', 0),
(12, 'aaron', 'Aaron', 'Gumasing', 'aaron@outlook.com', '', '09876543211', '*EBD06506A96D197546318B8AA5F3F34B1708BF61', 'What is your closest sibling’s name?', '*403A228385AA4CE72AA93761C9082B12957A8F64', 0),
(14, 'milo', 'Milo', 'Pascual', 'milo@gmail.com', 'Guiguinto', '09878566455', '*7D945296A696AAE36D2177FB65693248BF6B09D6', 'What is your favorite food?', '*F43F773BEA23FA87F0F922134993359D8EB20136', 0),
(16, 'shiela', 'Shiela', 'Juno', 'shiela@gmail.com', 'Taal, Bocaue', '09765467665', '*1A0FFF6419498F1BE9D7688DC1CB17B8676903DF', 'What is your favorite food?', '*8F5D447773EAFB609473B1611D4F8FA2F60865A8', 0),
(18, 'migui', 'Migui', 'Galoza', 'migui@aol.com', '', '', '*2766CBC333F884A2E78C03C28FD68031FFD240FF', 'What is your closest sibling’s name?', '*24321D7BD840E056BBED76E6086AD0DE8166C2C1', 0),
(20, 'alfred', 'Alfred', 'Ecal', 'alfred@gmail.com', '', '09689785695', '*6872419C321FFEA34458B5FD0BE5D22C610478B6', 'What is your favorite sports team?', '*0A3FC730C575323703277D26307342D6BB257DDC', 0),
(22, 'arlan', 'Arlan', 'Barja', 'arlan@gmail.com', 'Batia', '', '*715871A127F93ED1E35C458D31DC946DA12A841B', 'What is your favorite sports team?', '*59218A3AE1C5ECD3215E470E1BD1B4AFD4F414D7', 0),
(24, 'ches', 'Chester', 'De Guzman', 'chester@gmail.com', 'Batia, Bocaue, Bulacan', '09392049932', '*5C1D6D00BEF627B984921564976DE4AB64E8CB4D', 'What is your closest sibling’s name?', '*2CA662E61EBD10D5552417DB6A10688B5FEA164E', 0);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reserve_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL,
  `roomNo` varchar(10) NOT NULL,
  `checkIn` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reserve_id`, `person_id`, `roomNo`, `checkIn`, `status`) VALUES
(87, 2, 'R17', '2022-05-31', 'Pending'),
(88, 8, 'R3', '2022-05-31', 'Pending'),
(89, 10, 'R1', '2022-05-24', 'Pending'),
(90, 5, 'R10', '2022-05-19', 'Reserved'),
(92, 16, 'R5', '2022-05-31', 'Reserved'),
(96, 7, 'R17', '2022-05-31', 'Reserved'),
(97, 24, 'R6', '2022-05-29', 'Reserved'),
(98, 24, 'R7', '2022-05-29', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomNo` varchar(10) NOT NULL,
  `roomType` varchar(200) NOT NULL,
  `bed` varchar(200) NOT NULL,
  `price` int(11) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomNo`, `roomType`, `bed`, `price`, `status`) VALUES
('R1', 'AC', 'Single', 1000, 'Occupied'),
('R10', 'Non-AC', 'Single', 600, 'Reserved'),
('R11', 'Non-AC', 'Double', 1200, 'Available'),
('R12', 'Non-AC', 'Triple', 1800, 'Available'),
('R13', 'AC', 'Double', 1000, 'Available'),
('R14', 'AC', 'Double', 2000, 'Available'),
('R15', 'AC', 'Triple', 3000, 'Available'),
('R16', 'Non-AC', 'Single', 600, 'Occupied'),
('R17', 'Non-AC', 'Double', 1200, 'Reserved'),
('R18', 'AC', 'Triple', 3000, 'Occupied'),
('R2', 'AC', 'Double', 2000, 'Available'),
('R3', 'AC', 'Triple', 3000, 'Available'),
('R4', 'Non-AC', 'Single', 600, 'Available'),
('R5', 'Non-AC', 'Double', 1200, 'Reserved'),
('R6', 'AC', 'Single', 1000, 'Reserved'),
('R7', 'AC', 'Single', 1000, 'Available'),
('R8', 'AC', 'Double', 2000, 'Available'),
('R9', 'AC', 'Triple', 3000, 'Occupied');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL,
  `checkIn` varchar(50) NOT NULL,
  `roomNo` varchar(10) NOT NULL,
  `numberOfDaysStay` int(11) DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  `checkout` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `person_id`, `checkIn`, `roomNo`, `numberOfDaysStay`, `totalAmount`, `checkout`) VALUES
(30, 3, '2022-05-28', 'R3', 3, 9000, '2022-05-31'),
(31, 12, '2022-05-31', 'R9', NULL, NULL, NULL),
(36, 12, '2022-05-29', 'R12', 2, 3600, '2022-05-31'),
(37, 9, '2022-05-29', 'R16', NULL, NULL, NULL),
(38, 11, '2022-05-31', 'R1', NULL, NULL, NULL),
(39, 22, '2022-05-31', 'R18', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `visitorinformation`
--

CREATE TABLE `visitorinformation` (
  `visitor_id` int(11) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `visitorinformation`
--

INSERT INTO `visitorinformation` (`visitor_id`, `firstName`, `lastName`, `email`, `address`, `phone`) VALUES
(3, 'Ivy', 'Dosal', 'ivy@gmail.com', 'Bocaue', '09786756464'),
(5, 'Jethro', ' Pojas', 'jethro@yahoo.com', '', ''),
(7, 'Sheldon', 'Cooper', '', '', ''),
(9, 'Clarence', 'Salonga', '', 'Bocaue', ''),
(11, 'Darwin', 'Ramos', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employeeinformation`
--
ALTER TABLE `employeeinformation`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `guestinformation`
--
ALTER TABLE `guestinformation`
  ADD PRIMARY KEY (`guest_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reserve_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomNo`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `visitorinformation`
--
ALTER TABLE `visitorinformation`
  ADD PRIMARY KEY (`visitor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employeeinformation`
--
ALTER TABLE `employeeinformation`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `guestinformation`
--
ALTER TABLE `guestinformation`
  MODIFY `guest_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reserve_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
