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
require '../koneksi.php';
$sesName = $_SESSION['name'];

$no = mysqli_query($koneksi, "select id_transaksi from data_msk order by id_transaksi desc");
$idtran = mysqli_fetch_array($no);
$kode = isset($idtran['id_transaksi']) ? $idtran['id_transaksi'] : '';
// $kode = $idtran['id_transaksi'];


$urut = substr($kode, 8, 3);
$tambah = (int) $urut + 1;
$bulan = date("m");
$tahun = date("y");
$day = date("d");

if (strlen($tambah) == 1) {
    $format = "TRM-" . $bulan . $tahun . "00" . $tambah;
} else if (strlen($tambah) == 2) {
    $format = "TRM-" . $bulan . $tahun . "0" . $tambah;
} else {
    $format = "TRM-" . $bulan . $tahun . $tambah;
}
$tanggal_masuk = date("Y-m-d");

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="UTF-8">
    <title> Transaksi Barang Masuk </title>
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
                <a href="../barangMasuk.php" class="active">
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
                <a href="../preOrder.php" class="">
                    <i class='bx bxs-basket'></i>
                    <span class="links_name">Pre Order</span>
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
                <a  href="../barangMasuk.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
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
                <span class="dashboard">Barang Masuk</span>
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
                        <h3>Recent Barang Masuk</h3>
                        <button>
                            <a href="../barangMasuk.php" style="text-decoration: none;">Kembali</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="tambah">
                        <center>
                            <h1>Tambah Barang Masuk</h1>
                            <center>
                                <form method="POST" action="proses_tambah.php" enctype="multipart/form-data">
                                    <section class="base">
                                        <div>
                                            <label for="id_transaksi">Id Transaksi</label>
                                            <input type="text" name="id_transaksi" id="id_transaksi" value="<?php echo $format; ?>" readonly />
                                        </div>
                                        <div>
                                            <label for="tgl_msk">Tanggal Masuk</label>
                                            <input type="date" name="tgl_msk" id="tgl_msk" value="<?php echo $tanggal_masuk; ?>" />
                                        </div>
                                        <div>
                                            <label for="barang">Barang</label>
                                            <select name="barang" class="custom-select form-control" id="cmb_barang" autofocus="" required="">
                                                <option selected>-- Pilih barang --</option>
                                                <?php
                                                $det = mysqli_query($koneksi, "select * from data_brg order by id_brg ASC");
                                                while ($d = mysqli_fetch_array($det)) {

                                                    // echo "<option value='$d[id_brg].$d[barang]'>$d[id_brg] | $d[barang]</option>";
                                                    echo "<option value='$d[id_brg].$d[barang]'>$d[id_brg] | $d[barang]</option>";
                                                }
                                                ?>
                                            </select>
                                        </div>
                                        <div>
                                            <label for="hg_beli">Harga Beli</label>
                                            <input type="number" name="hg_beli" id="hg_beli" readonly maxlength="11"/>
                                        </div>
                                        <div>
                                            <label for="jml_masuk">Jumlah</label>
                                            <input type="number" onkeyup="sum()" name="jml_masuk" id="jml_masuk" autofocus="" required="" maxlength="4" />
                                        </div>

                                        <div>
                                            <label for="total_hrg">Total Harga</label>
                                            <input type="number" name="total_hrg" id="total_hrg" readonly/>
                                        </div>

                                        <div>
                                            <label for="pengirim">Mitra</label>
                                            <select name="pengirim" class="custom-select form-control" id="pengirim" autofocus="" required="">
                                                <option selected>-- Pilih Mitra --</option>
                                                <?php
                                                $det = mysqli_query($koneksi, "select * from data_mitra order by nama_mitra ASC");
                                                while ($d = mysqli_fetch_array($det)) {


                                                    echo "<option value='$d[nama_mitra]'>$d[nama_mitra]</option>";
                                                }
                                                ?>
                                            </select>
                                        </div>
                                        <div>
                                            <button type="submit" name="simpan">Simpan Product</button>
                                        </div>
                                    </section>
                                </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="../assets/js/jquery-1.10.2.js"></script>
    <script>
        function sum() {
	        var stok = document.getElementById('hg_beli').value;
	        var jumlahmasuk = document.getElementById('jml_masuk').value;
	        var result = parseInt(stok) * parseInt(jumlahmasuk);
	        if (!isNaN(result)) {
		        document.getElementById('total_hrg').value = result;
	        }
        }

        $('#cmb_barang').change(function(){
            var selected_id = $(this).val();
            var data = {id: selected_id};
            $.post('get_data_brg.php', data, function(data){
                $('#hg_beli').val(data);
            })
        });
    </script>

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