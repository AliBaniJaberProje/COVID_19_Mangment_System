-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 01, 2020 at 11:38 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `COVID_19_DB`
--

-- --------------------------------------------------------

--
-- Table structure for table `Record_COVID`
--

CREATE TABLE `Record_COVID` (
  `id` int(11) NOT NULL,
  `city` varchar(15) NOT NULL,
  `activ_case` int(11) NOT NULL,
  `healed_cases` int(11) NOT NULL,
  `Date_created` date NOT NULL,
  `Update_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Record_COVID`
--

INSERT INTO `Record_COVID` (`id`, `city`, `activ_case`, `healed_cases`, `Date_created`, `Update_date`) VALUES
(47, 'Jerusalem', 100, 100, '2020-08-04', '2020-08-01'),
(48, 'Jerusalem', 100, 100, '2020-07-04', '2020-08-03'),
(49, 'Nablues', 100, 100, '2020-08-04', '2020-08-03'),
(54, 'Tulkarm', 100, 100, '2020-07-04', '2020-08-01'),
(55, 'Tulkarm', 100, 100, '2020-08-04', '2020-08-01'),
(56, 'Ramallah', 100, 100, '2020-07-04', '2020-08-01'),
(57, 'Ramallah', 100, 100, '2020-08-04', '2020-08-01'),
(58, 'Nablues', 100, 100, '2020-07-04', '2020-08-01'),
(68, 'Hebron', 100, 100, '2020-08-04', '2020-08-01'),
(69, 'Hebron', 100, 100, '2020-07-04', '2020-08-02');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'user1', '123'),
(2, 'user2', '123'),
(3, 'user3', '123'),
(4, 'user2', '123'),
(5, 'user3', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Record_COVID`
--
ALTER TABLE `Record_COVID`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Record_COVID`
--
ALTER TABLE `Record_COVID`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
