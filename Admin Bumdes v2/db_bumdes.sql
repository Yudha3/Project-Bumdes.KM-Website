-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2021 at 07:43 PM
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
  `id_brg` int(5) NOT NULL,
  `barang` varchar(30) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `hg_beli` int(30) NOT NULL,
  `hg_jual` int(30) NOT NULL,
  `jml_stok` int(5) NOT NULL,
  `deskripsi` text NOT NULL,
  `gambar` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_brg`
--

INSERT INTO `data_brg` (`id_brg`, `barang`, `tgl_masuk`, `hg_beli`, `hg_jual`, `jml_stok`, `deskripsi`, `gambar`) VALUES
(1, 'Asbak Domba', '2021-12-02', 39500, 59000, 100, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor iure, repudiandae eius corrupti ipsum esse atque itaque eum quos cupiditate provident, mollitia porro commodi ex odit voluptatem assumenda in illo quasi doloremque,', '253-ceramic-jar-6585077_1920.jpg'),
(2, 'Kerajinan Tas Anyaman', '2021-11-27', 58000, 70000, 212, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor iure, repudiandae eius corrupti ipsum esse atque itaque eum quos cupiditate provident, mollitia porro commodi ex odit voluptatem assumenda in illo quasi doloremque, laboriosam ea iusto nisi! Asperiores, impedit expedita labore fugit exercitationem quam maxime! Numquam, error repellendus nihil culpa explicabo saepe corporis eveniet beatae fugiat deleniti!', '735-bag-3688900_1920.jpg'),
(5, 'Lampu Bambu', '2021-12-01', 45000, 60000, 520, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor iure, repudiandae eius corrupti ipsum esse atque itaque eum quos cupiditate provident, mollitia porro commodi ex odit voluptatem assumenda in illo quasi doloremque, laboriosam ea iusto nisi! Asperiores, impedit expedita labore fugit exercitationem quam maxime! Numquam, error repellendus nihil culpa explicabo saepe corporis eveniet beatae fugiat deleniti!', '571-candle-1021137_1920.jpg'),
(6, 'Angklung', '2021-11-30', 125000, 160000, 615, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor iure, repudiandae eius corrupti ipsum esse atque itaque eum quos cupiditate provident, mollitia porro commodi ex odit voluptatem assumenda in illo quasi doloremque, laboriosam ea iusto nisi! Asperiores, impedit expedita labore fugit exercitationem quam maxime! Numquam, error repellendus nihil culpa explicabo saepe corporis eveniet beatae fugiat deleniti!', '473-angklung-376513_1920.jpg'),
(7, 'Vas Bunga', '2021-11-29', 65000, 80000, 524, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor iure, repudiandae eius corrupti ipsum esse atque itaque eum quos cupiditate provident, mollitia porro commodi ex odit voluptatem assumenda in illo quasi doloremque,', '30-flower-3175428_1280.jpg'),
(8, 'Lampu Kayu Burung Hantu', '2021-12-02', 49000, 64000, 462, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor iure, repudiandae eius corrupti ipsum esse atque itaque eum quos cupiditate provident, mollitia porro commodi ex odit voluptatem assumenda in illo quasi doloremque, laboriosam ea iusto nisi! Asperiores, impedit expedita labore fugit exercitationem quam maxime! Numquam, error repellendus nihil culpa explicabo saepe corporis eveniet beatae fugiat deleniti!', '996-lamps-575870_1280.png'),
(9, 'Wadah Bambu', '2021-12-03', 38000, 50000, 518, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor iure, repudiandae eius corrupti ipsum esse atque itaque eum quos cupiditate provident, mollitia porro commodi ex odit voluptatem assumenda in illo quasi doloremque, laboriosam ea iusto nisi! Asperiores, impedit expedita labore fugit exercitationem quam maxime! Numquam, error repellendus nihil culpa explicabo saepe corporis eveniet beatae fugiat deleniti!', '294-bamboo-373814_1280.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `data_klr`
--

CREATE TABLE `data_klr` (
  `id` int(10) NOT NULL,
  `id_brg` int(10) NOT NULL,
  `tgl_keluar` date NOT NULL,
  `jml_keluar` int(10) NOT NULL,
  `penerima` varchar(35) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_klr`
--

INSERT INTO `data_klr` (`id`, `id_brg`, `tgl_keluar`, `jml_keluar`, `penerima`, `keterangan`) VALUES
(1, 1, '2021-12-21', 400, 'Anton', 'terjual'),
(2, 2, '2021-12-21', 200, 'Boy', 'terjual'),
(3, 1, '2021-12-21', 600, 'Dwiki', 'terjual'),
(4, 2, '2021-12-22', 200, 'Anto', 'terjual'),
(5, 8, '2021-12-21', 50, 'Lio', 'terjual'),
(6, 5, '2021-12-22', 200, 'Akbar', 'terjual');

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
(12, 'Lingkar Jati Abadi', 'Patrang', '085731379198', '2021-12-01', 'Kursi'),
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
-- Table structure for table `data_msk`
--

CREATE TABLE `data_msk` (
  `id` int(10) NOT NULL,
  `id_brg` int(10) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `jml_masuk` int(10) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_msk`
--

INSERT INTO `data_msk` (`id`, `id_brg`, `tgl_masuk`, `jml_masuk`, `keterangan`) VALUES
(2, 6, '2021-12-20', 500, 'lunas'),
(3, 2, '2021-11-27', 600, 'lunas'),
(4, 7, '2021-12-21', 500, 'lunas'),
(5, 5, '2021-12-21', 500, 'lunas'),
(6, 9, '2021-12-21', 500, 'lunas'),
(7, 8, '2021-12-21', 500, 'lunas'),
(8, 6, '2021-12-21', 500, 'lunas'),
(10, 5, '2021-12-22', 200, 'lunas');

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
  `nama_reseller` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_tlp` varchar(13) NOT NULL,
  `tgl_gabung` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_reseller`
--

INSERT INTO `data_reseller` (`id`, `nama_reseller`, `alamat`, `no_tlp`, `tgl_gabung`) VALUES
(2, 'Dwiki Gaming', 'Pasuruan', '085731379198', '2021-11-26'),
(4, 'Anton Gaming', 'Bondowoso', '085731379198', '2021-12-15'),
(5, 'Boy Gaming', 'Pasuruan', '085731379198', '2021-12-15'),
(6, 'Anton Pro', 'Kaliurang', '085731379198', '2021-12-21');

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `id_keranjang` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `qty` int(3) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `keranjang`
--

INSERT INTO `keranjang` (`id_keranjang`, `id_user`, `id_barang`, `qty`, `subtotal`) VALUES
(1, 2, 2, 1, 70000),
(2, 1, 5, 1, 60000);

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id_report` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `tgl_transaksi` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_user` int(11) NOT NULL,
  `alamat` varchar(120) NOT NULL,
  `no_telp` char(15) NOT NULL,
  `resi` varchar(30) NOT NULL,
  `total_transaksi` int(11) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `tgl_transaksi` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_user` int(11) NOT NULL,
  `alamat` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `no_telp` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `id_ongkir` int(11) NOT NULL,
  `resi` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `total_transaksi` int(11) NOT NULL,
  `status` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `bukti_tf` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tgl_transaksi`, `id_user`, `alamat`, `no_telp`, `id_ongkir`, `resi`, `total_transaksi`, `status`, `bukti_tf`) VALUES
(5, '2021-12-17 11:30:43', 2, 'ahaha', '0198291892', 2, '-', 2000000, 'Aktif', '-');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_produk`
--

CREATE TABLE `transaksi_produk` (
  `id_transaksi_produk` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `id_brg` int(11) NOT NULL,
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
  `username` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `no_telp` char(15) NOT NULL,
  `password` varchar(99) NOT NULL,
  `foto_profil` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `fullname`, `username`, `email`, `no_telp`, `password`, `foto_profil`) VALUES
(1, 'Yoga Andrian', 'yogandrn', 'yoga@gmail.com', '085667454320', '1234', 'yoga.jpg'),
(2, 'budiman', 'budi', 'budi@gmail.com', '089778760903', '0000', 'default_user.png'),
(5, 'New User 2', 'user2', 'user2@gmail.com', '08568400176', '1234', 'default_user.png'),
(6, 'Selii', 'seli', 'seli@gmail.com', '08564782948', 'selianjay', 'default_user.png'),
(8, 'Budianto', 'budianto', 'budianto@gmail.com', '097357288', 'budiku', 'default_user.png'),
(9, 'Dini Hati Nurvi', 'dininurvi', 'dininurvi@gmail.com', '085664792996', 'anjayani', 'default_user.png'),
(10, 'Slamet ', 'slamet', 'slamet@gmail.com', '075986678', 'anjay', 'default_user.png');

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
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`id_keranjang`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id_report`),
  ADD UNIQUE KEY `id_transaksi` (`id_transaksi`);

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
  ADD PRIMARY KEY (`id_transaksi_produk`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_brg` (`id_brg`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_brg`
--
ALTER TABLE `data_brg`
  MODIFY `id_brg` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `data_klr`
--
ALTER TABLE `data_klr`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `data_mitra`
--
ALTER TABLE `data_mitra`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `data_msk`
--
ALTER TABLE `data_msk`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `data_ongkir`
--
ALTER TABLE `data_ongkir`
  MODIFY `id_ongkir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `data_reseller`
--
ALTER TABLE `data_reseller`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `id_keranjang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id_report` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transaksi_produk`
--
ALTER TABLE `transaksi_produk`
  MODIFY `id_transaksi_produk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD CONSTRAINT `keranjang_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `keranjang_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `data_brg` (`id_brg`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_ongkir`) REFERENCES `data_ongkir` (`id_ongkir`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `transaksi_produk`
--
ALTER TABLE `transaksi_produk`
  ADD CONSTRAINT `transaksi_produk_ibfk_1` FOREIGN KEY (`id_brg`) REFERENCES `data_brg` (`id_brg`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_produk_ibfk_2` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
