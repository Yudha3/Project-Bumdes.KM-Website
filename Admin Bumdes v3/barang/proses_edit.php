<?php
// memanggil file koneksi.php untuk melakukan koneksi database
include '../koneksi.php';

// membuat variabel untuk menampung data dari form
$id = $_POST['id_brg'];
$nama_barang   = $_POST['barang'];
$tanggal_masuk  = $_POST['tgl_masuk'];
$harga_beli    = $_POST['hg_beli'];
$harga_jual    = $_POST['hg_jual'];
// $stok_barang    = $_POST['jml_stok'];
$deskripsi     = $_POST['deskripsi'];
$gambar_produk = $_FILES['gambar']['name'];
$fotolama = $_POST['fotolama'];
//cek dulu jika merubah gambar produk jalankan coding ini
if ($gambar_produk != "") {
    unlink('../images/barang/' . $fotolama);
    $ekstensi_diperbolehkan = array('png', 'jpg', 'jpeg'); //ekstensi file gambar yang bisa diupload 
    $x = explode('.', $gambar_produk); //memisahkan nama file dengan ekstensi yang diupload
    $ekstensi = strtolower(end($x));
    $file_tmp = $_FILES['gambar']['tmp_name'];
    $angka_acak     = rand(1, 999);
    $nama_gambar_baru = $angka_acak . '-' . $gambar_produk; //menggabungkan angka acak dengan nama file sebenarnya
    if (in_array($ekstensi, $ekstensi_diperbolehkan) === true) {
        // unlink('../images/barang' . $fotolama);

        move_uploaded_file($file_tmp, '../images/barang/' . $nama_gambar_baru); //memindah file gambar ke folder gambar

        // jalankan query UPDATE berdasarkan ID yang produknya kita edit
        $query  = "UPDATE data_brg SET barang = '$nama_barang', tgl_masuk = '$tanggal_masuk', hg_beli = '$harga_beli', hg_jual = '$harga_jual',  deskripsi = '$deskripsi', gambar = '$nama_gambar_baru'";
        $query .= "WHERE id_brg = '$id'";
        $result = mysqli_query($koneksi, $query);
        // periska query apakah ada error
        if (!$result) {
            die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
                " - " . mysqli_error($koneksi));
        } else {
            //tampil alert dan akan redirect ke halaman index.php
            //silahkan ganti index.php sesuai halaman yang akan dituju
            echo "<script>alert('Data berhasil diubah.');window.location='../barang.php';</script>";
        }
    } else {
        //jika file ekstensi tidak jpg dan png maka alert ini yang tampil
        echo "<script>alert('Ekstensi gambar yang boleh hanya jpg atau png.');window.location='newBarang.php';</script>";
    }
} else {
    // jalankan query UPDATE berdasarkan ID yang produknya kita edit
    $query  = "UPDATE data_brg SET barang = '$nama_barang', tgl_masuk = '$tanggal_masuk', hg_beli = '$harga_beli', hg_jual = '$harga_jual', deskripsi = '$deskripsi'";
    $query .= "WHERE id_brg = '$id'";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil diubah.');window.location='../barang.php';</script>";
    }
}
