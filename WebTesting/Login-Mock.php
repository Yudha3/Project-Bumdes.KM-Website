<?php

class LoginMock
{
    //membuat sebuah fungsi loginProcess pada class Login-Mock
    public function loginProcess($email, $password){
        if ($email == 'aliffrianto2@gmail.com')
            return 'SUCCESSFULL';
        else
            return 'FAILED';
    }
}
?>