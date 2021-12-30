<?php
include '../koneksi.php';

if(isset($_POST['update'])){
    $id = $_POST['id_transaksi']; //iddata
    $resi= $_POST['resi'];
    $keterangan = $_POST['keterangan'];

    $query  = "UPDATE transaksi SET resi = '$resi', keterangan = '$keterangan'";
    $query .= "WHERE id_transaksi = '$id'";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil diubah.');window.location='../barangKeluar.php';</script>";
    }


    
}
?>
