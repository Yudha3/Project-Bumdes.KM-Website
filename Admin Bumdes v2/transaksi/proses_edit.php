<?php
include '../koneksi.php';

if(isset($_POST['update'])){
    $id = $_POST['id']; //iddata
    $id_brg = $_POST['id_brg']; //idbarang
    $jml_keluar = $_POST['jml_keluar'];
    $penerima = $_POST['penerima'];
    $keterangan = $_POST['keterangan'];

    $lihatstock = mysqli_query($koneksi,"select * from data_brg where id_brg='$id_brg'"); //lihat stock barang itu saat ini
    $stocknya = mysqli_fetch_array($lihatstock); //ambil datanya
    $stockskrg = $stocknya['jml_stok'];//jml_keluar stocknya skrg

    $lihatdataskrg = mysqli_query($koneksi,"select * from data_klr where id='$id'"); //lihat qty saat ini
    $preqtyskrg = mysqli_fetch_array($lihatdataskrg); 
    $qtyskrg = $preqtyskrg['jml_keluar'];//jml_keluar skrg

    if($jml_keluar >= $qtyskrg){
        //ternyata inputan baru lebih besar jml_keluar keluarnya, maka kurangi lagi stock barang
        $hitungselisih = $jml_keluar-$qtyskrg;
        $kurangistock = $stockskrg-$hitungselisih;

        $queryx = mysqli_query($koneksi,"update data_brg set jml_stok='$kurangistock' where id_brg='$id_brg'");
        $updatedata1 = mysqli_query($koneksi,"update data_klr set jml_keluar='$jml_keluar',penerima='$penerima',keterangan='$keterangan' where id='$id'");
        
        //cek apakah berhasil
        if ($updatedata1 && $queryx){

            echo " <div class='alert alert-success'>
                <strong>Success!</strong> Redirecting you back in 1 seconds.
            </div>
            <meta http-equiv='refresh' content='1; url= ../barangKeluar.php'/>  ";
            } else { echo "<div class='alert alert-warning'>
                <strong>Failed!</strong> Redirecting you back in 3 seconds.
            </div>
            <meta http-equiv='refresh' content='3; url= ../barangKeluar.php'/> ";
            };

    } else {
        //ternyata inputan baru lebih kecil jml_keluar keluarnya, maka tambahi lagi stock barang
        $hitungselisih = $qtyskrg-$jml_keluar;
        $tambahistock = $stockskrg+$hitungselisih;

        $query1 = mysqli_query($koneksi,"update data_brg set jml_stok='$tambahistock' where id_brg='$id_brg'");

        $updatedata = mysqli_query($koneksi,"update data_klr set jml_keluar='$jml_keluar', penerima='$penerima', keterangan='$keterangan' where id='$id'");
        
        //cek apakah berhasil
        if ($query1 && $updatedata){

            echo " <div class='alert alert-success'>
                <strong>Success!</strong> Redirecting you back in 1 seconds.
            </div>
            <meta http-equiv='refresh' content='1; url= ../barangKeluar.php'/>  ";
            } else { echo "<div class='alert alert-warning'>
                <strong>Failed!</strong> Redirecting you back in 3 seconds.
            </div>
            <meta http-equiv='refresh' content='3; url= ../barangKeluar.php'/> ";
            };

    };


    
}
?>
