<?php
require_once('../koneksi.php');
require_once('database.php');
include "m_barang.php";

$connection = new Database($host, $user, $pass, $nama_db);
$mtr = new Mitra($connection);

$id = $_POST['id'];
$nama_mitra = $connection->conn->real_escape_string($_POST['nama_mitra']);
$alamat = $connection->conn->real_escape_string($_POST['alamat']);
$no_tlp = $connection->conn->real_escape_string($_POST['no_tlp']);
$tgl_gabung = $connection->conn->real_escape_string($_POST['tgl_gabung']);
$barang = $connection->conn->real_escape_string($_POST['barang']);
$mtr->edit("UPDATE data_mitra SET nama_mitra = '$nama_mitra', alamat = '$alamat', no_tlp = '$no_tlp', 
        tgl_gabung = '$tgl_gabung', barang = '$barang' WHERE id = '$id'");
echo "<script>window.location='?page=mitra';</script>";

