<?php
include '../koneksi.php';

$id = $_GET["id"];
$simpan = mysqli_query($koneksi, "INSERT INTO report_msk (id_transaksi,tgl_msk,id_brg,barang,pengirim,hg_beli,jml_masuk,total_hrg,keterangan) SELECT id_transaksi,tgl_msk,id_brg,barang,pengirim,hg_beli,jml_masuk,total_hrg,keterangan FROM data_msk WHERE id ='$_GET[id]'");
$delete = mysqli_query($koneksi, "DELETE FROM data_msk WHERE id = '$_GET[id]'");
if (!$delete) {
        die("Gagal menghapus data: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        echo "<script>alert('Data berhasil dihapus.');window.location='../barangMasuk.php';</script>";
    }
?>