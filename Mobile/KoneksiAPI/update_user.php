<?php 

if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require "config.php";
  if ($conn) {
    $id_user = $_POST['id_user'];
    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $no_telp = $_POST['no_telp'];
    $encoded_file = $_POST["EN_IMAGE"];

    $filename = uniqid().".jpeg";

    $queryCek = mysqli_query($conn, "SELECT * FROM users WHERE email = '$email'");
    if (mysqli_num_rows($queryCek > 0)) {
      $response = array('pesan' => 'EXIST');
      echo json_encode($response);
      exit;
    } else {
      $queryUPDATE = mysqli_query($conn, "UPDATE users SET foto_profil = '$filename', fullname = '$fullname', email = '$email', no_telp = '$no_telp' WHERE id_user = '$id_user' ");
      file_put_contents("../img/user/".$filename, base64_decode($encoded_file));
      if (mysqli_affected_rows($conn) > 0) {
        $response = array('pesan' => 'BERHASIL');
      } else {
        $response = array('pesan' => 'GAGAL');
      }
    }
  } else {
    $response = array('pesan' => 'NOT CONNECTED');
  }
  mysqli_close($conn);
  echo json_encode($response);
}
?>