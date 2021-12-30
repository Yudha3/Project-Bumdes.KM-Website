
<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
require("config.php");
$id_user = $_POST["id_user"];

$query_insert = "SELECT * FROM users WHERE id_user = '$id_user'";
$result = mysqli_query($conn, $query_insert);
$cek = mysqli_affected_rows($conn);

if ($cek > 0){
    // $response["kode"] = 1;
    // $response["pesan"] = "Data tersedia";
    // $response["data"] = array();

    // while($ambil = mysqli_fetch_object($result)){
    //     $F["id_user"] = $ambil->id_user;
    //     $F["fullname"] = $ambil->fullname;
    //     $F["username"] = $ambil->username;
    //     $F["email"] = $ambil->email;
    //     $F["no_telp"] = $ambil->no_telp;
    //     $F["foto_profil"] = $ambil->foto_profil;
        
    //     array_push($response["data"], $F);
    // }
    
    $rows = mysqli_fetch_assoc($result);
         $pesan = "BERHASIL";
           $id_user = $rows['id_user'];
           $fullname = $rows['fullname'];
           $jenis_kelamin = $rows['jenis_kelamin'];
           $username = $rows['username'];
           $email = $rows['email'];
           $no_telp = $rows['no_telp'];
           $foto_profil = $rows['foto_profil'];

           $response = array('pesan'=>$pesan, 'id_user' => $id_user, 'fullname' => $fullname, 'jenis_kelamin'=>$jenis_kelamin, 'username' => $username, 'email' => $email, 'no_telp' => $no_telp, 'foto_profil' => $foto_profil);
    
} else {
    $response['pesan'] = "GAGAL";
}
}
echo json_encode($response);
mysqli_close($conn);

?>