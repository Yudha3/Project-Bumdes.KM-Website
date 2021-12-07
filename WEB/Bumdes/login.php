<?php
require('koneksi.php');
// $koneksi = mysqli_connect("localhost", "root", "", "user");
session_start();
if(isset($_POST['submit'])){
    $username = $_POST['txt_username'];
    $pass = $_POST['txt_pass'];

    if(!empty(trim($email)) && !empty(trim($pass))){
        $query = "SELECT * FROM user WHERE username = '$username'";
        $result = mysqli_query($koneksi, $query);
        $num = mysqli_num_rows($result);

        while($row = mysqli_fetch_array($result)){
            $id = $row['id'];
            $userVal = $row['username'];
            $passVal = $row['password'];
            $userName = $row['user_fullname'];
        }

        if($num != 0) {
            if($userVal==$email && $passVal==$pass){
                $_SESSION['id'] = $id;
                $_SESSION['name'] = $userName;
                header('Location: home.php?user_fullname=' . urlencode($userName));
            }else{
                $error = 'user atau password salah!';
                header('Location: login.php');
            }
        }else{
            $error = 'Username tidak boleh kosong!';
            header('Location: login.php');
        }
    }else{
        $error = 'Data tidak boleh kosong!';
        echo $error;
    }
}

?>

<!DOCTYPE html>
<html>
<head>
	<title>Animated Login Form</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
	<script src="https://kit.fontawesome.com/a81368914c.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<img class="wave" src="img/17545.jpg">
	<div class="container">
		<div class="img">
			<img src="img/undraw_web_devices_re_m8sc.svg">
		</div>
		<div class="login-content">
			<form action="login.html">
				<img src="img/undraw_profile_pic_ic-5-t.svg">
				<h2 class="title">Welcome</h2>
           		<div class="input-div one">
           		   <div class="i">
           		   		<i class="fas fa-user"></i>
           		   </div>
           		   <div class="div">
           		   		<h5>Username</h5>
           		   		<input type="text" class="input">
           		   </div>
           		</div>
           		<div class="input-div pass">
           		   <div class="i"> 
           		    	<i class="fas fa-lock"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Password</h5>
           		    	<input type="password" class="input">
            	   </div>
            	</div>
            	<a href="#">Forgot Password?</a>
            	<input type="submit" class="btn" value="Login">
            </form>
        </div>
    </div>
    <script type="text/javascript" src="js/main.js"></script>
</body>
</html>
