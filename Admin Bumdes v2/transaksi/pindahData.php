<?php
include '../koneksi.php';

$id = $_GET["id"];
$simpan = mysqli_query($koneksi, "INSERT INTO report_klr (id_transaksi,tgl_keluar,id_brg,barang,jml_keluar,total_hrg,penerima,keterangan) SELECT id_transaksi,tgl_keluar,id_brg,barang,jml_keluar,total_hrg,penerima,keterangan FROM data_klr WHERE id ='$_GET[id]'");
$delete = mysqli_query($koneksi, "DELETE FROM data_klr WHERE id = '$_GET[id]'");
if (!$delete) {
        die("Gagal menghapus data: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        echo "<script>alert('Data berhasil dihapus.');window.location='../barangKeluar.php';</script>";
    }
?>