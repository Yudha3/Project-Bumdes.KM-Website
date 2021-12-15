<?php 
if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require "config.php";
  if ($conn) {
    $id_user = $_POST['id_user'];
    $id_brg = $_POST['id_brg'];
    $qty = $_POST['qty'];
    // $subtotal = $_POST['subtotal'];
    $intQty = (int)$qty;
    
    // $queryCek = mysqli_query($conn, "SELECT * FROM keranjang WHERE id_barang = '$id_brg' AND id_user = '$id_user'");
    
    $findHarga = mysqli_query($conn, "SELECT hg_jual FROM data_brg WHERE id_brg = '$id_brg'");
    $getHarga = mysqli_fetch_assoc($findHarga);
    $harga = $getHarga["hg_jual"];
    $subtotal = $intQty * $harga;
    
    // if (mysqli_num_rows($queryCek) > 0) {
    //     $qupdate = mysqli_query($conn, "UPDATE keranjang SET qty = qty + '$intQty', subtotal = '$subtotal' WHERE id_user = '$id_user' AND id_barang = 'id_brg' ");
    //     if (mysqli_affected_rows($conn) > 0) {
    //     $response = array('pesan'=>'BERHASIL');} else {$response = array('pesan'=>'GAGAL');}
    // } else {
    
    $queryInsert = mysqli_query($conn, "INSERT INTO keranjang (id_user, id_barang, qty, subtotal) VALUES ('$id_user', '$id_brg', '$intQty', '$subtotal')");

    if ($queryInsert) {
      $response = array('pesan'=>'BERHASIL');
    } else if (!$queryInsert) {
      $response = array('pesan'=>'GAGAL');
    }
    // }
  } else {
    $response = array('pesan'=>'NOT CONNECTED');
  }
}
  echo json_encode($response);
  mysqli_close($conn);

?>