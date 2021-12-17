-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2021 at 02:35 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_bumdes`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_brg`
--

CREATE TABLE `data_brg` (
  `id` int(5) NOT NULL,
  `nama_brg` text NOT NULL,
  `jenis_brg` text NOT NULL,
  `harga_brg` varchar(50) NOT NULL,
  `stok_brg` int(5) NOT NULL,
  `deskripsi_brg` varchar(100) NOT NULL,
  `gambar_brg` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_brg`
--

INSERT INTO `data_brg` (`id`, `nama_brg`, `jenis_brg`, `harga_brg`, `stok_brg`, `deskripsi_brg`, `gambar_brg`) VALUES
(6, '', 'Terapan', '150000', 50, 'Kayu Jati', '856-Wireframe Barang(1).png'),
(7, 'Bulpoin', 'Terapan', '12000', 50, 'Standart', '894-17545.jpg'),
(8, 'Pensil', 'Terapan', '1200', 150, 'A2', 'brg-1639537969.jpeg'),
(9, 'Kayu Ukir', 'Terapan', '150000', 40, 'Kayu', 'brg-1639554379.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `data_mitra`
--

CREATE TABLE `data_mitra` (
  `id` int(5) NOT NULL,
  `nama_mitra` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_tlp` varchar(13) NOT NULL,
  `tgl_gabung` date NOT NULL,
  `barang` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_mitra`
--

INSERT INTO `data_mitra` (`id`, `nama_mitra`, `alamat`, `no_tlp`, `tgl_gabung`, `barang`) VALUES
(12, 'Lingkar Jati', 'Patrang', '085731379198', '2021-12-01', 'Kursi'),
(13, 'Komiku', 'Kaliurang', '0854728365181', '2021-12-02', 'bola'),
(14, 'Venerable', 'Sumbersari', '087654789123', '2021-12-10', 'Gajah'),
(15, 'Live As', 'Mangli', '089765123890', '2021-12-08', 'Kain'),
(16, 'Mount', 'Krajan Timur', '089789543678', '2021-12-04', 'air'),
(17, 'Asura', 'Balung Wetan', '085678908123', '2021-12-12', 'sapu'),
(18, 'Shall', 'Semeru', '085731379198', '2021-12-01', 'Kursi'),
(19, 'Spirit', 'Patrang', '085731379198', '2021-12-07', 'bola'),
(20, 'Scribe', 'Kaliurang', '085731379198', '2021-12-05', 'Kursi'),
(21, 'The Way', 'Sumbersari', '085731379198', '2021-12-08', 'bola'),
(22, 'ShenWang', 'Mojokerto', '085731379198', '2021-12-06', 'Kursi');

-- --------------------------------------------------------

--
-- Table structure for table `data_reseller`
--

CREATE TABLE `data_reseller` (
  `id` int(5) NOT NULL,
  `nama_reseller` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_tlp` varchar(13) NOT NULL,
  `tgl_gabung` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_reseller`
--

INSERT INTO `data_reseller` (`id`, `nama_reseller`, `alamat`, `no_tlp`, `tgl_gabung`) VALUES
(2, 'Dwiki Gaming', 'Pasuruan', '085731379198', '2021-11-26');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_fullname` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `user_fullname`) VALUES
('admin', 'prakoso2', 'Aliffrianto Yudha Prakoso'),
('operator', '123456', 'Boy Dymas Hidayat');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_brg`
--
ALTER TABLE `data_brg`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_mitra`
--
ALTER TABLE `data_mitra`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_reseller`
--
ALTER TABLE `data_reseller`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_brg`
--
ALTER TABLE `data_brg`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `data_mitra`
--
ALTER TABLE `data_mitra`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `data_reseller`
--
ALTER TABLE `data_reseller`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
