<?php

require('koneksi.php');



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
    <link rel="stylesheet" type="text/css" href="assets/dataTable/datatables.min.css">
    <script type="text/javascript" src="assets/dataTable/datatables.min.js"></script>
</head>

<body>
    <div class="sidebar">
        <div class="logo-details">
            <i class='bx bxl-c-plus-plus'></i>
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
                <a href="#">
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
            <div class="search-box">
                <input type="text" placeholder="Search...">
                <i class='bx bx-search'></i>
            </div>
            <div class="profile-details">
                <img src="images/profile.jpg" alt="">
                <span class="admin_name">Prem Shahi</span>
                <i class='bx bx-chevron-down'></i>
            </div>
        </nav>

        <div class="home-content">
            <div class="sales-boxes">
                <div class="recent-sales1 box">
                    <div class="card-header">
                        <h3>Recent Mitra</h3>

                        <button>
                            <a href="mitra/newMitra.php" style="text-decoration: none;">New Mitra</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table width="100%">
                                <thead>
                                    <tr>
                                        <th>No</th>
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
                                            <td><?php echo $row['nama_mitra']; ?></td>
                                            <td><?php echo $row['alamat']; ?></td>
                                            <td><?php echo $row['no_tlp']; ?></td>
                                            <td><?php echo $row['tgl_gabung']; ?></td>
                                            <td><?php echo $row['barang']; ?></td>
                                            <td>
                                                <a href="mitra/editMitra.php?id=<?php echo $row['id']; ?>">Edit</a> |
                                                <a href="mitra/proses_hapus.php?id=<?php echo $row['id']; ?>" onclick="return confirm('Anda yakin akan menghapus data ini?')">Hapus</a>
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

    <script type="text/javascript">
        $(document).ready(function() {
            $('table').DataTable();
        } );
    </script>

</body>

</html>