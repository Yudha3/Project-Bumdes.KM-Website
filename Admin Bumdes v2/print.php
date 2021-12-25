    <?php 
	ob_start(); 
	?>
    <html>

    <head>
    	<title>Cetak PDF</title>
    	<style>
    		.table {
    			border-collapse: collapse;
    			table-layout: fixed;
    			width: 630px;
    		}

    		.table th {
    			padding: 5px;
    		}

    		.table td {
    			word-wrap: break-word;
    			width: 20%;
    			padding: 5px;
    		}
    	</style>
    </head>

    <body>
    	<?php  // Load file koneksi.php  
		include "koneksi.php";
		$tgl_awal = @$_GET['dari']; // Ambil data tgl_awal sesuai input (kalau tidak ada set kosong)  
		$tgl_akhir = @$_GET['ke']; // Ambil data tgl_awal sesuai input (kalau tidak ada set kosong)  
		if (empty($tgl_awal) or empty($tgl_akhir)) { // Cek jika tgl_awal atau tgl_akhir kosong, maka :    // Buat query untuk menampilkan semua data transaksi    
			$query = "SELECT * FROM report_msk";
			$label = "Semua Data Transaksi";
		} else { // Jika terisi    // Buat query untuk menampilkan data transaksi sesuai periode tanggal    
			$query = "SELECT * FROM report_msk WHERE (tgl_msk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "')";
			$tgl_awal = date('d-m-Y', strtotime($tgl_awal)); // Ubah format tanggal jadi dd-mm-yyyy    
			$tgl_akhir = date('d-m-Y', strtotime($tgl_akhir)); // Ubah format tanggal jadi dd-mm-yyyy    
			$label = 'Periode Tanggal ' . $tgl_awal . ' s/d ' . $tgl_akhir;
		}
		?>
    	<h4 style="margin-bottom: 5px;">Data Transaksi</h4> 
		<?php echo $label ?>
    	<table class="table" border="1" width="100%" style="margin-top: 10px;">
    		<tr>
    			<th>No</th>
    			<th>ID Transaksi</th>
    			<th>Tanggal Transaksi</th>
    			<th>Barang</th>
    			<th>Pengirim</th>
    			<th>Jumlah</th>
    			<th>Total Harga</th>
    			<th>Keterangan</th>
    		</tr>
    		<?php
			$sql = mysqli_query($koneksi, $query); // Eksekusi/Jalankan query dari variabel $query    
			$row = mysqli_num_rows($sql); // Ambil jumlah data dari hasil eksekusi $sql    
			if ($row > 0) { // Jika jumlah data lebih dari 0 (Berarti jika data ada)      
				while ($data = mysqli_fetch_array($sql)) { // Ambil semua data dari hasil eksekusi $sql        
					$tgl = date('d-m-Y', strtotime($data['tgl_msk'])); // Ubah format tanggal jadi dd-mm-yyyy        
					echo "<tr>";
					echo "<td>" . $data['id_transaksi'] . "</td>";
					echo "<td>" . $tgl . "</td>";
					echo "<td>" . $data['barang'] . "</td>";
					echo "<td>" . $data['pengirim'] . "</td>";
					echo "<td>" . $data['jml_masuk'] . "</td>";
					echo "<td>" . $data['total_hrg'] . "</td>";
					echo "<td>" . $data['keterangan'] . "</td>";
					echo "</tr>";
				}
			} else { // Jika data tidak ada      
				echo "<tr><td colspan='5'>Data tidak ada</td></tr>";
			}
			?>
    	</table>
    </body>

    </html>
    <?php
$html = ob_get_contents();
ob_end_clean();
 
$mpdf->WriteHTML(utf8_encode($html));
$mpdf->Output("".$nama_dokumen.".pdf" ,'D');
$db1->close();
?>