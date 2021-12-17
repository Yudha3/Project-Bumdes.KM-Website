<?php

require('koneksi.php');

// $username = $_GET['user_fullname'];
session_start();

$sesName = $_SESSION['name'];


$data_barang = mysqli_query($koneksi, "SELECT * FROM data_brg");
$data_mitra = mysqli_query($koneksi, "SELECT * FROM data_mitra");
$data_reseller = mysqli_query($koneksi, "SELECT * FROM data_reseller");

$jml_barang = mysqli_num_rows($data_barang);
$jml_mitra = mysqli_num_rows($data_mitra);
$jml_reseller = mysqli_num_rows($data_reseller);

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <meta charset="UTF-8">
  <title> Responsiive Admin Dashboard | CodingLab </title>
  <link rel="stylesheet" href="style.css">
  <!-- Boxicons CDN Link -->
  <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
  <div class="sidebar">
    <div class="logo-details">
      <i class='bx bxl-c-plus-plus'></i>
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
        <a href="#">
          <i class='bx bx-user'></i>
          <span class="links_name">Akun</span>
        </a>
      </li>
      <li>
        <a href="#">
          <i class='bx bx-cart'></i>
          <span class="links_name">Transaksi</span>
        </a>
      </li>
      <li>
        <a href="#">
          <i class='bx bx-book-alt'></i>
          <span class="links_name">Laporan</span>
        </a>
      </li>
      <li class="log_out">
        <a href="logout.php">
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
      <div class="search-box">
        <input type="text" placeholder="Search...">
        <i class='bx bx-search'></i>
      </div>
      <div class="profile-details">
        <img src="images/profile.jpg" alt="">
        <span class="admin_name"><?php echo $sesName; ?></span>
        <i class='bx bx-chevron-down'></i>
      </div>
    </nav>

    <div class="home-content">
      <div class="overview-boxes">
        <div class="box">
          <div class="right-side">
            <div class="box-topic">Total Order</div>
            <div class="number">40,876</div>
            <div class="indicator">
              <i class='bx bx-left-arrow-alt right'></i>
              <span class="text">Up from yesterday</span>
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
              <span class="text">Up from yesterday</span>
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
              <span class="text">Up from yesterday</span>
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
              <!-- <a href="reseller.php"><span class="text">See All</span></a> -->
              <span class="text">See All</span>
            </div>
          </div>
          <i class='bx bxs-collection cart four'></i>
        </div>
      </div>

      <div class="sales-boxes">
        <div class="recent-sales1 box">
          <div class="card-header1">
            <h3>Recent Barang</h3>

            <button>
              <a href="barang.php" style="text-decoration: none;">Detail</a>
              <span class="bx bx-right-arrow-alt"></span>
            </button>
          </div>
          <div class="card-body1">
            <div class="table-responsive">
              <table width="100%">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>Nama Barang</th>
                    <th>Harga Barang</th>
                    <th>Stok Barang</th>
                    <th>Gambar Barang</th>
                  </tr>
                </thead>
                <tbody>
                  <?php
                  // jalankan query untuk menampilkan semua data diurutkan berdasarkan nim
                  $query = "SELECT * FROM data_brg ORDER BY id ASC";
                  $result = mysqli_query($koneksi, $query);
                  //mengecek apakah ada error ketika menjalankan query
                  if (!$result) {
                    die("Query Error: " . mysqli_errno($koneksi) .
                      " - " . mysqli_error($koneksi));
                  }

                  //buat perulangan untuk element tabel dari data mahasiswa
                  $no = 1; //variabel untuk membuat nomor urut
                  // hasil query akan disimpan dalam variabel $data dalam bentuk array
                  // kemudian dicetak dengan perulangan while
                  while ($row = mysqli_fetch_assoc($result)) {
                  ?>
                    <tr>
                      <td align="center"><?php echo $no; ?></td>
                      <td><?php echo $row['nama_brg']; ?></td>
                      <td>Rp <?php echo $row['harga_brg']; ?></td>
                      <td><?php echo $row['stok_brg']; ?></td>
                      <td style="text-align: center;"><img src="images/gambar/<?php echo $row['gambar_brg']; ?>" style="width: 120px;"></td>
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
        <!-- <div class="top-sales box">
          <div class="title">Top Seling Product</div>
          <ul class="top-sales-details">
            <li>
                
            </li>
            <li>
              <a href="#">
                <img src="images/jeans.jpg" alt="">
                <span class="product">Hourglass Jeans </span>
              </a>
              <span class="price">$1567</span>
            </li>
            <li>
              <a href="#">
                <img src="images/nike.jpg" alt="">
                <span class="product">Nike Sport Shoe</span>
              </a>
              <span class="price">$1234</span>
            </li>
            <li>
              <a href="#">
                <img src="images/scarves.jpg" alt="">
                <span class="product">Hermes Silk Scarves.</span>
              </a>
              <span class="price">$2312</span>
            </li>
            <li>
              <a href="#">
                <img src="images/blueBag.jpg" alt="">
                <span class="product">Succi Ladies Bag</span>
              </a>
              <span class="price">$1456</span>
            </li>
            <li>
              <a href="#">
                <img src="images/bag.jpg" alt="">
                <span class="product">Gucci Womens's Bags</span>
              </a>
              <span class="price">$2345</span>
            <li>
              <a href="#">
                <img src="images/addidas.jpg" alt="">
                <span class="product">Addidas Running Shoe</span>
              </a>
              <span class="price">$2345</span>
            </li>
            <li>
              <a href="#">
                <img src="images/shirt.jpg" alt="">
                <span class="product">Bilack Wear's Shirt</span>
              </a>
              <span class="price">$1245</span>
            </li>
          </ul>
        </div> -->
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