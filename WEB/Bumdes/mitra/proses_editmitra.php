<?php
include '../koneksi.php';

// membuat variabel untuk menampung data dari form
if (isset($_POST['submit'])) {
    $id = $_POST['id'];
    $nama_mitra   = $_POST['nama_mitra'];
    $alamat  = $_POST['alamat'];
    $nomer_telepon    = $_POST['no_tlp'];
    $tgl_gabung    = $_POST['tgl_gabung'];
    $barang     = $_POST['barang'];

	$query  = "UPDATE data_mitra SET nama_mitra = '$nama_mitra', alamat = '$alamat', no_tlp = '$nomer_telepon', tgl_gabung = '$tgl_gabung', barang = '$barang'";
    $query .= "WHERE id = '$id'";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil diubah.');window.location='Mitra.php';</script>";
    }

}
