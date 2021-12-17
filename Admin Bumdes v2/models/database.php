<?php
class Database {
    private $host;
    private $user;
    private $pass;
    private $nama_db;
    public $conn;

    function __construct($host, $user, $pass, $nama_db) {
        $this->host = $host;
        $this->user = $user;
        $this->pass = $pass;
        $this->nama_db = $nama_db;

        $this->conn = new mysqli($this->host, $this->user, $this->pass, $this->nama_db) or die (mysqli_error());
        if(!$this->conn) {
            return false;
        } else {
            return true;
        }
    }
}

?>