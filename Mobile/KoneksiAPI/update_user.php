<?php 

if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require "config.php";
  if ($conn) {
    $id_user = $_POST['id_user'];
    $fullname = $_POST['fullname'];
    $jkelamin = $_POST['jkelamin'];
    $email = $_POST['email'];
    $no_telp = $_POST['no_telp'];
    // $encoded_file = $_POST["EN_IMAGE"];

    $filename = uniqid().".jpeg";

    $queryEmail = mysqli_query($conn, "SELECT * FROM users WHERE email = '$email' ");
    $queryCek = mysqli_query($conn, "SELECT * FROM users WHERE id_user = '$id_user'");
    
    if (mysqli_num_rows($queryEmail) > 0) {
        $get = mysqli_fetch_assoc($queryEmail);
        if ($get['email'] == $email && $get['id_user'] != $id_user ) {
            $response = array('pesan'=>'EMAIL EXIST');
            echo json_encode($response);
            exit;
        } else {
            mysqli_query($conn, "UPDATE users SET fullname = '$fullname', jenis_kelamin = '$jkelamin', email = '$email', no_telp = '$no_telp' WHERE id_user = '$id_user'");
            if (mysqli_affected_rows($conn) > 0) {
            $response = array('pesan' => 'BERHASIL');
          } else {
            $response = array('pesan' => 'GAGAL');
          }
        }
    } else {
        mysqli_query($conn, "UPDATE users SET fullname = '$fullname',jenis_kelamin = '$jkelamin', email = '$email', no_telp = '$no_telp' WHERE id_user = '$id_user' ");
          if (mysqli_affected_rows($conn) > 0) {
            $response = array('pesan' => 'BERHASIL');
          } else {
            $response = array('pesan' => 'GAGAL');
          }
    }
    
    // if (mysqli_num_rows($queryCek) > 0) {
    //   $get = mysqli_fetch_assoc($queryID);
    //   if ($get['email'] == $email && $get['id_user'] != $id_user) {
    //     $response = array('pesan' => 'EMAIL EXIST');
    //     echo json_encode($response);
    //     exit;
    //   } 
    // } else {
    //   $queryUPDATE = mysqli_query($conn, "UPDATE users SET fullname = '$fullname', email = '$email', no_telp = '$no_telp' WHERE id_user = '$id_user' ");
    //       if (mysqli_affected_rows($conn) > 0) {
    //         $response = array('pesan' => 'BERHASIL');
    //       } else {
    //         $response = array('pesan' => 'GAGAL');
    //       }
    // }
    // $data = mysqli_fetch_assoc($queryCek);
    //         $ifullname = $data['fullname'];
    //         $iusername = $data['username'];
    //         $iemail = $data['email'];
    //         $itelp = $data['no_telp'];
    //         $ifotoprofil = $data['foto_profil'];
    // if (foto)
    // if (mysqli_num_rows($queryCek > 0)) {
   
    //   $response = array('pesan' => 'EXIST');
    //   echo json_encode($response);
    //   exit;
    // } else {
    //   $queryUPDATE = mysqli_query($conn, "UPDATE users SET foto_profil = '$filename', fullname = '$fullname', email = '$email', no_telp = '$no_telp' WHERE id_user = '$id_user' ");
    //   file_put_contents("../img/user/".$filename, base64_decode($encoded_file));
    //   if (mysqli_affected_rows($conn) > 0) {
    //     $response = array('pesan' => 'BERHASIL');
    //   } else {
    //     $response = array('pesan' => 'GAGAL');
    //   }
    // // }
    
    // $get = mysqli_fetch_assoc($queryCek);
    // if ($get['email'] == $email && $get['id_user'] != $id_user) {
    //   $response = array('pesan' => 'EMAIL EXIST');
    //   echo json_encode($response);
    //   exit;
    // } elseif ($get['username'] == $username && $get['id_user'] != $id_user ){
    //   $response = array('pesan' => 'USERNAME EXIST');
    //   echo json_encode($response);
    //   exit;
    // } else {
    //   if (!isset($encoded_file) || $encoded_file = null || $encoded_file == "" || $encoded_file = undefined){
    //     $queryUPDATE = mysqli_query($conn, "UPDATE users SET fullname = '$fullname', email = '$email', no_telp = '$no_telp' WHERE id_user = '$id_user' ");
    //     if (mysqli_affected_rows($conn) > 0) {
    //       $response = array('pesan' => 'BERHASIL');
    //     } else {
    //       $response = array('pesan' => 'GAGAL');
    //     }
    //   } else {
    //     $queryUPDATE = mysqli_query($conn, "UPDATE users SET foto_profil = '$filename', fullname = '$fullname', email = '$email', no_telp = '$no_telp' WHERE id_user = '$id_user' ");
    //     file_put_contents("../img/user/".$filename, base64_decode($encoded_file));
    //     if (mysqli_affected_rows($conn) > 0) {
    //       $response = array('pesan' => 'BERHASIL');
    //     } else {
    //       $response = array('pesan' => 'GAGAL');
    //     }
    //   }
    // }
    
  } else {
    $response = array('pesan' => 'NOT CONNECTED');
  }
  mysqli_close($conn);
  echo json_encode($response);
}
?>