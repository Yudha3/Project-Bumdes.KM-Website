<?php
session_start();
if (isset($_SESSION['LOGIN'])) {
	header("location: index.php");
	exit();
}
require('koneksi.php');
if(isset($_POST['login'])){
    $username = $_POST['username'];
    $password = $_POST['password'];

    if(!empty(trim($username)) && !empty(trim($password))){
        $query = "SELECT * FROM admin WHERE username = '$username'";
        $result = mysqli_query($koneksi, $query);
        $num = mysqli_num_rows($result);

        while($row = mysqli_fetch_array($result)){
            $userVal = $row['username'];
            $passVal = $row['password'];
            $userName = $row['user_fullname'];
        }

        if($num != 0) {
            if($userVal==$username && $passVal==$password){
				$_SESSION['LOGIN'] =1;
				session_start();
                $_SESSION['name'] = $userName;
                header('Location: index.php?user_fullname=' . urlencode($userName));
            }else{
                $error = 'username atau password salah!';
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
	<title>Login Bumdes</title>
	<link rel="stylesheet" type="text/css" href="style/style_login.css">
	<link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
	<script src="https://kit.fontawesome.com/a81368914c.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<img class="wave" src="images/17545.jpg">
	<div class="container">
		<div class="img">
			<img src="images/undraw_web_devices_re_m8sc.svg">
		</div>
		<div class="login-content">
			<form action="" method="POST">
				<img src="images/undraw_profile_pic_ic-5-t.svg">
				<h2 class="title">Welcome</h2>
				<div class="input-div one">
					<div class="i">
						<i class="fas fa-user"></i>
					</div>
					<div class="div">
						<h5>Username</h5>
						<input type="text" class="input" name="username" autofocus>
					</div>
				</div>
				<div class="input-div pass">
					<div class="i"> 
						<i class="fas fa-lock"></i>
					</div>
					<div class="div">
						<h5>Password</h5>
						<input type="password" class="input" name="password">
					</div>
            	</div>
            	<a href="#">Forgot Password?</a>
				<p>username: admin, password: admin.</p>
            	<input type="submit" class="btn" name="login" value="Login">
            </form>
        </div>
    </div>
    <script type="text/javascript" src="assets/js/main.js"></script>
</body>
</html>
