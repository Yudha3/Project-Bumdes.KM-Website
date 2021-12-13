<?php 
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  require 'config.php';

  if ($conn) {
    $id_user = $_POST['id_user'];

    $ambilKeranjang = mysqli_query($conn, "SELECT * FROM keranjang WHERE id_user = '$id_user'");
    $totalBelanja =0;
      while ($getKeranjang = mysqli_fetch_assoc($ambilKeranjang)) {
          $subtotal = $getKeranjang["subtotal"];
          $totalBelanja += $subtotal;
      } 
    
    $response = array('pesan'=> 'BERHASIL');
    $response = array('kode'=> $totalBelanja);
  } else {
  $response = array('pesan'=> $GAGAL); 
  }
  echo json_encode($response);
  mysqli_close($conn);
}
?>