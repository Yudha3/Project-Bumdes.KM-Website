<?php
include '../koneksi.php';
$barang=$_POST['barang']; //id barang
$jml_keluar=$_POST['jml_keluar'];
$tgl_keluar=$_POST['tgl_keluar'];
$penerima=$_POST['penerima'];
$keterangan=$_POST['keterangan'];

$dt=mysqli_query($koneksi,"select * from data_brg where id_brg='$barang'");
$data=mysqli_fetch_array($dt);
$sisa=$data['jml_stok']-$jml_keluar;
$query1 = mysqli_query($koneksi,"update data_brg set jml_stok='$sisa' where id_brg='$barang'");

$query2 = mysqli_query($koneksi,"insert into data_klr (id_brg,tgl_keluar,jml_keluar,penerima,keterangan) values('$barang','$tgl_keluar','$jml_keluar','$penerima','$keterangan')");

if($query1 && $query2){
    echo "<script>alert('Data berhasil ditambah.');window.location='../barangKeluar.php';</script>";
} else {
  echo "<script>alert('Data gagal ditambah.');window.location='../barangKeluar.php';</script>";
}
?>