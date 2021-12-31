<?php
include '../koneksi.php';

$id = $_GET["id_transaksi"];

$find= mysqli_query($koneksi, "SELECT * FROM transaksi WHERE id_transaksi = '$id'");
while ($get = mysqli_fetch_assoc($find)) {
    $id_trx = $get['id_transaksi'];
    $tgl = substr($get['tgl_transaksi'], 0, -9);
    $id_user = $get['id_user'];
    $penerima = $get['penerima'];
    $alamat = $get['alamat'];
    $no_telp = $get['no_telp'];
    $id_ongkir = $get['id_ongkir'];
    $total = $get['total_transaksi'];
}
$simpan = mysqli_query($koneksi, "INSERT INTO report_klr (id_transaksi, tgl_transaksi, penerima, alamat, total_transaksi, status) VALUES ('$id_trx','$tgl','$penerima','$alamat','$total', 'Selesai') ");

$delete = mysqli_query($koneksi, "DELETE FROM transaksi WHERE id_transaksi = '$id'");
$delete = mysqli_query($koneksi, "DELETE FROM transaksi_produk WHERE id_transaksi = '$id'");
if (!$delete) {
        die("Gagal menghapus data: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        echo "<script>alert('Data berhasil dihapus.');window.location='../barangKeluar.php';</script>";
    }
?>                              
