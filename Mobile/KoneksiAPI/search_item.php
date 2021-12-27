<?php

if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require("config.php");

  $keyword = $_POST['keyword'];

  $query_insert = "SELECT * FROM data_brg WHERE barang LIKE '%$keyword%' OR deskripsi LIKE '%$keyword%' ";
  $result = mysqli_query($conn, $query_insert);
  $cek = mysqli_num_rows($result);

  if ($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($result)){
        $F["id_brg"] = $ambil->id_brg;
        $F["barang"] = $ambil->barang;
        $F["hg_jual"] = $ambil->hg_jual;
        $F["jml_stok"] = $ambil->jml_stok;
        $F["gambar"] = $ambil->gambar;
        $F["deskripsi"] = $ambil->deskripsi;
        
        array_push($response["data"], $F);
    }
    
  } else {
    $response["kode"] = 0;
    $response["pesan"] = "Data tidak tersedia";
  }
}
echo json_encode($response);
mysqli_close($conn);

?>