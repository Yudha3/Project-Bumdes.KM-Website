<?php
include '../koneksi.php';
$id_transaksi = $_POST['id_transaksi'];
$barang=$_POST['barang']; //id barang
$pecah_barang = explode(".", $barang);
$kode_barang = $pecah_barang[0];
$nama_barang = $pecah_barang[1];
$jml_keluar=$_POST['jml_keluar'];
$tgl_keluar=$_POST['tgl_keluar'];
$penerima=$_POST['penerima'];
$keterangan=$_POST['keterangan'];
$total = $_POST['total_hrg'];
$hg_jual = $_POST['hg_jual'];

$dt=mysqli_query($koneksi,"select * from data_brg where id_brg='$kode_barang'");
$data=mysqli_fetch_array($dt);
$sisa=$data['jml_stok']-$jml_keluar;
$query1 = mysqli_query($koneksi,"update data_brg set jml_stok='$sisa' where id_brg='$kode_barang'");

$query2 = mysqli_query($koneksi,"insert into data_klr (id_transaksi,tgl_keluar,id_brg,barang,hg_jual,jml_keluar,total_hrg,penerima,keterangan) values('$id_transaksi','$tgl_keluar','$kode_barang','$nama_barang','$hg_jual','$jml_keluar','$total','$penerima','$keterangan')");

if($query1 && $query2){
    echo "<script>alert('Data berhasil ditambah.');window.location='../barangKeluar.php';</script>";
} else {
  echo "<script>alert('Data gagal ditambah.');window.location='../barangKeluar.php';</script>";
}
?>