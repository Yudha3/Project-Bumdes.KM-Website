<?php
include '../koneksi.php';

// membuat variabel untuk menampung data dari form
if (isset($_POST['submit'])) {
    $id = $_POST['id'];
    $alamat  = $_POST['alamat'];
    $statusket = $_POST['statusket'];

	$query  = "UPDATE transaksi SET alamat = '$alamat', status = '$statusket'";
    $query .= "WHERE id_transaksi = '$id'";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil diubah.');window.location='../transaksi.php';</script>";
    }

}
