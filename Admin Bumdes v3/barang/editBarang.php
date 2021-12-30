<?php
session_start();
if (!isset($_SESSION['LOGIN'])) {
    header("location: ../login.php");
    exit();
}

if (isset($_GET['aksi'])) {
    $aksi = $_GET['aksi'];

    if ($aksi == "logout") {
        if (isset($_SESSION['LOGIN'])) {
            unset($_SESSION['LOGIN']);
            session_unset();
            session_destroy();
            $_SESSION = array();
        }
        header("location: ../login.php");
        exit();
    }
}
require('../koneksi.php');
$sesName = $_SESSION['name'];

// mengecek apakah di url ada nilai GET id
if (isset($_GET['id_brg'])) {
    // ambil nilai id dari url dan disimpan dalam variabel $id
    $id = ($_GET["id_brg"]);

    // menampilkan data dari database yang mempunyai id=$id
    $query = "SELECT * FROM data_brg WHERE id_brg = '$id'";
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
        echo "<script>alert('Data tidak ditemukan pada database');window.location='../barang.php';</script>";
    }
} else {
    // apabila tidak ada data GET id pada akan di redirect ke index.php
    echo "<script>alert('Masukkan data id.');window.location='../barang.php';</script>";
}

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="UTF-8">
    <title> Product </title>
    <link rel="stylesheet" href="../style/style.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
    <div class="sidebar">
        <div class="logo-details">
            <i class='bx bx-analyse'></i>
            <span class="logo_name">Bumdes.KM</span>
        </div>
        <ul class="nav-links">
            <li>
                <a href="../index.php" class="">
                    <i class='bx bx-grid-alt'></i>
                    <span class="links_name">Dashboard</span>
                </a>
            </li>
            <li>
                <a href="../barang.php" class="active">
                    <i class='bx bx-box'></i>
                    <span class="links_name">Product</span>
                </a>
            </li>
            <li>
                <a href="../mitra.php" class="">
                    <i class='bx bx-badge-check'></i>
                    <span class="links_name">Mitra</span>
                </a>
            </li>
            <li>
                <a href="../reseller.php" class="">
                    <i class='bx bxs-collection'></i>
                    <span class="links_name">Reseller</span>
                </a>
            </li>
            <li>
                <a href="../barangMasuk.php">
                    <i class='bx bxs-cart-add'></i>
                    <span class="links_name">Transaksi Masuk</span>
                </a>
            </li>
            <li>
                <a href="../barangKeluar.php" class="">
                    <i class='bx bxs-cart-download'></i>
                    <span class="links_name">Transaksi Keluar</span>
                </a>
            </li>
            <li>
                <a href="../preOrder.php" class="">
                    <i class='bx bxs-basket'></i>
                    <span class="links_name">Pre Order</span>
                </a>
            </li>
            <li>
                <a href="../reportMasuk.php" class="">
                    <i class='bx bxs-archive-in'></i>
                    <span class="links_name">Laporan Masuk</span>
                </a>
            </li>
            <li>
                <a href="../reportKeluar.php" class="">
                    <i class='bx bxs-archive-out'></i>
                    <span class="links_name">Laporan Keluar</span>
                </a>
            </li>
            <li class="log_out">
                <a href="../barang.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
                    <i class='bx bx-log-out'></i>
                    <span class="links_name">Log out</span>
                </a>
            </li>
        </ul>
    </div>
    <section class="home-section">
        <nav>
            <div class="sidebar-button">
                <i class='bx bx-menu sidebarBtn'></i>
                <span class="dashboard">Product</span>
            </div>
            <!-- <div class="search-box">
                <input type="text" placeholder="Search...">
                <i class='bx bx-search'></i>
            </div> -->
            <div class="profile-details">
                <!-- <img src="../images/profile.jpg" alt=""> -->
                <span class="admin_name"><?php echo $sesName; ?></span>
                <!-- <i class='bx bx-chevron-down'></i> -->
            </div>
        </nav>

        <div class="home-content">
            <div class="sales-boxes">
                <div class="recent-sales1 box">
                    <div class="card-header1">
                        <h3>Recent Product</h3>
                        <button>
                            <a href="../barang.php" style="text-decoration: none;">Kembali</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="tambah">
                        <center>
                            <h1>Edit Product <?php echo $data['barang']; ?></h1>
                        <center>
                        <form method="POST" action="proses_edit.php" enctype="multipart/form-data">
                            <section class="base">
                                <!-- menampung nilai id produk yang akan di edit -->
                                <input name="id" value="<?php echo $data['id_brg']; ?>" hidden />
                                <div>
                                    <label for="id_brg">Kode Barang</label>
                                    <input type="text" name="id_brg" id="id_brg" value="<?php echo $data['id_brg']; ?>" readonly />
                                </div>
                                <div>
                                    <label for="barang">Nama Product</label>
                                    <input type="text" name="barang" id="barang" value="<?php echo $data['barang']; ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="tgl_masuk">Tanggal Masuk Product</label>
                                    <input type="date" name="tgl_masuk" id="tgl_masuk" value="<?php echo $data['tgl_masuk']; ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="hg_beli">Harga Beli Product</label>
                                    <input type="number" name="hg_beli" id="hg_beli" value="<?php echo $data['hg_beli']; ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="hg_jual">Harga Jual Product</label>
                                    <input type="number" name="hg_jual" id="hg_jual" value="<?php echo $data['hg_jual']; ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="jml_stok">Stok Product</label>
                                    <input type="number" name="jml_stok" id="jml_stok" value="<?php echo $data['jml_stok']; ?>" autofocus="" required="" disabled />
                                </div>
                                <div>
                                    <label for="deskripsi">Deskripsi</label>
                                    <input type="text" name="deskripsi" id="deskripsi" value="<?php echo $data['deskripsi']; ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="gambar">Gambar Product</label>
                                    <img src="../images/barang/<?php echo $data['gambar']; ?>" style="width: 120px;float: left;margin-bottom: 5px;">
                                    <input type="file" name="gambar" />
                                    <i style="float: left;font-size: 11px;color: red">Abaikan jika tidak merubah gambar produk</i>
                                    <input type="hidden" name="fotolama" value="<?php echo $data['gambar']; ?>">
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
    </section>

    <script>
        let sidebar = document.querySelector(".sidebar");
        let sidebarBtn = document.querySelector(".sidebarBtn");
        sidebarBtn.onclick = function() {
            sidebar.classList.toggle("active");
            if (sidebar.classList.contains("active")) {
                sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
            } else
                sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
        }
    </script>

</body>

</html>