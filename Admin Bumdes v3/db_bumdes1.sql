-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2022 at 06:12 AM
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
-- Database: `db_bumdes1`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_fullname` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`, `user_fullname`) VALUES
('admin', 'prakoso2', 'Aliffrianto Yudha Prakoso'),
('operator', '123456', 'Boy Dymas Hidayat');

-- --------------------------------------------------------

--
-- Table structure for table `data_admin`
--

CREATE TABLE `data_admin` (
  `id` varchar(15) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `telp` int(13) NOT NULL,
  `alamat` text NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_admin`
--

INSERT INTO `data_admin` (`id`, `nama`, `telp`, `alamat`, `username`, `password`) VALUES
('01', 'boy', 85231, 'bondowoso', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_brg`
--

CREATE TABLE `data_brg` (
  `id_brg` varchar(50) NOT NULL,
  `barang` varchar(30) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `hg_beli` int(30) NOT NULL,
  `hg_jual` int(30) NOT NULL,
  `jml_stok` int(11) NOT NULL,
  `deskripsi` text NOT NULL,
  `gambar` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_brg`
--

INSERT INTO `data_brg` (`id_brg`, `barang`, `tgl_masuk`, `hg_beli`, `hg_jual`, `jml_stok`, `deskripsi`, `gambar`) VALUES
('BRG-0122851', 'Asbak Ukir', '2022-01-01', 10000, 15000, 0, 'Cocok buat perokok', '973-ceramic-jar-6585077_1920.jpg'),
('BRG-1221726', 'Miniatur Sepeda', '2021-12-29', 59000, 65000, 98, 'Kerajinan yang satu ini merupakan kerjainan yang sangat unik dan khas. Berbahan dasar kayu membuatnya terlihat nyentrik dan menonjolkan kesan estetik. Proses pembuatannya juga memerlukan ketekunan dan ketelitian tingkat tinggi.', '540-miniatur_sepeda.jpg'),
('BRG-1221744', 'Miniatur Becak', '2021-12-27', 68000, 79000, 14, 'Miniatur Becak ini merupakan salah satu hiasan unik yang terbuat dari kayu. Pembuatan kerajinan ini memiliki tingkat kesulitan yang cukup tinggi, karena pengrajin harus membuat becak tiruan semirip mungkin dan sedetail mungkin seperti becak sungguhan. ', '554-miniatur_becak.jpg'),
('BRG-1221816', 'Tempat Pensil Stik Es Krim', '2021-12-29', 25000, 32000, 27, 'Tempat pensil ini merupakan kerajinan berbahan dasar stik es krim bekas. ', '829-tempat_pensil.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `data_klr`
--

CREATE TABLE `data_klr` (
  `id` int(10) NOT NULL,
  `id_transaksi` varchar(100) NOT NULL,
  `tgl_keluar` date NOT NULL,
  `id_brg` int(10) NOT NULL,
  `barang` varchar(50) NOT NULL,
  `jml_keluar` int(10) NOT NULL,
  `total_hrg` int(11) NOT NULL,
  `penerima` varchar(35) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `data_mitra`
--

CREATE TABLE `data_mitra` (
  `id` int(5) NOT NULL,
  `kd_mitra` varchar(10) NOT NULL,
  `nama_mitra` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_tlp` varchar(13) NOT NULL,
  `tgl_gabung` date NOT NULL,
  `barang` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_mitra`
--

INSERT INTO `data_mitra` (`id`, `kd_mitra`, `nama_mitra`, `alamat`, `no_tlp`, `tgl_gabung`, `barang`) VALUES
(26, 'MTR-012261', 'Jaya Abadi', 'Patrang', '85731379198', '2022-01-01', 'Miniatur Sepeda');

-- --------------------------------------------------------

--
-- Table structure for table `data_msk`
--

CREATE TABLE `data_msk` (
  `id` int(10) NOT NULL,
  `id_transaksi` varchar(100) NOT NULL,
  `tgl_msk` date NOT NULL,
  `id_brg` varchar(50) NOT NULL,
  `barang` varchar(50) NOT NULL,
  `pengirim` varchar(50) NOT NULL,
  `hg_beli` int(11) NOT NULL,
  `jml_masuk` int(10) NOT NULL,
  `total_hrg` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `data_ongkir`
--

CREATE TABLE `data_ongkir` (
  `id_ongkir` int(11) NOT NULL,
  `jenis_ongkir` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ongkos_kirim` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `data_ongkir`
--

INSERT INTO `data_ongkir` (`id_ongkir`, `jenis_ongkir`, `ongkos_kirim`) VALUES
(1, 'Reguler', 30000),
(2, 'Express', 48000);

-- --------------------------------------------------------

--
-- Table structure for table `data_reseller`
--

CREATE TABLE `data_reseller` (
  `id` int(5) NOT NULL,
  `kd_reseller` varchar(10) NOT NULL,
  `nama_reseller` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_tlp` varchar(13) NOT NULL,
  `tgl_gabung` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `item_preorder`
--

CREATE TABLE `item_preorder` (
  `id_item_preorder` int(11) NOT NULL,
  `id_preorder` varchar(100) NOT NULL,
  `id_brg` varchar(100) NOT NULL,
  `qty` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `id_keranjang` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_barang` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `qty` int(3) NOT NULL,
  `subtotal` int(11) NOT NULL,
  `jenis` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `preorder`
--

CREATE TABLE `preorder` (
  `id_preorder` varchar(100) NOT NULL,
  `tgl_preorder` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_user` int(11) NOT NULL,
  `penerima` varchar(32) NOT NULL,
  `alamat` varchar(99) NOT NULL,
  `no_telp` char(15) NOT NULL,
  `id_ongkir` int(11) NOT NULL,
  `total_preorder` int(11) NOT NULL,
  `status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `report_klr`
--

CREATE TABLE `report_klr` (
  `id` int(11) NOT NULL,
  `id_transaksi` varchar(100) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `penerima` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `total_transaksi` int(11) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report_klr`
--

INSERT INTO `report_klr` (`id`, `id_transaksi`, `tgl_transaksi`, `penerima`, `alamat`, `total_transaksi`, `status`) VALUES
(3, 'TRK-0122002', '2022-01-01', 'Yoga ', 'Jombang', 159000, 'Selesai');

-- --------------------------------------------------------

--
-- Table structure for table `report_msk`
--

CREATE TABLE `report_msk` (
  `id` int(11) NOT NULL,
  `id_transaksi` varchar(50) NOT NULL,
  `tgl_msk` date NOT NULL,
  `id_brg` varchar(50) NOT NULL,
  `barang` varchar(50) NOT NULL,
  `pengirim` varchar(50) NOT NULL,
  `hg_beli` int(11) NOT NULL,
  `jml_masuk` int(10) NOT NULL,
  `total_hrg` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report_msk`
--

INSERT INTO `report_msk` (`id`, `id_transaksi`, `tgl_msk`, `id_brg`, `barang`, `pengirim`, `hg_beli`, `jml_masuk`, `total_hrg`) VALUES
(4, 'TRM-0122001', '2022-01-01', 'BRG-1221816', 'Tempat Pensil Stik Es Krim', 'Jaya Abadi', 25000, 20, 500000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tgl_transaksi` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_user` int(11) NOT NULL,
  `penerima` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `alamat` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `no_telp` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `id_ongkir` int(11) NOT NULL,
  `resi` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '-',
  `total_transaksi` int(11) NOT NULL,
  `status` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `bukti_tf` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '-'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_produk`
--

CREATE TABLE `transaksi_produk` (
  `id_transaksi_produk` int(11) NOT NULL,
  `id_transaksi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `id_brg` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `qty` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `fullname` varchar(32) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL DEFAULT 'Not set',
  `username` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `no_telp` char(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  `foto_profil` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `fullname`, `jenis_kelamin`, `username`, `email`, `no_telp`, `password`, `foto_profil`) VALUES
(1, 'Yoga Andrian', 'Not set', 'yogandrn', 'yoga@gmail.com', '085667454320', '1234', 'yoga.jpg'),
(2, 'budiman', 'Not set', 'budi', 'budi@gmail.com', '089778760903', '0000', 'default_user.png'),
(5, 'New User 2', 'Not set', 'user2', 'user2@gmail.com', '08568400176', '1234', 'default_user.png'),
(8, 'Budianto', 'Not set', 'budianto', 'budianto@gmail.com', '097357288', 'budiku', 'default_user.png'),
(10, 'Slamet ', 'Not set', 'slamet', 'slamet@gmail.com', '075986678', 'anjay', 'default_user.png'),
(11, 'Ahmad Subandi', 'Laki-laki', 'ahmadsubandi', 'ahmadsubandi@gmail.com', '085852852258', '$2y$10$W3cK5R4qsPzoODQ8K7NKOu5CUNqvoQ0nArwuxWvM9nwlgQ6eaVQsi', '61c75160a480a.jpeg'),
(12, 'Budi Slamet', 'Laki-laki', 'budislamet', 'budislamet@gmail.com', '08264391986', '$2y$10$789m97nD2Jb8b0vaKWhEF.5C5O.W2I4zOYLZTZGdz1s.pwtVLYlKu', 'default_user.png'),
(13, 'Bambang Agus', 'Not set', 'bmbgagus', 'bambangagus@gmail.com', '085963123666', '$2y$10$FDjjnubBKg8TZFT7.dNJMeVXA1hutfloFb28fEEAugnh4HG2T/Zm.', 'default_user.png'),
(14, 'User Satu', 'Laki-laki', 'user1', 'user1@gmail.com', '08596664976', '$2y$10$7qpY/eMTV56VZAcg1Z2dReSUy4nxSE1aIAmVQ1Cdj7ddaRIyO8hR2', 'default_user.png'),
(15, 'User Dua', 'Not set', 'user21', 'user21@gmail.com', '085943494546', '$2y$10$XKxPlWaqTLsuJDik6WsZVuk3mKslwllPjnKhl2pYqJVyUf.DAGL9y', 'default_user.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `data_brg`
--
ALTER TABLE `data_brg`
  ADD PRIMARY KEY (`id_brg`);

--
-- Indexes for table `data_klr`
--
ALTER TABLE `data_klr`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_mitra`
--
ALTER TABLE `data_mitra`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_msk`
--
ALTER TABLE `data_msk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_ongkir`
--
ALTER TABLE `data_ongkir`
  ADD PRIMARY KEY (`id_ongkir`);

--
-- Indexes for table `data_reseller`
--
ALTER TABLE `data_reseller`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item_preorder`
--
ALTER TABLE `item_preorder`
  ADD PRIMARY KEY (`id_item_preorder`);

--
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`id_keranjang`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `preorder`
--
ALTER TABLE `preorder`
  ADD PRIMARY KEY (`id_preorder`);

--
-- Indexes for table `report_klr`
--
ALTER TABLE `report_klr`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `report_msk`
--
ALTER TABLE `report_msk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_ongkir` (`id_ongkir`);

--
-- Indexes for table `transaksi_produk`
--
ALTER TABLE `transaksi_produk`
  ADD PRIMARY KEY (`id_transaksi_produk`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_klr`
--
ALTER TABLE `data_klr`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `data_mitra`
--
ALTER TABLE `data_mitra`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `data_msk`
--
ALTER TABLE `data_msk`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `data_ongkir`
--
ALTER TABLE `data_ongkir`
  MODIFY `id_ongkir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `data_reseller`
--
ALTER TABLE `data_reseller`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `item_preorder`
--
ALTER TABLE `item_preorder`
  MODIFY `id_item_preorder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `id_keranjang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `report_klr`
--
ALTER TABLE `report_klr`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `report_msk`
--
ALTER TABLE `report_msk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transaksi_produk`
--
ALTER TABLE `transaksi_produk`
  MODIFY `id_transaksi_produk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD CONSTRAINT `keranjang_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_ongkir`) REFERENCES `data_ongkir` (`id_ongkir`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
