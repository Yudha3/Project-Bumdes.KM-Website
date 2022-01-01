<?php
include '../koneksi.php';

$id = $_GET["id_transaksi"];
$find = mysqli_query($koneksi, "SELECT * FROM transaksi WHERE id_transaksi ='$id'");

while ($data = mysqli_fetch_assoc($find)){
    $id_trx = ['id_transaksi'];
    $tgl = substr($data['tgl_transaksi'], 0, -9);
    $penerima = ['penerima'];
    $alamat = ['alamat'];
    $total = ['total_transaksi'];

    mysqli_query($koneksi, "INSERT INTO report_klr (id_transaksi, tgl_transaksi, penerima, alamat, total_transaksi,status) VALUES ('$id_trx','$tgl','$penerima','$alamat','$total','Selesai')");

    if (mysqli_affected_rows($koneksi) > 0) {
        $deleteTransaksi = mysqli_query($koneksi, "DELETE FROM transaksi WHERE id_transaksi = '$id'");
        $deleteItem = mysqli_query($koneksi, "DELETE FROM transaksi_produk WHERE id_transaksi = '$id'");
        if (!$deleteTransaksi && !$deleteItem) {
                die("Gagal menghapus data: " . mysqli_errno($koneksi) .
                    " - " . mysqli_error($koneksi));
            } else {
                echo "<script>alert('Data berhasil dihapus.');window.location='../barangKeluar.php';</script>";
            }
    } else {
        die("Gagal memindahkan data: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    }
}
?>                              
