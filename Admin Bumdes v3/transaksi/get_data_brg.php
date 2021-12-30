<?php

// if(isset($_POST['id'])){
    include '../koneksi.php';

    $id = $_POST['id'];
    $pecah_bar = explode(".", $id);
    $kode_bar = $pecah_bar[0];
    $dt = mysqli_query($koneksi, "select * from data_brg where id_brg = '$kode_bar'");
    $data = mysqli_fetch_array($dt);

    $harga_beli = $data['hg_jual'];
    echo $harga_beli;
// } else {
//     echo "gak ada post";
// }
?>