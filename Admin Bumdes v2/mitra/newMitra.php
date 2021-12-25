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

$no = mysqli_query($koneksi, "select kd_mitra from data_mitra order by kd_mitra desc");
$idtran = mysqli_fetch_array($no);
$kode = isset($idtran['kd_mitra']) ? $idtran['kd_mitra'] : '';
// $kode = $idtran['id_transaksi'];


$urut = substr($kode, 8, 3);
$tambah = rand(1, 999);
$bulan = date("m");
$tahun = date("y");

if (strlen($tambah) == 1) {
    $format = "MTR-" . $bulan . $tahun . "00" . $tambah;
} else if (strlen($tambah) == 2) {
    $format = "MTR-" . $bulan . $tahun . "0" . $tambah;
} else {
    $format = "MTR-" . $bulan . $tahun . $tambah;
}
$tanggal_masuk = date("Y-m-d");

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="UTF-8">
    <title> Mitra </title>
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
                <a href="../barang.php" class="">
                    <i class='bx bx-box'></i>
                    <span class="links_name">Product</span>
                </a>
            </li>
            <li>
                <a href="../mitra.php" class="active">
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
                <a href="../mitra.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
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
                <span class="dashboard">Mitra</span>
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
                        <h3>Recent Mitra</h3>
                        <button>
                            <a href="../mitra.php" style="text-decoration: none;">Kembali</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="tambah">
                        <center>
                            <h1>Tambah Mitra</h1>
                        <center>
                        <form method="POST" action="proses_tambah.php" enctype="multipart/form-data">
                            <section class="base">
                                <div>
                                    <label for="kd_mitra">Kode Mitra</label>
                                    <input type="text" name="kd_mitra" id="kd_mitra" value="<?php echo $format; ?>" readonly />
                                </div>
                                <div>
                                    <label for="nama_mitra">Nama Mitra</label>
                                    <input type="text" name="nama_mitra" id="nama_mitra" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="alamat">Alamat</label>
                                    <input type="text" name="alamat" id="alamat" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="no_tlp">Nomer Telepon</label>
                                    <input type="number" name="no_tlp" id="no_tlp" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="tgl_gabung">Tanggal Gabung</label>
                                    <input type="date" name="tgl_gabung" id="tgl_gabung" value="<?php echo $tanggal_masuk; ?>" />
                                </div>
                                <div>
                                    <label for="barang">Barang</label>
                                    <input type="text" name="barang" id="barang" autofocus="" required />
                                </div>
                                <div>
                                    <button type="submit" name="bsimpan">Simpan Mitra</button>
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