<?php
// memanggil file koneksi.php untuk membuat koneksi
include '../koneksi.php';

// mengecek apakah di url ada nilai GET id
if (isset($_GET['id'])) {
    // ambil nilai id dari url dan disimpan dalam variabel $id
    $id = ($_GET["id"]);

    // menampilkan data dari database yang mempunyai id=$id
    $query = "SELECT * FROM data_brg WHERE id='$id'";
    $result = mysqli_query($koneksi, $query);
    // jika data gagal diambil maka akan tampil error berikut
    if (!$result) {
        die("Query Error: " . mysqli_errno($koneksi) .
            " - " . mysqli_error($koneksi));
    }
    // mengambil data dari database
    $data = mysqli_fetch_assoc($result);
    // apabila data tidak ada pada database maka akan dijalankan perintah ini
    if (!count($data)) {
        echo "<script>alert('Data tidak ditemukan pada database');window.location='barang.php';</script>";
    }
} else {
    // apabila tidak ada data GET id pada akan di redirect ke index.php
    echo "<script>alert('Masukkan data id.');window.location='barang.php';</script>";
}

?>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1">
    <title>Bumdes Admin</title>
    <link rel="stylesheet" href="https://maxst.icons8.com/vue-
    static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="../css/barang.css">
    <link rel="stylesheet" href="../1.3.0/css/line-awesome.min.css">

</head>

<body>

    <input type="checkbox" id="nav-toggle">
    <div class="sidebar">
        <div class="sidebar-brand">
            <h2><span class="lab la-amazon"></span> <span>Bumdes.KM</span></h2>
        </div>

        <div class="sidebar-menu">
            <ul>
                <li>
                    <a href="../dasboard.php" class=""><span class="las la-igloo"></span>
                        <span>Dasboard</span></a>
                </li>
                <li>
                    <a href="../mitra/Mitra.php" class=""><span class="las la-users"></span>
                        <span>Mitra</span></a>
                </li>
                <li>
                    <a href="../reseller/reseller.php" class=""><span class="las la-street-view"></span>
                        <span>Reseller</span></a>
                </li>
                <li>
                    <a href="barang.php" class="active"><span class="las la-box"></span>
                        <span>Barang</span></a>
                </li>
                <li>
                    <a href="" class=""><span class="las la-shopping-cart"></span>
                        <span>Transaksi</span></a>
                </li>
                <li>
                    <a href="" class=""><span class="las la-file-alt"></span>
                        <span>Laporan</span></a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main-content">
        <header>
            <h2>
                <label for="nav-toggle">
                    <span class="las la-bars"></span>
                </label>
                Barang
            </h2>

            <div class="search-wrapper">
                <span class="las la-search"></span>
                <input type="search" placeholder="Search here..." />
            </div>

            <div class="user-wrapper">
                <div>
                    <a href=""><span class="las la-bell"></span></a>
                </div>
                <div>
                    <a href=""><span class="las la-envelope"></span></a>
                </div>
                <img src="../img/avatar.svg" width="40px" height="40px" alt="">
                <div>
                    <h4>Boy Dymas Hidayat</h4>
                    <small>Admin</small>
                </div>
            </div>
        </header>

        <main>

            <div class="recent-grid">
                <div class="projects">
                    <div class="card">
                        <div class="card-header">
                            <h3>Recent Barang</h3>
                        </div>

                        <div class="tambah">

                            <center>
                                <h1>Edit Produk <?php echo $data['nama_brg']; ?></h1>
                            <center>
                            <form method="POST" action="proses_edit.php" enctype="multipart/form-data">
                                <section class="base">
                                    <!-- menampung nilai id produk yang akan di edit -->
                                    <input name="id" value="<?php echo $data['id']; ?>" hidden />
                                    <div>
                                        <label>Nama Barang</label>
                                        <input type="text" name="nama_brg" value="<?php echo $data['nama_brg']; ?>" autofocus="" required="" />
                                    </div>
                                    <div>
                                        <label>Jenis Barang</label>
                                        <input type="text" name="jenis_brg" value="<?php echo $data['jenis_brg']; ?>" />
                                    </div>
                                    <div>
                                        <label>Harga Barang</label>
                                        <input type="text" name="harga_brg" required="" value="<?php echo $data['harga_brg']; ?>" />
                                    </div>
                                    <div>
                                        <label>Stok Barang</label>
                                        <input type="text" name="stok_brg" required="" value="<?php echo $data['stok_brg']; ?>" />
                                    </div>
                                    <div>
                                        <label>Deskripsi</label>
                                        <input type="text" name="deskripsi_brg" required="" value="<?php echo $data['deskripsi_brg']; ?>" />
                                    </div>
                                    <div>
                                        <label>Gambar Produk</label>
                                        <img src="../img/gambar/<?php echo $data['gambar_brg']; ?>" style="width: 120px;float: left;margin-bottom: 5px;">
                                        <input type="file" name="gambar_brg" />
                                        <i style="float: left;font-size: 11px;color: red">Abaikan jika tidak merubah gambar produk</i>
                                    </div>
                                    <div>
                                        <button type="submit">Simpan Perubahan</button>
                                    </div>
                                </section>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

</body>

</html>