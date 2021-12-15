<?php 
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  require 'config.php';

  if ($conn) {
    $id_user = $_POST["id_user"];

    $queryGET = mysqli_query($conn, "SELECT * FROM keranjang WHERE id_user='$id_user'");
    
    if (mysqli_num_rows($queryGET) > 0) {
      $response["kode"] = 1;
      $response["pesan"] = "Data tersedia";
      $response["data"] = array();

      while($ambil = mysqli_fetch_object($queryGET)){
        $F["id_keranjang"] = $ambil->id_keranjang;
        $F["id_user"] = $ambil->id_user;
        $F["id_brg"] = $ambil->id_barang;
        $F["qty"] = $ambil->qty;
        $F["subtotal"] = $ambil->subtotal;
        $idb = $F['id_brg'];
        $qy = $conn->query("SELECT * FROM data_brg WHERE id_brg = '$idb'");
        while($getX = mysqli_fetch_assoc($qy)) {
            $F['barang'] = $getX['barang'];
            $F['harga'] = $getX['hg_jual'];
            $F['gambar'] = $getX['gambar'];
        }
        
        array_push($response["data"], $F);
      }
    } else {
      $response["kode"] = 0;
      $response["pesan"] = "Data tidak tersedia";
    }

  } else {
    $response["kode"] = 404;
    $response["pesan"] = "Not Connected";
  }
  echo json_encode($response);
  mysqli_close($conn);
}
?>