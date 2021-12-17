<?php
include '../koneksi.php';

    $id = $_POST['id'];

    $query  = "INSERT INTO report(id_transaksi,tgl_transaksi,id_user,alamat,no_telp,resi,total_transaksi,status)
    SELECT id_transaksi,tgl_transaksi,id_user,alamat,no_telp,resi,total_transaksi,status
    FROM transaksi WHERE id_transaksi='$id'";
    $query .= "DELETE FROM transaksi WHERE id_transaksi='$id'";
    $result = mysqli_query($koneksi, $query);
 
    if (!$result) {
        die("Error data: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
       
        echo "<script>alert('Transaksi Selesai.');window.location='../transaksi.php';</script>";
    }
