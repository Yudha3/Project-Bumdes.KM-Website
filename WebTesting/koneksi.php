<?php
    $koneksi = mysqli_connect("localhost", "root", "", "lightlance");
    
    //output jika gagal terkoneksi dengan database
    if (mysqli_connect_errno()){
        echo "Koneksi database gagal : " . mysqli_connect_error();
    }
 
?>