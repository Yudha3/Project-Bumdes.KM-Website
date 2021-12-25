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

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="UTF-8">
    <title> Laporan Barang Keluar </title>
    <link rel="stylesheet" href="style/style.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css" />
    <!-- <link rel="stylesheet" href="assets/plugin/jquery-ui/jquery-ui.min.css" /> Load file css jquery-ui -->
    <!-- <script src="assets/js/jquery-1.10.2.js"></script>
    <script src="assets/datepicker/js/bootstrap-datepicker.js"></script> -->
    <link rel="stylesheet" href="assets/datepicker/css/datepicker.css">
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
                <a href="barangMasuk.php" class="">
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
                <a href="reportKeluar.php" class="active">
                    <i class='bx bxs-archive-out'></i>
                    <span class="links_name">Laporan Keluar</span>
                </a>
            </li>
            <li class="log_out">
                <a href="reportKeluar.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
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
                <span class="dashboard">Laporan Barang Keluar</span>
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
                        <h3>Data Laporan</h3>
                    </div>
                    <!-- <form method="GET">
                        <label>Pilih Tanggal</label>
                        <input type="date" name="tanggal">
                        <input type="submit" value="FILTER">
                        <a href="./">Back</a>
                    </form> -->
                    <form action="reportKeluar.php" method="get" style="margin-bottom: 1rem;">
                        <div class="row g-3 align-items-center">
                            <div class="col-auto">
                                <label class="col-form-label">Periode</label>
                            </div>
                            <div class="col-auto">
                                <input type="date" class="form-control" name="dari" required>
                            </div>
                            <div class="col-auto">
                                -
                            </div>
                            <div class="col-auto">
                                <input type="date" class="form-control" name="ke" required>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-primary" type="submit" name="filter" value="true">Cari</button>
                                <button class="btn btn-danger">
                                    <a href="reportKeluar.php" style="text-decoration: none; color:white;">Back</a>
                                </button>
                            </div>
                        </div>
                    </form>

                    <div class="card-body1">
                        <div class="table-responsive">
                            <table width="100%">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>ID Transaksi</th>
                                        <th>Tanggal Transaksi</th>
                                        <th>Barang</th>
                                        <th>Jumlah</th>
                                        <th>Total Harga</th>
                                        <th>penerima</th>
                                        <th>Keterangan</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    //jika tanggal dari dan tanggal ke ada maka
                                    $tgl_awal = @$_GET['dari']; // Ambil data tgl_awal sesuai input (kalau tidak ada set kosong)        
                                    $tgl_akhir = @$_GET['ke']; // Ambil data tgl_awal sesuai input (kalau tidak ada set kosong)
                                    if (empty($tgl_awal) or empty($tgl_akhir)) {
                                        //jika tidak ada tanggal dari dan tanggal ke maka tampilkan seluruh data
                                        $data = mysqli_query($koneksi, "select * from report_klr");
                                        $url_cetak = "print2.php";
                                    } else {
                                        // tampilkan data yang sesuai dengan range tanggal yang dicari 
                                        $data = mysqli_query($koneksi, "SELECT * FROM report_klr WHERE tgl_keluar BETWEEN '" . $tgl_awal . "' and '" . $tgl_akhir . "'");
                                        $url_cetak = "print2.php?dari=".$tgl_awal."&ke=".$tgl_akhir."&filter=true";
                                    }
                                    // $no digunakan sebagai penomoran 
                                    $no = 1;
                                    // while digunakan sebagai perulangan data 
                                    while ($d = mysqli_fetch_array($data)) {
                                    ?>
                                        <tr>
                                            <td><?php echo $no++; ?></td>
                                            <td><?php echo $d["id_transaksi"]; ?></td>
                                            <td><?php $tanggals = $d['tgl_keluar'];
                                                echo date("d-M-Y", strtotime($tanggals)) ?></td>
                                            <td><?php echo $d["barang"];   ?></td>
                                            <td><?php echo $d["jml_keluar"];   ?></td>
                                            <td><?php echo $d["total_hrg"];   ?></td>
                                            <td><?php echo $d["penerima"];   ?></td>
                                            <td><?php echo $d["keterangan"];   ?></td>
                                        </tr>
                                    <?php
                                    }
                                    ?>
                                </tbody>
                            </table>
                            <button class="btn btn-primary">
                                <a href="<?php echo $url_cetak ?>" style="text-decoration: none; color:white;">Cetak PDF</a>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/jquery-1.10.2.js"></script>
    <script src="assets/datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript">
        $(function() {
            $(".datepicker").datepicker({
                format: 'dd-mm-yyyy',
                autoclose: true,
                todayHighlight: false,
            });
            $("#tgl_mulai").on('changeDate', function(selected) {
                var startDate = new Date(selected.date.valueOf());
                $("#tgl_akhir").datepicker('setStartDate', startDate);
                if ($("#tgl_mulai").val() > $("#tgl_akhir").val()) {
                    $("#tgl_akhir").val($("#tgl_mulai").val());
                }
            });
        });
    </script> -->
    <!-- <script src="assets/plugin/jquery-ui/jquery-ui.min.js"></script> Load file plugin js jquery-ui -->

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
                "info": false,
                "pagingType": "full_numbers",
                "lengthMenu": [
                    [10, 25, 50, -1],
                    [10, 25, 50, "All"]
                ],
                responsive: true,
                language: {
                    search: "_INPUT_",
                    searchPlaceholder: "Search Your Data",
                }
            });
        });
    </script>

</body>

</html>