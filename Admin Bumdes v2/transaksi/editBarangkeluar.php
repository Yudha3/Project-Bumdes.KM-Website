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
                    <i class='bx bx-cart'></i>
                    <span class="links_name">Transaksi Masuk</span>
                </a>
            </li>
            <li>
                <a href="../barangKeluar.php" class="active">
                    <i class='bx bx-cart'></i>
                    <span class="links_name">Transaksi Keluar</span>
                </a>
            </li>
            <li>
                <a href="../report.php">
                    <i class='bx bx-book-alt'></i>
                    <span class="links_name">Laporan</span>
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

        <?php
                                    $brg = mysqli_query($koneksi, "SELECT * FROM data_klr sb, data_brg st where st.id_brg=sb.id_brg ORDER BY id DESC");
                                    $no = 1;
                                    while ($b = mysqli_fetch_array($brg)) {
                                        $idb = $b['id_brg'];
                                        $id = $b['id'];
                                    ?>
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
                            <h1>Edit Product <?php echo $b['barang'] ?></h1>
                        <center>
                        <form method="POST" action="proses_edit.php" enctype="multipart/form-data">
                            <section class="base">
                                <!-- menampung nilai id produk yang akan di edit -->
                                <input name="id" value="<?=$id; ?>" hidden />
                                <input type="hidden" name="id_brg" value="<?=$idb;?>">
                                <div>
                                    <label for="tgl_keluar">Tanggal keluar</label>
                                    <input type="date" name="tgl_keluar" id="tgl_keluar" value="<?php echo $b['tgl_keluar'] ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="barang">Nama Barang</label>
                                    <input type="text" name="barang" id="barang" value="<?php echo $b['barang'] ?>" autofocus="" required="" disabled />
                                </div>
                                <div>
                                    <label for="jml_keluar">Jumlah</label>
                                    <input type="number" name="jml_keluar" id="jml_keluar" value="<?php echo $b['jml_keluar'] ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="penerima">Penerima</label>
                                    <input type="text" name="penerima" id="penerima" value="<?php echo $b['penerima'] ?>" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="keterangan">Keterangan</label>
                                    <input type="text" name="keterangan" id="keterangan" value="<?php echo $b['keterangan'] ?>" autofocus="" required="" />
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
        <?php 
			}
		?>
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

