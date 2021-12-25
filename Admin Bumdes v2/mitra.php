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
require('koneksi.php');
$sesName = $_SESSION['name'];

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="UTF-8">
    <title> Mitra </title>
    <link rel="stylesheet" href="style/style.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css"/>
</head>

<body>
    <div class="sidebar">
        <div class="logo-details">
            <i class='bx bx-analyse'></i>
            <span class="logo_name">Bumdes.KM</span>
        </div>
        <ul class="nav-links">
            <li>
                <a href="index.php" class="">
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
                <a href="mitra.php" class="active">
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
                <a href="mitra.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
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
                <!-- <img src="images/profile.jpg" alt=""> -->
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
                            <a href="mitra/newMitra.php" style="text-decoration: none;">Tambah Mitra</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="card-body1">
                        <div class="table-responsive">
                            <table width="100%">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Kode Mitra</th>
                                        <th>Nama Mitra</th>
                                        <th>Alamat</th>
                                        <th>Nomer Telepon</th>
                                        <th>Tanggal Gabung</th>
                                        <th>Barang</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    // jalankan query untuk menampilkan semua data diurutkan berdasarkan nim
                                    $query = "SELECT * FROM data_mitra ORDER BY id ASC";
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
                                            <td><?php echo $row['kd_mitra']; ?></td>
                                            <td><?php echo $row['nama_mitra']; ?></td>
                                            <td><?php echo $row['alamat']; ?></td>
                                            <td><?php echo $row['no_tlp']; ?></td>
                                            <td><?php echo $row['tgl_gabung']; ?></td>
                                            <td><?php echo $row['barang']; ?></td>
                                            <td>
                                                <a href="mitra/editMitra.php?id=<?php echo $row['id']; ?>" style="text-decoration: none;">Edit</a> |
                                                <a href="mitra/proses_hapus.php?id=<?php echo $row['id']; ?>" onclick="return confirm('Anda yakin akan menghapus data ini?')" style="text-decoration: none;">Hapus</a>
                                            </td>
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

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('table').DataTable({
                // "paging":   false,
                "ordering": false,
                "info":     false,
                "pagingType": "full_numbers",
                "lengthMenu": [
                    [10, 25, 50, -1],
                    [10, 25, 50, "All"]
                ],
                responsive : true,
                language: {
                    search: "_INPUT_",
                    searchPlaceholder: "Search Your Data",
                }
            });
        } );
    </script>

</body>

</html>