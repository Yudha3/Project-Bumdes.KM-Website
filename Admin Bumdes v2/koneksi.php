<?php
  $host = "localhost"; 
  $user = "u1694897_c_reg_5";
  $pass = "jtipolije";
  $nama_db = "u1694897_c_reg_5_db"; //nama database
  $koneksi = mysqli_connect($host,$user,$pass,$nama_db); //pastikan urutan nya seperti ini, jangan tertukar

  if(!$koneksi){ //jika tidak terkoneksi maka akan tampil error
    die ("Koneksi dengan database gagal: ".mysqli_connect_error());
  }
?>