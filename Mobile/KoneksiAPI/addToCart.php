<?php 
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  require "config.php";
  if ($conn) {
    $id_user = $_POST['id_user'];
    $id_brg = $_POST['id_brg'];
    $qty = $_POST['qty'];
    // $subtotal = $_POST['subtotal'];

    $findHarga = $conn->query("SELECT hg_jual FROM data_brg WHERE id_brg = '$id_brg'");
    $getHarga = mysqli_fetch_assoc($findHarga);
    $harga = $getHarga['hg_jual'];
    $subtotal = $qty * $harga;
    
    $queryInsert = mysqli_query($conn, "INSERT INTO keranjang VALUES ('', '$id_user', '$id_brg', '$qty', '$subtotal')");

    if ($queryInsert) {
      $response = array('pesan'=>'BERHASIL');
    } else if (!$queryInsert) {
      $response = array('pesan'=>'GAGAL');
    }
  } else {
    $response = array('pesan'=>'NOT CONNECTED');
  }
  echo json_encode($response);
  mysqli_close($conn);
}

?>