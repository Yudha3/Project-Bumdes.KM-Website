<?php
include '../koneksi.php';

if(isset($_POST['update'])){
    $id = $_POST['id']; //iddata
    $id_brg = $_POST['id_brg']; //idbarang
    $jml_masuk = $_POST['jml_masuk'];
    $total_hrg = $_POST['total_hrg'];

    $lihatstock = mysqli_query($koneksi,"select * from data_brg where id_brg='$id_brg'"); //lihat stock barang itu saat ini
    $stocknya = mysqli_fetch_array($lihatstock); //ambil datanya
    $stockskrg = $stocknya['jml_stok'];//jml_masuk stocknya skrg

    $lihatdataskrg = mysqli_query($koneksi,"select * from data_msk where id='$id'"); //lihat qty saat ini
    $preqtyskrg = mysqli_fetch_array($lihatdataskrg); 
    $qtyskrg = $preqtyskrg['jml_masuk'];//jml_masuk skrg

    if($jml_masuk >= $qtyskrg){
        //ternyata inputan baru lebih besar jml_masuk masuknya, maka tambahi lagi stock barang
        $hitungselisih = $jml_masuk-$qtyskrg;
        $tambahistock = $stockskrg+$hitungselisih;

        $queryx = mysqli_query($koneksi,"update data_brg set jml_stok='$tambahistock' where id_brg='$id_brg'");
        $updatedata1 = mysqli_query($koneksi,"update data_msk set jml_masuk='$jml_masuk',total_hrg='$total_hrg' where id='$id'");
        
        //cek apakah berhasil
        if ($updatedata1 && $queryx){

            echo "<script>alert('Data berhasil diubah.');window.location='../barangMasuk.php';</script>";
        } else {
          echo "<script>alert('Data gagal dihapus.');window.location='../barangMasuk.php';</script>";
        };

    } else {
        //ternyata inputan baru lebih kecil jml_masuk masuknya, maka kurangi lagi stock barang
        $hitungselisih = $qtyskrg-$jml_masuk;
        $kurangistock = $stockskrg-$hitungselisih;

        $query1 = mysqli_query($koneksi,"update data_brg set jml_stok='$kurangistock' where id_brg='$id_brg'");

        $updatedata = mysqli_query($koneksi,"update data_msk set jml_masuk='$jml_masuk',total_hrg='$total_hrg' where id='$id'");
        
        //cek apakah berhasil
        if ($query1 && $updatedata){

            echo "<script>alert('Data berhasil dihapus.');window.location='../barangMasuk.php';</script>";
        } else {
          echo "<script>alert('Data gagal dihapus.');window.location='../barangMasuk.php';</script>";
        };

    };


    
};
?>