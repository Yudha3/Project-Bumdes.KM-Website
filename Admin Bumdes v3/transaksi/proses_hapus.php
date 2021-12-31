<?php
include '../koneksi.php';

if($_GET["id_transaksi"]){
  $id = $_GET['id_transaksi'];

  $queryx = mysqli_query($koneksi,"delete from transaksi where id_transaksi='$id'");
  $del = mysqli_query($koneksi,"delete from transaksi_produk where id_transaksi_produk='$id'");

  
  //cek apakah berhasil
  if ($queryx && $del){
      echo "<script>alert('Data berhasil dihapus.');window.location='../barangKeluar.php';</script>";
  } else {
    echo "<script>alert('Data gagal dihapus.');window.location='../barangKeluar.php';</script>";
  }
}

?>