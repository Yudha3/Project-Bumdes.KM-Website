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

$no = mysqli_query($koneksi, "select id_brg from data_brg order by id_brg desc");
$idtran = mysqli_fetch_array($no);
$kode = isset($idtran['id_brg']) ? $idtran['id_brg'] : '';
// $kode = $idtran['id_transaksi'];


$urut = substr($kode, 8, 3);
$tambah = rand(1, 999);
$bulan = date("m");
$tahun = date("y");

if (strlen($tambah) == 1) {
    $format = "BRG-" . $bulan . $tahun . "00" . $tambah;
} else if (strlen($tambah) == 2) {
    $format = "BRG-" . $bulan . $tahun . "0" . $tambah;
} else {
    $format = "BRG-" . $bulan . $tahun . $tambah;
}
$tanggal_masuk = date("Y-m-d");

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
                            <h1>Tambah Product</h1>
                        <center>
                        <form method="POST" action="proses_tambah.php" enctype="multipart/form-data">
                            <section class="base">
                                <div>
                                    <label for="id_brg">Kode Barang</label>
                                    <input type="text" name="id_brg" id="id_brg" value="<?php echo $format; ?>" readonly />
                                </div>
                                <div>
                                    <label class="control-label" for="barang">Nama Product</label>
                                    <input type="text" name="barang" class="form-control" id="barang" autofocus="" required="" />
                                </div>
                                <div>
                                    <label class="control-label" for="tgl_masuk">Tanggal Masuk Product</label>
                                    <input type="date" name="tgl_masuk" class="form-control" id="tgl_masuk" value="<?php echo $tanggal_masuk; ?>" />
                                </div>
                                <div>
                                    <label class="control-label" for="hg_beli">Harga Beli Product</label>
                                    <input type="number" name="hg_beli" class="form-control" id="hg_beli" autofocus="" required="" />
                                </div>
                                <div>
                                    <label class="control-label" for="hg_jual">Harga Jual Product</label>
                                    <input type="number" name="hg_jual" class="form-control" id="hg_jual" autofocus="" required="" />
                                </div>
                                <div>
                                    <label class="control-label" for="jml_stok">Stok Product</label>
                                    <input type="number" name="jml_stok" class="form-control" id="jml_stok" autofocus="" required="" />
                                </div>
                                <div>
                                    <label class="control-label" for="deskripsi">Deskripsi Product</label>
                                    <input type="text" name="deskripsi" class="form-control" id="deskripsi" autofocus="" required="" />
                                </div>
                                <div>
                                    <label class="control-label" for="gambar">Gambar Product</label>
                                    <input type="file" name="gambar" class="form-control" id="gambar" autofocus="" required="" />
                                </div>
                                <div>
                                    <button type="submit" name="bsimpan">Simpan Product</button>
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