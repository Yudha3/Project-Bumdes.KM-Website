<?php 
include '../koneksi.php';

$id = $_GET["id_preorder"];
$findPreorder = mysqli_query($koneksi, "SELECT * FROM preorder WHERE id_preorder = '$id'");
while ($getPreorder = mysqli_fetch_assoc($findPreorder)) {
    $tgl = $getPreorder['tgl_preorder'];
    $id_user = $getPreorder['id_user'];
    $penerima = $getPreorder['penerima'];
    $alamat = $getPreorder['alamat'];
    $no_telp = $getPreorder['no_telp'];
    $id_ongkir = $getPreorder['id_ongkir'];
    $total = $getPreorder['total_preorder'];
}

$simpan = mysqli_query($koneksi, "INSERT INTO transaksi (tgl_transaksi, id_user, penerima, alamat, no_telp, id_ongkir, total_transaksi, status)
                        VALUES ('$tgl', '$id_user', '$penerima', '$alamat', '$no_telp', '$id_ongkir', '$total', 'Menunggu Pembayaran')");
if (mysqli_affected_rows($koneksi) > 0) {
$getID = mysqli_fetch_assoc(mysqli_query($koneksi, "SELECT * FROM transaksi WHERE id_user = '$id_user' ORDER BY id_transaksi DESC LIMIT 1 "));
$id_trx = $getID['id_transaksi'];
}

$getItemPrerder = mysqli_query($koneksi, "SELECT * FROM item_preorder WHERE id_preorder = '$id' ");
while ($itemPreorder = mysqli_fetch_assoc($getItemPrerder)) {
    $id_brg = $itemPreorder['id_brg'];
    $qty = $itemPreorder['qty'];
    $subtotal = $itemPreorder['subtotal'];

    $insertItem = mysqli_query($koneksi, "INSERT INTO transaksi_produk (id_transaksi, id_brg, qty, subtotal) VALUES ('$id_trx', '$id_brg', '$qty', '$subtotal')");

    // $updateStok = mysqli_query($koneksi, "UPDATE data_brg SET jml_stok = jml_stok - '$qty' WHERE id_brg = '$id_brg'");

} 
$delete =  mysqli_query($koneksi, "DELETE FROM preorder WHERE id_preorder = '$id'");
$delete =  mysqli_query($koneksi, "DELETE FROM item_preorder WHERE id_preorder = '$id'");

if (!$delete) {
    die("Gagal menghapus data: " . mysqli_errno($koneksi) .
        " - " . mysqli_error($koneksi));
} else {
    echo "<script>alert('Berhasil membuat transaksi');window.location='../preOrder.php';</script>";
}
?>