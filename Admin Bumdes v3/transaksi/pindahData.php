<?php
include '../koneksi.php';

$id = $_GET["id_transaksi"];
$simpan = mysqli_query($koneksi, "INSERT INTO report_klr (id_transaksi,tgl_transaksi, total_transaksi,penerima,status) SELECT id_transaksi,tgl_transaksi,total_transaksi,penerima,status FROM transaksi WHERE id_transaksi ='$id'");

$delete = mysqli_query($koneksi, "DELETE FROM transaksi WHERE id_transaksi = '$id'");
$delete = mysqli_query($koneksi, "DELETE FROM transaksi_produk WHERE id_transaksi = '$id'");
if (!$delete) {
        die("Gagal menghapus data: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        echo "<script>alert('Data berhasil dihapus.');window.location='../barangKeluar.php';</script>";
    }
?>                              