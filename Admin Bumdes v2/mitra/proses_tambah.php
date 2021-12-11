<?php

include '../koneksi.php';

// membuat variabel untuk menampung data dari form
$nama_mitra   = $_POST['nama_mitra'];
$alamat  = $_POST['alamat'];
$nomer_telepon    = $_POST['no_tlp'];
$tgl_gabung    = $_POST['tgl_gabung'];
$barang     = $_POST['barang'];

if (isset($_POST['bsimpan'])) {
    $simpan = mysqli_query($koneksi, "INSERT INTO data_mitra (nama_mitra, alamat, no_tlp, tgl_gabung, barang)
										  VALUES ('$_POST[nama_mitra]', 
										  		 '$_POST[alamat]', 
										  		 '$_POST[no_tlp]', 
										  		 '$_POST[tgl_gabung]',
                                                '$_POST[barang]')");
    if ($simpan) //jika simpan sukses
    {
        echo "<script>
						alert('Simpan data suksess!');
						document.location='../mitra.php';
				     </script>";
    } else {
        echo "<script>
						alert('Simpan data GAGAL!!');
						document.location='../mitra.php';
				     </script>";
    }
}
