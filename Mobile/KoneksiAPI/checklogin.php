<?php 
require_once 'config.php';

if ( $conn ) {
    $email = $_POST['email'];
    $password = $_POST['password'];

    $query = "SELECT * FROM users WHERE email='$email' AND password='$password' ";
    $result = mysqli_query($conn, $query);
    // $response = array();

    $row = mysqli_num_rows($result);
    // $rows = mysqli_fetch_array($result);
    if ( $row > 0 ) {
        // $response['pesan'] = "BERHASIL";
        // $response['data'] = array();

        // while ($ambil = mysqli_fetch_object($result)){
        //     $F["id_user"] = $ambil->id_user;
        //     $F["fullname"] = $ambil->fullname;
        //     $F["username"] = $ambil->username;
        //     $F["email"] = $ambil->email;
        //     $F["no_telp"] = $ambil->no_telp;
        //     $F["foto_profil"] = $ambil->foto_profil;
            
        //     array_push($response['data'], $F);
        // }
        
            $rows = mysqli_fetch_assoc($result);
         $pesan = "BERHASIL";
           $id_user = $rows['id_user'];
           $fullname = $rows['fullname'];
           $username = $rows['username'];
           $email = $rows['email'];
           $no_telp = $rows['no_telp'];
           $foto_profil = $rows['foto_profil'];

           $response = array('pesan'=>$pesan, 'id_user' => $id_user, 'fullname' => $fullname, 'username' => $username, 'email' => $email, 'no_telp' => $no_telp, 'foto_profil' => $foto_profil);
      

        // $temp = array();
	
        // $temp['id_user'] = $id_user;
        // $temp['fullname'] = $fullname;
        // $temp['username'] = $username;
        // $temp['email'] = $email;
        //     array_push($response, $temp);
        
    } else {
        $response['pesan'] = "WRONG";
    }
} else {
    $response['pesan'] = "FAILED";
}

echo json_encode($response);
mysqli_close($conn);
?>