<?php
session_start();
if (!isset($_SESSION['LOGIN'])) {
  header("location: login.php");
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
    header("location: login.php");
    exit();
  }
}
require('koneksi.php');
$sesName = $_SESSION['name'];

$data_barang = mysqli_query($koneksi, "SELECT * FROM data_brg");
$data_mitra = mysqli_query($koneksi, "SELECT * FROM data_mitra");
$data_reseller = mysqli_query($koneksi, "SELECT * FROM data_reseller");
$transaksi = mysqli_query($koneksi, "SELECT * FROM transaksi");

$jml_barang = mysqli_num_rows($data_barang);
$jml_mitra = mysqli_num_rows($data_mitra);
$jml_reseller = mysqli_num_rows($data_reseller);
$jml_transaksi = mysqli_num_rows($transaksi);

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <meta charset="UTF-8">
  <title> Dashboard Bumdes </title>
  <link rel="stylesheet" href="style/style.css">
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
        <a href="index.php" class="active">
          <i class='bx bx-grid-alt'></i>
          <span class="links_name">Dashboard</span>
        </a>
      </li>
      <li>
        <a href="barang.php" class="">
          <i class='bx bx-box'></i>
          <span class="links_name">Product</span>
        </a>
      </li>
      <li>
        <a href="mitra.php" class="">
          <i class='bx bx-badge-check'></i>
          <span class="links_name">Mitra</span>
        </a>
      </li>
      <li>
        <a href="reseller.php" class="">
          <i class='bx bxs-collection'></i>
          <span class="links_name">Reseller</span>
        </a>
      </li>
      <li>
        <a href="barangMasuk.php">
          <i class='bx bxs-cart-add'></i>
          <span class="links_name">Transaksi Masuk</span>
        </a>
      </li>
      <li>
        <a href="barangKeluar.php" class="">
          <i class='bx bxs-cart-download'></i>
          <span class="links_name">Transaksi Keluar</span>
        </a>
      </li>
      <li>
        <a href="preOrder.php" class="">
          <i class='bx bxs-basket'></i>
          <span class="links_name">Pre Order</span>
        </a>
      </li>
      <li>
        <a href="reportMasuk.php" class="">
          <i class='bx bxs-archive-in'></i>
          <span class="links_name">Laporan Masuk</span>
        </a>
      </li>
      <li>
        <a href="reportKeluar.php" class="">
          <i class='bx bxs-archive-out'></i>
          <span class="links_name">Laporan Keluar</span>
        </a>
      </li>
      <li class="log_out">
        <a href="index.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
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
        <span class="dashboard">Dashboard</span>
      </div>
      <div class="profile-details">
        <span class="admin_name"><?php echo $sesName; ?></span>
      </div>
    </nav>

    <div class="home-content">
      <div class="overview-boxes">
        <div class="box">
          <div class="right-side">
            <div class="box-topic">Total Order</div>
            <div class="number"><?php echo $jml_transaksi; ?></div>
            <div class="indicator">
              <i class='bx bx-left-arrow-alt right'></i>
              <a href="barangKeluar.php" class="text">
                See All
              </a>
            </div>
          </div>
          <i class='bx bx-cart-alt cart'></i>
        </div>
        <div class="box">
          <div class="right-side">
            <div class="box-topic">Total Produk</div>
            <div class="number"><?php echo $jml_barang; ?></div>
            <div class="indicator">
              <i class='bx bx-left-arrow-alt left'></i>
              <a href="barang.php" class="text">
                See All
              </a>
            </div>
          </div>
          <i class='bx bx-box cart two'></i>
        </div>
        <div class="box">
          <div class="right-side">
            <div class="box-topic">Total Mitra</div>
            <div class="number"><?php echo $jml_mitra; ?></div>
            <div class="indicator">
              <i class='bx bx-left-arrow-alt up'></i>
              <a href="mitra.php" class="text">
                See All
              </a>
            </div>
          </div>
          <i class='bx bx-badge-check cart three'></i>
        </div>
        <div class="box">
          <div class="right-side">
            <div class="box-topic">Total Reseller</div>
            <div class="number"><?php echo $jml_reseller; ?></div>
            <div class="indicator">
              <i class='bx bx-left-arrow-alt down'></i>
              <a href="reseller.php" class="text">
                See All
              </a>
            </div>
          </div>
          <i class='bx bxs-collection cart four'></i>
        </div>
      </div>

      <div class="sales-boxes">
        <div class="recent-sales1 box">
          <div class="card-header1">
            <h3>Recent Transaksi</h3>

            <button>
              <a href="barangKeluar.php" style="text-decoration: none;">Detail</a>
              <span class="bx bx-right-arrow-alt"></span>
            </button>
          </div>
          <div class="card-body1">
            <div class="table-responsive">
              <table width="100%">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>ID Transaksi</th>
                    <th>Tanggal</th>
                    <th>Penerima</th>
                    <th>Alamat</th>
                    <th>Total Harga</th>
                    <th>Status</th>
                  </tr>
                </thead>
                <tbody>
                  <?php
                  $brg = mysqli_query($koneksi, "SELECT * FROM transaksi ORDER BY id_transaksi DESC");
                  $no = 1;
                  while ($b = mysqli_fetch_array($brg)) {
                    $idb = $b['id_transaksi'];
                  ?>
                    <tr>
                      <td align="center"><?php echo $no; ?></td>
                      <td><?php echo $b['id_transaksi'] ?></td>
                      <td><?php $tanggals = $b['tgl_transaksi'];
                          echo date("d-M-Y", strtotime($tanggals)) ?></td>
                      <td><?php echo $b['penerima'] ?></td>
                      <td><?php echo $b['alamat'] ?></td>
                      <td>Rp <?php echo number_format($b['total_transaksi'],0,',','.') ?></td>
                      <td><?php echo $b['status'] ?></td>
                    </tr>
                  <?php
                    $no++; //untuk nomor urut terus bertambah 1
                  }
                  ?>
                </tbody>
              </table>
            </div>
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