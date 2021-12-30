<?php

require("config.php");

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    $id = $_POST["id_keranjang"];
    
    $query_insert = "DELETE FROM keranjang WHERE id_keranjang = '$id'";
    $result = mysqli_query($conn, $query_insert);
    $cek = mysqli_affected_rows($conn);

    if ($cek > 0){
        $response["pesan"] = "BERHASIL";
    } else {
        $response["pesan"] = "GAGAL";
    }
} else {
    $response["pesan"] = "NOT CONNECTED";
}

echo json_encode($response);
mysqli_close($conn);

?>