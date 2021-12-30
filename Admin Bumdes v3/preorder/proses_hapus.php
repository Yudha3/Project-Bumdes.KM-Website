<?php
include '../koneksi.php';

if($_GET["id_preorder"]){
  $id = $_GET['id_preorder'];
  // $id_brg = "select id_brg from data_msk where id='$id'";

  $queryx = mysqli_query($koneksi,"delete from preorder where id_preorder='$id'");
  $del = mysqli_query($koneksi,"delete from item_preorder where id_item_preorder='$id'");

  
  //cek apakah berhasil
  if ($queryx && $del){
      echo "<script>alert('Data berhasil dihapus.');window.location='../preOrder.php';</script>";
  } else {
    echo "<script>alert('Data gagal dihapus.');window.location='../preOrder.php';</script>";
  }
}

?>