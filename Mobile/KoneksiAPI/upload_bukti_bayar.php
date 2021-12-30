<?php 
if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require 'config.php';
  $id_transaksi = $_POST['id_transaksi'];

  if ($_FILES['gambar']) {
    $namaFile = $_FILES['gambar']['name'];
    $error = $_FILES['gambar']['error'];
    $ukuranFile = $_FILES['gambar']['size'];
    $tmpFile = $_FILES['gambar']['tmp_name'];

    $ekstensiFileValid = ['jpg', 'jpeg', 'png'];
    $ekstensiFile = explode('.', $namaFile);
    $ekstensiFile = strtolower(end($ekstensiFile));
    if ( $error === 4) {
      $response = array('pesan' => 'EKSTENSI SALAH');
      echo json_encode($response);
      exit;
    } 
    if (!in_array($ekstensiFile, $ekstensiFileValid)) {
      $response = array('pesan' => 'EKSTENSI SALAH');
      echo json_encode($response);
      exit;
    } 
      $namaFileBaru = uniqid();
      $namaFileBaru .= '.';
      $namaFileBaru .= $ekstensiFile;

      move_uploaded_file($tmpFile, "../images/pembayaran/" . $namaFileBaru);

      mysqli_query($conn, "UPDATE transaksi SET bukti_tf = '$namaFileBaru' WHERE id_transaksi = '$id_transaksi' ");
      if (mysqli_affected_rows($conn)) {
        $response = array('pesan' => 'BERHASIL');
      } else {
        $response = array('pesan' => 'GAGAL');
      }

      echo json_encode($response);
  }
}
mysqli_close($conn);
?>