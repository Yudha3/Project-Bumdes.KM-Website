<?php

// if(isset($_POST['id'])){
    include '../koneksi.php';

    $id = $_POST['id'];
    $dt = mysqli_query($koneksi, "select * from data_brg where id_brg='$id'");
    $data = mysqli_fetch_array($dt);

    $harga_beli = $data['hg_jual'];
    echo $harga_beli;
// } else {
//     echo "gak ada post";
// }
?>