<?php 

// if ($_SERVER['REQUEST_METHOD'] == 'POST' ) {
//     require 'config.php';

//     $id_user = $_POST['id_user'];
//     $alamat = $_POST['alamat'];
//     $no_telp = $_POST['no_telp'];
//     $id_ongkir = $_POST['id_ongkir'];
//     // $total_belanja = $_POST['total_belanja'];
    
//     $ambilKeranjang = mysqli_query($conn, "SELECT * FROM keranjang WHERE id_user = $id_user");
//     // $getKeranjang = mysqli_fetch_assoc($ambilKeranjang);
//     $getKeranjang = $ambilKeranjang->fetch_assoc();
    
//     $totalBelanja =0;
//     while ($getKeranjang = mysqli_fetch_assoc($ambilKeranjang)) {
//         $subtotal = $getKeranjang["subtotal"];
//         $totalBelanja += $subtotal;
//     } 

//     if ($id_ongkir == 1) {
//          $ongkir = 30000;
//     } elseif ($id_ongkir == 2) {
//         $ongkir = 48000;
//     }

//     $total_bayar = $totalBelanja + $ongkir;

//     // $queryTransaksi = mysqli_query($conn, "INSERT INTO transaksi VALUES ('', '$id_user', '$alamat', '$no_telp', '$id_ongkir', '$total_bayar', 'MENUNGGU PEMBAYARAN')");
    
//     $queryTransaksi = $conn->query("INSERT INTO transaksi VALUES ('', '$id_user', '$alamat', '$no_telp', '$id_ongkir', '$total_bayar', 'MENUNGGU PEMBAYARAN')");
    
//     $cek = mysqli_affected_rows($conn);
//     // $current_id = $conn->insert_id;
//     $getX = mysqli_fetch_assoc(mysqli_query($conn, "SELECT * FROM transaksi WHERE id_user = '$id_user' ORDER BY id_transaksi DESC LIMIT 1"));
//     $transaksi_terakhir = $getX['id_transaksi'];

//     if ($queryTransaksi) {
//         $cariKeranjang = mysqli_query($conn, "SELECT * FROM keranjang WHERE id_user = '$id_user'");
//         while ($item = mysqli_fetch_assoc($cariKeranjang)) {
//             $id_barang = $item['id_barang'];
//             $qty = $item['qty'];
//             $subtotal = $item['subtotal'];

//             $insert = mysqli_query($conn, "INSERT INTO transaksi_produk (id_transaksi, id_brg, qty, subtotal) VALUES ('$transaksi_terakhir', '$id_barang', '$qty', '$subtotal' )");
//         }

//         $response = array('pesan'=>'BERHASIL', 'total'=>$total_bayar);
        
//     } elseif (!$queryTransaksi) {
//         $response = array('pesan'=>'GAGAL', 'total'=>$total_bayar);
//     }
// }

// echo json_encode($response);
// mysqli_close($conn);


//dari vscode offline
// date_default_timezone_set('Asia/Jakarta');

if ($_SERVER['REQUEST_METHOD'] == 'POST' ) {
    require 'config.php';

    $id_user = $_POST['id_user'];
    $alamat = $_POST['alamat'];
    $penerima = $_POST['penerima'];
    $no_telp = $_POST['no_telp'];
    $id_ongkir = $_POST['id_ongkir'];
    // $tgl = date("Y-m-d H:i:s");
    $timezone = time() + (60 * 60 * 7);
    $date = gmdate('Y-m-d H:i:s', $timezone);
    // $date = date("Y-m-d H:i:s");
    // $total_belanja = $_POST['total_belanja'];
    
    $ambilKeranjang = mysqli_query($conn, "SELECT * FROM keranjang WHERE id_user = '$id_user'");
    // $getKeranjang = mysqli_fecth_assoc($ambilKeranjang);
    
    
    $totalBelanja =0;
    while ($getKeranjang = mysqli_fetch_assoc($ambilKeranjang)) {
        $subtotal = $getKeranjang["subtotal"];
        $totalBelanja += $subtotal;
    } 

    if ($id_ongkir == 1) {
         $ongkir = 30000;
    } elseif ($id_ongkir == 2) {
        $ongkir = 48000;
    }

    $total_bayar = $totalBelanja + $ongkir;

    $queryTransaksi = mysqli_query($conn, "INSERT INTO transaksi (tgl_transaksi, id_user, nama_penerima, alamat, no_telp, id_ongkir, total_transaksi, status) VALUES ('$date', '$id_user', '$penerima', '$alamat', '$no_telp', '$id_ongkir', '$total_bayar', 'MENUNGGU PEMBAYARAN')");
    $cek = mysqli_affected_rows($conn);
    // $current_id = $conn->insert_id;
    $getX = mysqli_fetch_assoc(mysqli_query($conn, "SELECT * FROM transaksi WHERE id_user = '$id_user' ORDER BY id_transaksi DESC LIMIT 1"));
    $transaksi_terakhir = $getX['id_transaksi'];

    if ($queryTransaksi) {
        $cariKeranjang = mysqli_query($conn, "SELECT * FROM keranjang WHERE id_user = '$id_user'");
        while ($item = mysqli_fetch_assoc($cariKeranjang)) {
            $id_barang = $item['id_barang'];
            $qty = $item['qty'];
            $subtotal = $item['subtotal'];

            $insert = mysqli_query($conn, "INSERT INTO transaksi_produk (id_transaksi, id_brg, qty, subtotal) VALUES ('$transaksi_terakhir', '$id_barang', '$qty', '$subtotal' )");
            
             $update = mysqli_query($conn, "UPDATE data_brg SET jml_stok = jml_stok - '$qty' WHERE id_brg = '$id_barang'");
        }

        $delete = mysqli_query($conn, "DELETE FROM keranjang WHERE id_user = '$id_user'");
        $response = array('pesan'=>'BERHASIL', 'total'=>$total_bayar);
        
    } elseif (!$queryTransaksi) {
        $response = array('pesan'=>'GAGAL', 'total'=>$total_bayar);
    }

echo json_encode($response);
mysqli_close($conn);
}

?>