<?php
// memanggil file koneksi.php untuk melakukan koneksi database
include 'koneksi.php';

// membuat variabel untuk menampung data dari form
$id = $_POST['id'];
$nama_barang   = $_POST['nama_brg'];
$jenis_barang  = $_POST['jenis_brg'];
$harga_barang    = $_POST['harga_brg'];
$stok_barang    = $_POST['stok_brg'];
$deskripsi     = $_POST['deskripsi_brg'];
$gambar_produk = $_FILES['gambar_brg']['name'];
//cek dulu jika merubah gambar produk jalankan coding ini
if ($gambar_produk != "") {
    $ekstensi_diperbolehkan = array('png', 'jpg'); //ekstensi file gambar yang bisa diupload 
    $x = explode('.', $gambar_brg); //memisahkan nama file dengan ekstensi yang diupload
    $ekstensi = strtolower(end($x));
    $file_tmp = $_FILES['gambar_brg']['tmp_name'];
    $angka_acak     = rand(1, 999);
    $nama_gambar_baru = $angka_acak . '-' . $gambar_produk; //menggabungkan angka acak dengan nama file sebenarnya
    if (in_array($ekstensi, $ekstensi_diperbolehkan) === true) {
        move_uploaded_file($file_tmp, 'img/gambar/' . $nama_gambar_baru); //memindah file gambar ke folder gambar

        // jalankan query UPDATE berdasarkan ID yang produknya kita edit
        $query  = "UPDATE data_brg SET nama_brg = '$nama_produk', jenis_brg = '$jenis_barang', harga_brg = '$harga_barang', stok_brg = '$stok_barang', deskripsi_brg = '$deskripsi', gambar_brg = '$nama_gambar_baru'";
        $query .= "WHERE id = '$id'";
        $result = mysqli_query($koneksi, $query);
        // periska query apakah ada error
        if (!$result) {
            die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
                " - " . mysqli_error($koneksi));
        } else {
            //tampil alert dan akan redirect ke halaman index.php
            //silahkan ganti index.php sesuai halaman yang akan dituju
            echo "<script>alert('Data berhasil diubah.');window.location='barang.php';</script>";
        }
    } else {
        //jika file ekstensi tidak jpg dan png maka alert ini yang tampil
        echo "<script>alert('Ekstensi gambar yang boleh hanya jpg atau png.');window.location='newBarang.php';</script>";
    }
} else {
    // jalankan query UPDATE berdasarkan ID yang produknya kita edit
    $query  = "UPDATE data_brg SET nama_brg = '$nama_barang', jenis_brg = '$jenis_barang', harga_brg = '$harga_barang', stok_brg = '$stok_barang', deskripsi_brg = '$deskripsi'";
    $query .= "WHERE id = '$id'";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil diubah.');window.location='barang.php';</script>";
    }
}
