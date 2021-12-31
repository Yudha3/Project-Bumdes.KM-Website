<?php
include '../koneksi.php';

if(isset($_POST['update'])){
    $id = $_POST['id_preorder']; //iddata
    $status = $_POST['status'];

    $query  = "UPDATE preorder SET status = '$status'";
    $query .= "WHERE id_preorder = '$id'";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil diubah.');window.location='../preOrder.php';</script>";
    }
}
?>
