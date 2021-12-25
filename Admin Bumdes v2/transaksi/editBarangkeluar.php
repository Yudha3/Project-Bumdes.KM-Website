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
                        
$id = $_GET['id'];
// $query = "SELECT * from data_brg INNER JOIN data_klr ON data_brg.id_brg=data_klr.id_brg WHERE data_brg.id_brg='$id'";
$query = "SELECT * from data_brg INNER JOIN data_klr ON data_brg.id_brg=data_klr.id_brg WHERE data_klr.id='$id'";
// $query = "SELECT * from data_msk INNER JOIN data_brg ON data_brg.id_brg=data_msk.id_brg WHERE data_msk.id='$id'";
// $query = "SELECT * from data_msk sb, data_brg st where st.id_brg=sb.id_brg order by sb.id='$id'";
$result = mysqli_query($koneksi, $query);
$data = mysqli_fetch_assoc($result);

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="UTF-8">
    <title> Transaksi Barang Keluar </title>
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
                <a href="../barangKeluar.php" class="active">
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
                <a href="../barangKeluar.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
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
                <span class="dashboard">Barang Keluar</span>
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
                        <h3>Edit Order List</h3>
                        <button>
                            <a href="../barangKeluar.php" style="text-decoration: none;">Kembali</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="tambah">
                        <center>
                            <h1>Edit Product <?php echo $data['barang'] ?></h1>
                        <center>
                        <form method="POST" action="proses_edit.php" enctype="multipart/form-data">
                            <section class="base">
                                <!-- menampung nilai id produk yang akan di edit -->
                                <input type="hidden" name="id" value="<?php echo $data['id'] ?>">
                                <input type="hidden" name="id_brg" value="<?php echo $data['id_brg'] ?>">
                                <div>
                                    <label for="id_transaksi">ID Transaksi</label>
                                    <input type="text" name="id_transaksi" id="id_transaksi" value="<?php echo $data['id_transaksi'] ?>" disabled />
                                </div>
                                <div>
                                    <label for="tgl_keluar">Tanggal keluar</label>
                                    <input type="date" name="tgl_keluar" id="tgl_keluar" value="<?php echo $data['tgl_keluar'] ?>" disabled />
                                </div>
                                <div>
                                    <label for="barang">Nama Barang</label>
                                    <input type="text" name="barang" id="barang" value="<?php echo $data['barang'] ?>" disabled />
                                </div>
                                <div>
                                    <label for="jml_keluar">Jumlah</label>
                                    <input type="number" name="jml_keluar" id="jml_keluar" value="<?php echo $data['jml_keluar'] ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="penerima">Penerima</label>
                                    <input type="text" name="penerima" id="penerima" value="<?php echo $data['penerima'] ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="keterangan">Keterangan</label>
                                    <input type="text" name="keterangan" id="keterangan" value="<?php echo $data['keterangan'] ?>" />
                                </div>
                                <div>
                                    <button type="submit" name="update">Simpan Perubahan</button>
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

