<?php 

if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require "config.php";
  if ($conn) {
    $id_user = $_POST['id_user'];
    $encoded_file = $_POST["EN_IMAGE"];

    $getMe = mysqli_fetch_assoc(mysqli_query($conn, "SELECT * FROM users WHERE id_user = '$id_user' "));
    $oldPhoto = $getMe['foto_profil'];
    $file = "../img/user/".$oldPhoto;
    
    $filename = uniqid().".jpeg";
    
    mysqli_query($conn, "UPDATE users SET foto_profil = '$filename' WHERE id_user = '$id_user'");
    
    if (mysqli_affected_rows($conn) > 0) {
        file_put_contents("../img/user/".$filename, base64_decode($encoded_file));
        unlink($file);
        $response = array('pesan' => 'BERHASIL');
    } else {
        $response = array('pesan' => 'GAGAL');
    }
  } else {
    $response = array('pesan' => 'NOT CONNECTED');
  }
  mysqli_close($conn);
  echo json_encode($response);
}
?>