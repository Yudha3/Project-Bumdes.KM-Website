<?php

include '../koneksi.php';

// membuat variabel untuk menampung data dari form
$kode_reseller = $_POST['kd_reseller'];
$nama_reseller   = $_POST['nama_reseller'];
$alamat  = $_POST['alamat'];
$nomer_telepon    = $_POST['no_tlp'];
$tgl_gabung    = $_POST['tgl_gabung'];

if (isset($_POST['bsimpan'])) {
	$simpan = mysqli_query($koneksi, "INSERT INTO data_reseller (kd_reseller,nama_reseller, alamat, no_tlp, tgl_gabung)
										  VALUES ('$_POST[kd_reseller]', '$_POST[nama_reseller]', 
										  		 '$_POST[alamat]', 
										  		 '$_POST[no_tlp]', 
										  		 '$_POST[tgl_gabung]')
										 ");
	if ($simpan) //jika simpan sukses
	{
		echo "<script>
						alert('Simpan data suksess!');
						document.location='../reseller.php';
				     </script>";
	} else {
		echo "<script>
						alert('Simpan data GAGAL!!');
						document.location='../reseller.php';
				     </script>";
	}
}
