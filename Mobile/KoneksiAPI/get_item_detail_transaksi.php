<?php 
if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require 'config.php';

  if ($conn) {
    $id_transaksi = $_POST['id_transaksi'];
    // $id_transaksi = 13;
    $queryGET = mysqli_query($conn, "SELECT * FROM transaksi_produk WHERE id_transaksi = '$id_transaksi'");
    if (mysqli_num_rows($queryGET) > 0) {
      $response['pesan'] = "Data tersedia";
      $response['item_transaksi'] = array();

      while ($ambil = mysqli_fetch_object($queryGET)) {
        $F['id_transaksi_produk'] = $ambil->id_transaksi_produk;
        $F['id_brg'] = $ambil->id_brg;
        $F['qty'] = $ambil->qty;
        $F['subtotal'] = $ambil->subtotal;
        $idb = $F['id_brg'];
        $queryA = mysqli_query($conn, "SELECT id_brg FROM data_brg WHERE id_brg = '$idb' ");
        while ($getID = mysqli_fetch_assoc($queryA)) {
          $id_brg = $getID['id_brg'];
          $queryB = mysqli_query($conn, "SELECT barang, hg_jual, gambar FROM data_brg WHERE id_brg = '$id_brg'");
          while($getData = mysqli_fetch_assoc($queryB)) {
            $F['barang'] = $getData['barang'];
            $F['harga'] = $getData['hg_jual'];
            $F['gambar'] = $getData['gambar'];
          } 
        }
        array_push($response["item_transaksi"], $F);
      }

    } else {
      $response['pesan'] = "Data tidak tersedia";
    } 
  } else {
    $response['pesan'] = "Not Connected";
  }
  echo json_encode($response);
  mysqli_close($conn);
}
?>