<?php
require_once('../koneksi.php');
require_once('database.php');
include "m_barang.php";

$connection = new Database($host, $user, $pass, $nama_db);
$rsl = new Mitra($connection);

$id = $_POST['id'];
$nama_reseller = $connection->conn->real_escape_string($_POST['nama_reseller']);
$alamat = $connection->conn->real_escape_string($_POST['alamat']);
$no_tlp = $connection->conn->real_escape_string($_POST['no_tlp']);
$tgl_gabung = $connection->conn->real_escape_string($_POST['tgl_gabung']);
$rsl->edit("UPDATE data_reseller SET nama_reseller = '$nama_reseller', alamat = '$alamat', no_tlp = '$no_tlp', 
        tgl_gabung = '$tgl_gabung' WHERE id = '$id'");
echo "<script>window.location='?page=reseller';</script>";

