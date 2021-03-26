-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2021 at 11:27 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ujiantiga`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetDepartment` (INOUT `depar` VARCHAR(20), INOUT `jumlah_pegawai` INT)  BEGIN

    SELECT department, COUNT(worker_id) AS jumlah_pegawai
 	FROM tbl_worker
	WHERE department = depar;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bonus`
--

CREATE TABLE `tbl_bonus` (
  `worker_ref_id` int(11) NOT NULL,
  `bonus_date` datetime NOT NULL,
  `bonus_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_bonus`
--

INSERT INTO `tbl_bonus` (`worker_ref_id`, `bonus_date`, `bonus_amount`) VALUES
(1, '2021-03-24 06:23:33', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_title`
--

CREATE TABLE `tbl_title` (
  `worker_ref_id` int(11) NOT NULL,
  `worker_title` varchar(20) NOT NULL,
  `affected_from` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_title`
--

INSERT INTO `tbl_title` (`worker_ref_id`, `worker_title`, `affected_from`) VALUES
(1, 'Admin', '2021-03-24 06:23:33');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_worker`
--

CREATE TABLE `tbl_worker` (
  `worker_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `salary` double NOT NULL,
  `joining_date` datetime NOT NULL,
  `department` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_worker`
--

INSERT INTO `tbl_worker` (`worker_id`, `first_name`, `last_name`, `salary`, `joining_date`, `department`) VALUES
(1, 'Umi', 'Atiyah', 9090909, '2021-03-24 06:23:33', 'HR'),
(2, 'Wawan', 'Suryawan', 4000000, '2021-03-24 06:23:33', 'Account'),
(3, 'Agus', 'Salim', 2000000, '2021-03-24 06:23:33', 'HR'),
(4, 'Irene', 'Ireng', 1000000, '2021-03-24 06:23:33', 'Admin'),
(5, 'Dita', 'Ditanya', 1000000, '2021-03-24 06:23:33', 'Admin'),
(6, 'Ikbal', 'Balungan', 1000000, '2021-03-24 06:23:33', 'HR'),
(7, 'Januar', 'Januari', 3000000, '2021-03-24 06:23:33', 'Account'),
(8, 'Dewi', 'Broto', 1000000, '2021-03-24 06:23:33', 'Account'),
(9, 'Sumi', 'Sumanto', 5000000, '2021-03-24 06:23:33', 'Admin'),
(10, 'Deri', 'Daryanto', 1000000, '2021-03-24 06:23:33', 'HR');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_worker`
--
ALTER TABLE `tbl_worker`
  ADD PRIMARY KEY (`worker_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
