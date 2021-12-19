<?php
// memanggil file koneksi.php untuk melakukan koneksi database
include '../koneksi.php';

// membuat variabel untuk menampung data dari form
$nama_barang   = $_POST['barang'];
$tanggal_masuk  = $_POST['tgl_masuk'];
$harga_beli    = $_POST['hg_beli'];
$harga_jual    = $_POST['hg_jual'];
$stok_barang    = $_POST['jml_stok'];
$deskripsi     = $_POST['deskripsi'];

// $extensi = explode(".", $_FILES['gambar']['name']);
// $gambar_brg = "brg-" . round(microtime(true)) . "." . end($extensi);
// $sumber = $_FILES['gambar']['tmp_name'];

// $upload = move_uploaded_file($sumber, "../images/barang/" . $gambar_brg);
// if ($upload) {
//     $query = "INSERT INTO data_brg (barang, tgl_masuk, hg_beli, hg_jual, jml_stok, deskripsi, gambar) VALUES ('$nama_barang', '$tanggal_masuk', '$harga_beli', '$harga_jual', '$stok_barang', '$deskripsi', '$gambar_brg')";
//     $result = mysqli_query($koneksi, $query);
//     // periska query apakah ada error
//     if (!$result) {
//         die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
//             " - " . mysqli_error($koneksi));
//     } else {
//         //tampil alert dan akan redirect ke halaman index.php
//         //silahkan ganti index.php sesuai halaman yang akan dituju
//         echo "<script>alert('Data berhasil ditambah.');window.location='../barang.php';</script>";
//     }
// } else {
//     echo "<script>alert('Upload gambar gagal!')</script>";
// }
$gambar_produk = $_FILES['gambar']['name'];
//cek dulu jika ada gambar produk jalankan coding ini
if ($gambar_produk != "") {
    $ekstensi_diperbolehkan = array('png', 'jpg', 'jpeg'); //ekstensi file gambar yang bisa diupload 
    $x = explode('.', $gambar_produk); //memisahkan nama file dengan ekstensi yang diupload
    $ekstensi = strtolower(end($x));
    $file_tmp = $_FILES['gambar']['tmp_name'];
    $angka_acak     = rand(1, 999);
    $nama_gambar_baru = $angka_acak . '-' . $gambar_produk; //menggabungkan angka acak dengan nama file sebenarnya
    if (in_array($ekstensi, $ekstensi_diperbolehkan) === true) {
        move_uploaded_file($file_tmp, '../images/barang/' . $nama_gambar_baru); //memindah file gambar ke folder gambar
        // jalankan query INSERT untuk menambah data ke database pastikan sesuai urutan (id tidak perlu karena dibikin otomatis)
        $query = "INSERT INTO data_brg (barang, tgl_masuk, hg_beli, hg_jual, jml_stok, deskripsi, gambar) VALUES ('$nama_barang', '$tanggal_masuk', '$harga_beli', '$harga_jual', '$stok_barang', '$deskripsi', '$nama_gambar_baru')";
        $result = mysqli_query($koneksi, $query);
        // periska query apakah ada error
        if (!$result) {
            die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
                " - " . mysqli_error($koneksi));
        } else {
            //tampil alert dan akan redirect ke halaman index.php
            //silahkan ganti index.php sesuai halaman yang akan dituju
            echo "<script>alert('Data berhasil ditambah.');window.location='../barang.php';</script>";
        }
    } else {
        //jika file ekstensi tidak jpg dan png maka alert ini yang tampil
        echo "<script>alert('Ekstensi gambar yang boleh hanya jpg atau png.');window.location='newBarang.php';</script>";
    }
} else {
    $query = "INSERT INTO data_brg (barang, tgl_masuk, hg_beli, hg_jual, jml_stok, deskripsi, gambar) VALUES ('$nama_barang', '$tanggal_masuk', '$harga_beli', '$harga_jual', '$stok_barang', '$deskripsi', null)";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil ditambah.');window.location='../barang.php';</script>";
    }
}
