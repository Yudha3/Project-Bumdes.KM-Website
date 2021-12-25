<?php
include '../koneksi.php';
$id = $_GET["id"];
//mengambil id yang ingin dihapus

//jalankan query DELETE untuk menghapus data
$query = "DELETE FROM data_klr WHERE id='$id' ";
$hasil_query = mysqli_query($koneksi, $query);

//periksa query, apakah ada kesalahan
if (!$hasil_query) {
    die("Gagal menghapus data: " . mysqli_errno($koneksi) .
        " - " . mysqli_error($koneksi));
} else {
    echo "<script>alert('Data berhasil dihapus.');window.location='../barangKeluar.php';</script>";
}

// if(isset($_POST['hapus'])){
//     $id = $_POST['id'];
//     $idx = $_POST['idx'];

//     $lihatstock = mysqli_query($conn,"select * from sstock_brg where idx='$idx'"); //lihat stock barang itu saat ini
//     $stocknya = mysqli_fetch_array($lihatstock); //ambil datanya
//     $stockskrg = $stocknya['stock'];//jumlah stocknya skrg

//     $lihatdataskrg = mysqli_query($conn,"select * from sbrg_keluar where id='$id'"); //lihat qty saat ini
//     $preqtyskrg = mysqli_fetch_array($lihatdataskrg); 
//     $qtyskrg = $preqtyskrg['jumlah'];//jumlah skrg

//     $adjuststock = $stockskrg+$qtyskrg;

//     $queryx = mysqli_query($conn,"update sstock_brg set stock='$adjuststock' where idx='$idx'");
//     $del = mysqli_query($conn,"delete from sbrg_keluar where id='$id'");

    
//     //cek apakah berhasil
//     if ($queryx && $del){

//         echo " <div class='alert alert-success'>
//             <strong>Success!</strong> Redirecting you back in 1 seconds.
//           </div>
//         <meta http-equiv='refresh' content='1; url= keluar.php'/>  ";
//         } else { echo "<div class='alert alert-warning'>
//             <strong>Failed!</strong> Redirecting you back in 1 seconds.
//           </div>
//          <meta http-equiv='refresh' content='1; url= keluar.php'/> ";
//         }
// };
