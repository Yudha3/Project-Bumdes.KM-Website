<?php 
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  require 'config.php';

  if ($conn) {
    $id_user = $_POST['id_user'];

    $queryGET = mysqli_query($conn, "SELECT * FROM transaksi WHERE id_user = '$id_user' ORDER BY id_transaksi DESC");
    
    if (mysqli_num_rows($queryGET) > 0) {
      $response["kode"] = 1;
      $response["pesan"] = "Data tersedia";
      $response["data"] = array();

      while($ambil = mysqli_fetch_object($queryGET)){
        $F["id_transaksi"] = $ambil->id_transaksi;
        $F["tgl_transaksi"] = $ambil->tgl_transaksi;
        $F["id_user"] = $ambil->id_user;
        $F["penerima"] = $ambil->nama_penerima;
        $F["alamat"] = $ambil->alamat;
        $F["no_telp"] = $ambil->no_telp;
        $F["total_transaksi"] = $ambil->total_transaksi;
        $F["status"] = $ambil->status;
        $idt = $F['id_transaksi'];
        $qy = $conn->query("SELECT id_brg FROM transaksi_produk WHERE id_transaksi = '$idt' ORDER BY id_transaksi_produk DESC LIMIT 1");
        $getidb = mysqli_fetch_assoc($qy);
        $idb = $getidb['id_brg'];
        $queryX = $conn->query("SELECT gambar FROM data_brg WHERE id_brg = '$idb'");
        $getGambar = mysqli_fetch_assoc($queryX); 
        // while($getX = mysqli_fetch_assoc($qy)) {
        //     $F['harga'] = $getX['hg_jual'];
        //     $F['gambar'] = $getX['gambar'];
        // }
        $F['gambar'] = $getGambar['gambar'];
        
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