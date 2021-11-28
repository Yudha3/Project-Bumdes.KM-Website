<?php
include '../koneksi.php';

// membuat variabel untuk menampung data dari form
if (isset($_POST['submit'])) {
    $id = $_POST['id'];
    $nama_reseller   = $_POST['nama_reseller'];
    $alamat  = $_POST['alamat'];
    $nomer_telepon    = $_POST['no_tlp'];
    $tgl_gabung    = $_POST['tgl_gabung'];

	$query  = "UPDATE data_reseller SET nama_reseller = '$nama_reseller', alamat = '$alamat', no_tlp = '$nomer_telepon', tgl_gabung = '$tgl_gabung'";
    $query .= "WHERE id = '$id'";
    $result = mysqli_query($koneksi, $query);
    // periska query apakah ada error
    if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        echo "<script>alert('Data berhasil diubah.');window.location='reseller.php';</script>";
    }

}

//   if($_GET['id'])
// 		{
// 			//Data akan di edit
// 			$edit = mysqli_query($koneksi, "UPDATE data_mitra set
// 											 	nama_mitra = '$_POST[nama_mitra]',
// 											 	alamat = '$_POST[alamat]',
// 												nomer_telepon = '$_POST[no_tlp]',
// 											 	tgl_gabung = '$_POST[tgl_gabung]',
//                                                 barang = '$_POST[barang]'
// 											 WHERE id = '$_GET[id]'
// 										   ");
// 			if($edit) //jika edit sukses
// 			{
// 				echo "<script>
// 						alert('Edit data suksess!');
// 						document.location='Mitra.php';
// 				     </script>";
// 			}
// 			else
// 			{
// 				echo "<script>
// 						alert('Edit data GAGAL!!');
// 						document.location='Mitra.php';
// 				     </script>";
// 			}
// 		}
