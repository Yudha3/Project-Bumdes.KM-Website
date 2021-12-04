<?php
//menggunakan Framework PHPUnit
use PHPUnit\Framework\TestCase;

// Class yang akan di TEST.
require_once "Login-Mock.php";

// Class untuk run Testing.
class Test_login extends TestCase
{
    //membuat sebuah fungsi
    public function testLoginPost()
    {
        //class yang akan kita pakai
        $insert = new LoginMock();

        //memasukkan username dan password sesuai yang ada pada database
        $email="aliffrianto2@gmail.com";
        $password="567456";
        
        $hasil= $insert->loginProcess($email, $password);

        //memeriksa nilai yang dihasilkan dari fungsi apakah sama dengan nilai ekspektasi kita atau tidak.
        //jika benar akan menampilkan berhasil
        $this->assertEquals($hasil,'berhasil');
    }
}