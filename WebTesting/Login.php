<?php

class Login
{
    //membuat sebuah fungsi yang terdapat 2 parameter, yaitu username dan password
    public function loginProcess($email, $password){
        // menghubungkan php dengan koneksi database
        include 'koneksi.php';

        // menyeleksi data user dengan username dan password yang sesuai
        $login = mysqli_query($koneksi,"SELECT * from user where email='$email' and password='$password'");
        // menghitung jumlah data yang ditemukan
        $ambil = mysqli_num_rows($login);

        // cek apakah username dan password di temukan pada database
        if($ambil>0){
            $data = mysqli_fetch_assoc($login);

            //menampilkan kata berhasil jika logi berhasil
            return 'berhasil';

        }else{
            //jika gagal tampil tulisan gagal
            return 'gagal';
        }
    }
}
?>