<?php

include '../koneksi.php';

// membuat variabel untuk menampung data dari form
  $nama_mitra   = $_POST['nama_mitra'];
  $alamat  = $_POST['alamat'];
  $nomer_telepon    = $_POST['no_tlp'];
  $tgl_gabung    = $_POST['tgl_gabung'];
  $barang     = $_POST['barang'];

	if(isset($_POST['bsimpan'])) {
    $simpan = mysqli_query($koneksi, "INSERT INTO data_mitra (nama_mitra, alamat, no_tlp, tgl_gabung, barang)
										  VALUES ('$_POST[nama_mitra]', 
										  		 '$_POST[alamat]', 
										  		 '$_POST[no_tlp]', 
										  		 '$_POST[tgl_gabung]',
                           '$_POST[barang]')
										 ");
			if($simpan) //jika simpan sukses
			{
				echo "<script>
						alert('Simpan data suksess!');
						document.location='Mitra.php';
				     </script>";
			}
			else
			{
				echo "<script>
						alert('Simpan data GAGAL!!');
						document.location='Mitra.php';
				     </script>";
			}
  }


// if (isset($_POST['submit'])) {
//   $nama_mitra   = $_POST['nama_mitra'];
//   $alamat  = $_POST['alamat'];
//   $nomer_telepon    = $_POST['no_tlp'];
//   $tanggal_gabung    = $_POST['tgl_gabung'];
//   $barang     = $_POST['barang'];
 
  
// 	$query = "INSERT INTO data_mitra (nama_mitra, alamat, no_tlp, tgl_gabung, barang) VALUES ('$nama_mitra', '$alamat', '$nomer_telepon', '$tanggal_gabung', '$barang')";
// 	$statement = oci_parse($conn,$query);
// 	$r = oci_execute($statement,OCI_DEFAULT);
// 	 $res = oci_commit($conn);
//   if ($res) {
//     // pesan jika data tersimpan
//     echo "<script>alert('Data Barang berhasil ditambahkan'); 
// 	window.location.href='data_barang.php'</script>";
//   } else {
//     // pesan jika data gagal disimpan
//     echo "<script>alert('Data transaksi gagal ditambahkan');
// 	window.location.href='data_barang.php'</script>";
//   }
// } else {
//   //jika coba akses langsung halaman ini akan diredirect ke halaman index
//   header('Location: data_barang.php'); 
// }
// memanggil file koneksi.php untuk melakukan koneksi database
// include 'koneksi.php';

// 	// membuat variabel untuk menampung data dari form
//   $nama_mitra   = $_POST['nama_mitra'];
//   $alamat  = $_POST['alamat'];
//   $nomer_telepon    = $_POST['no_tlp'];
//   $tanggal_gabung    = $_POST['tgl_gabung'];
//   $barang     = $_POST['barang'];
//   $gambar_produk = $_FILES['gambar_brg']['name'];


//cek dulu jika ada gambar produk jalankan coding ini
// if($gambar_produk != "") {
//   $ekstensi_diperbolehkan = array('png','jpg'); //ekstensi file gambar yang bisa diupload 
//   $x = explode('.', $gambar_produk); //memisahkan nama file dengan ekstensi yang diupload
//   $ekstensi = strtolower(end($x));
//   $file_tmp = $_FILES['gambar_brg']['tmp_name'];   
//   $angka_acak     = rand(1,999);
//   $nama_gambar_baru = $angka_acak.'-'.$gambar_produk; //menggabungkan angka acak dengan nama file sebenarnya
        // if(in_array($ekstensi, $ekstensi_diperbolehkan) === true)  {     
//                 move_uploaded_file($file_tmp, 'img/gambar/'.$nama_gambar_baru); //memindah file gambar ke folder gambar
                  // jalankan query INSERT untuk menambah data ke database pastikan sesuai urutan (id tidak perlu karena dibikin otomatis)
//                   $query = "INSERT INTO data_mitra (nama_mitra, alamat, no_tlp, tgl_gabung, barang) VALUES ('$nama_mitra', '$alamat', '$nomer_telepon', '$tanggal_gabung', '$barang')";
//                   $result = mysqli_query($koneksi, $query);
//                   // periska query apakah ada error
//                   if(!$result){
//                       die ("Query gagal dijalankan: ".mysqli_errno($koneksi).
//                            " - ".mysqli_error($koneksi));
//                   } else {
//                     //tampil alert dan akan redirect ke halaman index.php
//                     //silahkan ganti index.php sesuai halaman yang akan dituju
//                     echo "<script>alert('Data berhasil ditambah.');window.location='Mitra.php';</script>";
//                   }

//             } else {     
//              //jika file ekstensi tidak jpg dan png maka alert ini yang tampil
//                 echo "<script>alert('Ekstensi gambar yang boleh hanya jpg atau png.');window.location='newMitra.php';</script>";
//             }
// } else {
//    $query = "INSERT INTO data_mitra (nama_mitra, alamat, no_tlp, tgl_gabung, barang) VALUES ('$nama_mitra', '$alamat', '$nomer_telepon', '$tanggal_gabung', '$barang', null)";
//                   $result = mysqli_query($koneksi, $query);
//                   // periska query apakah ada error
//                   if(!$result){
//                       die ("Query gagal dijalankan: ".mysqli_errno($koneksi).
//                            " - ".mysqli_error($koneksi));
//                   } else {
//                     //tampil alert dan akan redirect ke halaman index.php
//                     //silahkan ganti index.php sesuai halaman yang akan dituju
//                     echo "<script>alert('Data berhasil ditambah.');window.location='Mitra.php';</script>";
//                   }
// }

 
