<?php
require_once('../koneksi.php');
require_once('database.php');
include "m_barang.php";

$connection = new Database($host, $user, $pass, $nama_db);
$brg = new Barang($connection);

$id = $_POST['id'];
$nama_brg = $connection->conn->real_escape_string($_POST['nama_brg']);
$jenis_brg = $connection->conn->real_escape_string($_POST['jenis_brg']);
$harga_brg = $connection->conn->real_escape_string($_POST['harga_brg']);
$stok_brg = $connection->conn->real_escape_string($_POST['stok_brg']);
$deskripsi_brg = $connection->conn->real_escape_string($_POST['deskripsi_brg']);

$pict = $_FILES['gambar_brg']['name'];
$extensi = explode(".", $_FILES['gambar_brg']['name']);
$gambar_brg = "brg-".round(microtime(true)).".".end($extensi);
$sumber = $_FILES['gambar_brg']['tmp_name'];

if($pict == '') {
    $brg->edit("UPDATE data_brg SET nama_brg = '$nama_brg', jenis_brg = '$jenis_brg', harga_brg = '$harga_brg', 
        stok_brg = '$stok_brg', deskripsi_brg = '$deskripsi_brg' WHERE id = '$id'");
    echo "<script>window.location='?page=barang';</script>";
} else {
    $gambar_awal = $brg->tampil($id)->fetch_object()->gambar_brg;
    unlink("../images/barang/".$gambar_awal);

    $upload = move_uploaded_file($sumber, "../images/barang/".$gambar_brg);
    if($upload) {
        // var_dump($id);
        $brg->edit("UPDATE data_brg SET nama_brg = '$nama_brg', jenis_brg = '$jenis_brg', harga_brg = '$harga_brg', 
            stok_brg = '$stok_brg', deskripsi_brg = '$deskripsi_brg', gambar_brg = '$gambar_brg' WHERE id = '$id'");
        echo "<script>window.location='?page=barang';</script>";
    } else {
        echo "<script>alert('Upload gambar gagal!')</script>";
    }
} 

?>