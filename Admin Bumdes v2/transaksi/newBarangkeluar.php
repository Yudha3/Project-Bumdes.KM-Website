<?php
session_start();
if (!isset($_SESSION['LOGIN'])) {
	header("location: login.php");
	exit();
}

if (isset($_GET['aksi'])) {
  $aksi = $_GET['aksi'];

  if($aksi == "logout") {
    if(isset($_SESSION['LOGIN'])) {
      unset ($_SESSION['LOGIN']);
      session_unset();
      session_destroy();
      $_SESSION = array();
    }
    header("location: login.php");
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
                <a href="../reseller.php" >
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

        <div class="home-content">
            <div class="sales-boxes">
                <div class="recent-sales1 box">
                    <div class="card-header1">
                        <h3>New Order List</h3>

                        <button>
                            <a href="../barangKeluar.php" style="text-decoration: none;">Kembali</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="tambah">
                        <center>
                            <h1>Order</h1>
                        <center>
                        <form method="POST" action="proses_tambah.php" enctype="multipart/form-data">
                            <section class="base">
                                <div>
                                    <label for="tgl_keluar">Tanggal Keluar</label>
                                    <input type="date" name="tgl_keluar" id="tgl_keluar" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="barang">Nama Barang</label>
                                    <select name="barang" class="custom-select form-control" id="barang" autofocus="" required="">
                                        <option selected>Pilih barang</option>
                                        <?php
                                        $det = mysqli_query($koneksi, "select * from data_brg order by barang ASC");
                                        while ($d = mysqli_fetch_array($det)) {
                                        ?>
                                            <option value="<?php echo $d['id_brg'] ?>"><?php echo $d['barang'] ?> </option>
                                        <?php
                                        }
                                        ?>
                                    </select>
                                </div>
                                <div>
                                    <label for="jml_keluar">Jumlah</label>
                                    <input type="number" min="1" name="jml_keluar" id="jml_keluar" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="penerima">Penerima</label>
                                    <input type="text" name="penerima" id="penerima" autofocus="" required="" />
                                </div>
                                <div>
                                    <label for="keterangan">Keterangan</label>
                                    <input type="text" name="keterangan" id="keterangan" autofocus="" required="" />
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