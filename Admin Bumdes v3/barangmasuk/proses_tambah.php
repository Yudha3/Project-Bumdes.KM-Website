<?php
include '../koneksi.php';
$id_transaksi = $_POST['id_transaksi'];
$barang = $_POST['barang']; // ini ID barang nya
$pecah_barang = explode(".", $barang);
$kode_barang = $pecah_barang[0];
$nama_barang = $pecah_barang[1];
$jml_masuk = $_POST['jml_masuk'];
$tgl_msk = $_POST['tgl_msk'];
$nama_mitra = $_POST['pengirim'];
$total = $_POST['total_hrg'];
$hg_beli = $_POST['hg_beli'];

if ($barang === "-- Pilih Barang --") {
  echo "<script>alert('Terjadi Kesalahan : Tidak ada Barang yang terpilih');window.location='newBarangmasuk.php';</script>";
  exit;
} elseif ($nama_mitra === "-- Pilih Mitra --") {
  echo "<script>alert('Terjadi Kesalahan : Anda belum memilih nama Mitra');window.location='newBarangmasuk.php';</script>";
  exit;
}

$dt = mysqli_query($koneksi, "select * from data_brg where id_brg='$kode_barang'");
$data = mysqli_fetch_array($dt);
$sisa = $data['jml_stok'] + $jml_masuk;
$query1 = mysqli_query($koneksi, "update data_brg set jml_stok='$sisa' where id_brg='$kode_barang'");

$query2 = mysqli_query($koneksi, "insert into data_msk (id_transaksi,tgl_msk,id_brg,barang,pengirim,hg_beli,jml_masuk,total_hrg) 
                      values('$id_transaksi','$tgl_msk','$kode_barang','$nama_barang','$nama_mitra','$hg_beli','$jml_masuk','$total')");

if ($query1 && $query2) {
  echo "<script>alert('Data berhasil ditambah.');window.location='../barangMasuk.php';</script>";
} else {
  echo "<script>alert('Data gagal ditambah.');window.location='../barangMasuk.php';</script>";
}


?>