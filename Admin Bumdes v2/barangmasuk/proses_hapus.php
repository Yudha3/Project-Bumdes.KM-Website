<?php
include '../koneksi.php';
$id = $_GET["id"];
//mengambil id yang ingin dihapus

//jalankan query DELETE untuk menghapus data
$query = "DELETE FROM data_msk WHERE id='$id' ";
$hasil_query = mysqli_query($koneksi, $query);

//periksa query, apakah ada kesalahan
if (!$hasil_query) {
    die("Gagal menghapus data: " . mysqli_errno($koneksi) .
        " - " . mysqli_error($koneksi));
} else {
    echo "<script>alert('Data berhasil dihapus.');window.location='../barangMasuk.php';</script>";
}


// include '../koneksi.php';

// if(isset($_POST['hapus'])){
//     $id = $_POST['id'];
//     $id_brg = $_POST['id_brg'];

//     $lihatstock = mysqli_query($koneksi,"select * from data_brg where id_brg='$id_brg'"); //lihat stock barang itu saat ini
//     $stocknya = mysqli_fetch_array($lihatstock); //ambil datanya
//     $stockskrg = $stocknya['jml_stok'];//jumlah stocknya skrg

//     $lihatdataskrg = mysqli_query($koneksi,"select * from data_msk where id='$id'"); //lihat qty saat ini
//     $preqtyskrg = mysqli_fetch_array($lihatdataskrg); 
//     $qtyskrg = $preqtyskrg['jml_masuk'];//jumlah skrg

//     $adjuststock = $stockskrg-$qtyskrg;

//     $queryx = mysqli_query($koneksi,"update data_brg set jml_stok='$adjuststock' where id_brg='$id_brg'");
//     $del = mysqli_query($koneksi,"delete from data_msk where id='$id'");

    
//     //cek apakah berhasil
//     if ($queryx && $del){

//         echo " <div class='alert alert-success'>
//             <strong>Success!</strong> Redirecting you back in 1 seconds.
//           </div>
//         <meta http-equiv='refresh' content='1; url= ../barangMasuk.php'/>  ";
//         } else { echo "<div class='alert alert-warning'>
//             <strong>Failed!</strong> Redirecting you back in 1 seconds.
//           </div>
//          <meta http-equiv='refresh' content='1; url= ../barangMasuk.php'/> ";
//         }
// }
?>