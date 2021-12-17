<?php
class Barang {

    private $mysqli;

    function __construct($conn) {
        $this->mysqli = $conn;
    }

    public function tampil($id = null) {
        $db = $this->mysqli->conn;
        $sql = "SELECT * FROM data_brg";
        if($id != null) {
            $sql .= "WHERE id = $id";
        }
        $query = $db->query($sql) or die ($db->error);
        return $query;
    }

    public function tambah($nama_brg, $jenis_brg, $harga_brg, $stok_brg, $deskripsi_brg, $gambar_brg) {
        $db = $this->mysqli->conn;
        $db->query("INSERT INTO data_brg VALUES ('', '$nama_brg', '$jenis_brg', '$harga_brg', '$stok_brg', '$deskripsi_brg', '$gambar_brg')") or die ($db->error);

    }

    public function edit($sql) {
        $db = $this->mysqli->conn;
        $db->query($sql) or die ($db->error);
    }

    public function hapus($id) {
        $db = $this->mysqli->conn;
        $db->query("DELETE FROM data_brg WHERE id = '$id'") or die ($db->error);
    }

    function __destruct() {
        $db = $this->mysqli->conn;
        $db->close();
    }
}

class Mitra {

    private $mysqli;

    function __construct($conn) {
        $this->mysqli = $conn;
    }

    public function tampil($id = null) {
        $db = $this->mysqli->conn;
        $sql = "SELECT * FROM data_mitra";
        if($id != null) {
            $sql .= "WHERE id = $id";
        }
        $query = $db->query($sql) or die ($db->error);
        return $query;
    }

    public function tambah($nama_mitra, $alamat, $no_tlp, $tgl_gabung, $barang) {
        $db = $this->mysqli->conn;
        $db->query("INSERT INTO data_mitra VALUES ('', '$nama_mitra', '$alamat', '$no_tlp', '$tgl_gabung', '$barang')") or die ($db->error);

    }

    public function edit($sql) {
        $db = $this->mysqli->conn;
        $db->query($sql) or die ($db->error);
    }

    public function hapus($id) {
        $db = $this->mysqli->conn;
        $db->query("DELETE FROM data_mitra WHERE id = '$id'") or die ($db->error);
    }

    function __destruct() {
        $db = $this->mysqli->conn;
        $db->close();
    }
}

class Reseller {

    private $mysqli;

    function __construct($conn) {
        $this->mysqli = $conn;
    }

    public function tampil($id = null) {
        $db = $this->mysqli->conn;
        $sql = "SELECT * FROM data_reseller";
        if($id != null) {
            $sql .= "WHERE id = $id";
        }
        $query = $db->query($sql) or die ($db->error);
        return $query;
    }

    public function tambah($nama_reseller, $alamat, $no_tlp, $tgl_gabung) {
        $db = $this->mysqli->conn;
        $db->query("INSERT INTO data_reseller VALUES ('', '$nama_reseller', '$alamat', '$no_tlp', '$tgl_gabung')") or die ($db->error);

    }

    public function edit($sql) {
        $db = $this->mysqli->conn;
        $db->query($sql) or die ($db->error);
    }

    public function hapus($id) {
        $db = $this->mysqli->conn;
        $db->query("DELETE FROM data_reseller WHERE id = '$id'") or die ($db->error);
    }

    function __destruct() {
        $db = $this->mysqli->conn;
        $db->close();
    }
}

?>