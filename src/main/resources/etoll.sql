-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 22 Feb 2016 pada 03.44
-- Versi Server: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `etoll`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `activities`
--

CREATE TABLE `activities` (
  `id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `vehicle_id` int(11) NOT NULL,
  `source_toll_id` int(11) NOT NULL,
  `dest_toll_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `activities`
--

INSERT INTO `activities` (`id`, `time`, `vehicle_id`, `source_toll_id`, `dest_toll_id`, `price`, `user_id`) VALUES
(4, '2016-02-19 16:21:07', 5, 1, 1, 1000, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prices`
--

CREATE TABLE `prices` (
  `id` int(11) NOT NULL,
  `source_toll_id` int(11) NOT NULL,
  `dest_toll_id` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `staffs`
--

CREATE TABLE `staffs` (
  `id` int(11) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `toll_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `staffs` ADD COLUMN `token` VARCHAR (255) DEFAULT NULL;

--
-- Dumping data untuk tabel `staffs`
--

INSERT INTO `staffs` (`id`, `email`, `password`, `toll_id`) VALUES
(1, 'testing@gmail.com', 'testing', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tolls`
--

CREATE TABLE `tolls` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tolls`
--

INSERT INTO `tolls` (`id`, `name`) VALUES
(1, 'tol pasteur');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `msisdn` varchar(24) NOT NULL,
  `credentials` varchar(64) NOT NULL,
  `token` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `msisdn`, `credentials`, `token`) VALUES
(1, '081234567890', '', ''),
(2, '085728165503', '', ''),
(3, '085712345678', '', ''),
(4, '085712345679', '', ''),
(5, '085712345677', '123456', 'token');

-- --------------------------------------------------------

--
-- Struktur dari tabel `vehicles`
--

CREATE TABLE `vehicles` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `plate_no` varchar(16) NOT NULL,
  `photo_url` varchar(128) DEFAULT NULL,
  `msisdn` varchar(24) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `vehicles`
--

INSERT INTO `vehicles` (`id`, `name`, `plate_no`, `photo_url`, `msisdn`) VALUES
(5, 'abraham', 'AB1234CD', NULL, '081234567890');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `vehicle_id` (`vehicle_id`,`source_toll_id`,`dest_toll_id`),
  ADD UNIQUE KEY `vehicle_id_2` (`vehicle_id`,`source_toll_id`,`dest_toll_id`),
  ADD UNIQUE KEY `vehicle_id_4` (`vehicle_id`),
  ADD UNIQUE KEY `user_id` (`user_id`),
  ADD KEY `vehicle_id_3` (`vehicle_id`,`source_toll_id`,`dest_toll_id`),
  ADD KEY `source_toll_id` (`source_toll_id`),
  ADD KEY `dest_toll_id` (`dest_toll_id`);

--
-- Indexes for table `prices`
--
ALTER TABLE `prices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `souce_toll_id` (`source_toll_id`),
  ADD KEY `dest_toll_id` (`dest_toll_id`);

--
-- Indexes for table `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `toll_id` (`toll_id`);

--
-- Indexes for table `tolls`
--
ALTER TABLE `tolls`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `msisdn` (`msisdn`);

--
-- Indexes for table `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `msisdn` (`msisdn`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activities`
--
ALTER TABLE `activities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `prices`
--
ALTER TABLE `prices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `staffs`
--
ALTER TABLE `staffs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tolls`
--
ALTER TABLE `tolls`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `activities`
--
ALTER TABLE `activities`
  ADD CONSTRAINT `activities_ibfk_2` FOREIGN KEY (`source_toll_id`) REFERENCES `tolls` (`id`),
  ADD CONSTRAINT `activities_ibfk_3` FOREIGN KEY (`dest_toll_id`) REFERENCES `tolls` (`id`),
  ADD CONSTRAINT `activities_ibfk_4` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`),
  ADD CONSTRAINT `activities_ibfk_5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ketidakleluasaan untuk tabel `prices`
--
ALTER TABLE `prices`
  ADD CONSTRAINT `prices_ibfk_1` FOREIGN KEY (`source_toll_id`) REFERENCES `tolls` (`id`),
  ADD CONSTRAINT `prices_ibfk_2` FOREIGN KEY (`dest_toll_id`) REFERENCES `tolls` (`id`);

--
-- Ketidakleluasaan untuk tabel `staffs`
--
ALTER TABLE `staffs`
  ADD CONSTRAINT `staffs_ibfk_1` FOREIGN KEY (`toll_id`) REFERENCES `tolls` (`id`);

--
-- Ketidakleluasaan untuk tabel `vehicles`
--
ALTER TABLE `vehicles`
  ADD CONSTRAINT `vehicles_ibfk_1` FOREIGN KEY (`msisdn`) REFERENCES `users` (`msisdn`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
