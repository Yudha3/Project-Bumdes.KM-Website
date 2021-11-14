<?php 
require_once 'conn.php';

if ( $conn ) {
    $email = $_POST['email'];
    $password = $_POST['password'];

    $query = "SELECT * FROM users WHERE email='$email' AND password='$password' ";
    $result = mysqli_query($conn, $query);
    $response = array();

    $row = mysqli_num_rows($result);
    // $rows = mysqli_fetch_array($result);
    if ( $row > 0 ) {
            array_push($response, array(
                "status" => "OK"
            ));
        
    } else {
        array_push($response, array(
            "status" => "FAILED"
        ));
    }
} else {
    array_push($response, array(
        "status" => "FAILED"
    ));
}

echo json_encode(array("server_response" => $response));
mysqli_close($conn);
?>