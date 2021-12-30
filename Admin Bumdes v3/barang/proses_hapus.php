<?php
include '../koneksi.php';
$id = $_GET["id_brg"];
$gambar = $_GET["gambar"];
//mengambil id yang ingin dihapus

//jalankan query DELETE untuk menghapus data
unlink('../images/barang/'.$gambar);
$query = "DELETE FROM data_brg WHERE id_brg = '$id' ";
$hasil_query = mysqli_query($koneksi, $query);

//periksa query, apakah ada kesalahan
if (!$hasil_query) {
    die("Gagal menghapus data: " . mysqli_errno($koneksi) .
        " - " . mysqli_error($koneksi));
} else {
    echo "<script>alert('Data berhasil dihapus.');window.location='../barang.php';</script>";
}
