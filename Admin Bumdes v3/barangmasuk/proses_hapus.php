<?php
include '../koneksi.php';

if($_GET["id"]){
    $id = $_GET['id'];
    // $id_brg = "select id_brg from data_msk where id='$id'";
    $getID = mysqli_fetch_assoc(mysqli_query($koneksi, "SELECT * FROM data_msk WHERE id = '$id'"));
    $id_brg = $getID['id_brg'];

    $lihatstock = mysqli_query($koneksi,"select * from data_brg where id_brg='$id_brg'"); //lihat stock barang itu saat ini
    $stocknya = mysqli_fetch_array($lihatstock); //ambil datanya
    $stockskrg = $stocknya['jml_stok'];//jumlah stocknya skrg

    $lihatdataskrg = mysqli_query($koneksi,"select * from data_msk where id='$id'"); //lihat qty saat ini
    $preqtyskrg = mysqli_fetch_array($lihatdataskrg); 
    $qtyskrg = $preqtyskrg['jml_masuk'];//jumlah skrg

    $adjuststock = $stockskrg-$qtyskrg;

    $queryx = mysqli_query($koneksi,"update data_brg set jml_stok='$adjuststock' where id_brg='$id_brg'");
    $del = mysqli_query($koneksi,"delete from data_msk where id='$id'");

    
    //cek apakah berhasil
    if ($queryx && $del){
        echo "<script>alert('Data berhasil dihapus.');window.location='../barangMasuk.php';</script>";
    } else {
      echo "<script>alert('Data gagal dihapus.');window.location='../barangMasuk.php';</script>";
    }
}
