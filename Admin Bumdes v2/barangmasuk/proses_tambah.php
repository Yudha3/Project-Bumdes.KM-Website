<?php 
include '../koneksi.php';
$barang=$_POST['barang']; // ini ID barang nya
$jml_masuk=$_POST['jml_masuk'];
$tgl_masuk=$_POST['tgl_masuk'];
$keterangan=$_POST['keterangan'];

$dt=mysqli_query($koneksi,"select * from data_brg where id_brg='$barang'");
$data=mysqli_fetch_array($dt);
$sisa=$data['jml_stok']+$jml_masuk;
$query1 = mysqli_query($koneksi,"update data_brg set jml_stok='$sisa' where id_brg='$barang'");

$query2 = mysqli_query($koneksi,"insert into data_msk (id_brg,tgl_masuk,jml_masuk,keterangan) values('$barang','$tgl_masuk','$jml_masuk','$keterangan')");

if($query1 && $query2){
    echo "<script>alert('Data berhasil ditambah.');window.location='../barangMasuk.php';</script>";
} else {
  echo "<script>alert('Data gagal ditambah.');window.location='../barangMasuk.php';</script>";
}


?>