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
                        
$id = $_GET['id_preorder'];
$queryTransaksi = mysqli_query($koneksi, "SELECT * FROM preorder WHERE id_preorder = '$id'");
$dataTransaksi = mysqli_fetch_assoc($queryTransaksi);

$queryItem = mysqli_query($koneksi, "SELECT * FROM item_preorder WHERE id_preorder = '$id'");

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="UTF-8">
    <title> Pre Order </title>
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
                <a href="../barangKeluar.php" class="">
                    <i class='bx bxs-cart-download'></i>
                    <span class="links_name">Transaksi Keluar</span>
                </a>
            </li>
            <li>
                <a href="../preOrder.php" class="active">
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
                <a href="../preOrder.php?aksi=logout" onclick="return confirm('Apakah anda akan keluar?')">
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
                <span class="dashboard">Pre Order</span>
            </div>
            <div class="profile-details">
                <span class="admin_name"><?php echo $sesName; ?></span>
            </div>
        </nav>

        <div class="home-content">
            <div class="sales-boxes">
                <div class="recent-sales1 box">
                    <div class="card-header1">
                        <h3>Edit Pre Order List</h3>
                        <button>
                            <a href="../preOrder.php" style="text-decoration: none;">Kembali</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                    </div>
                    <div class="tambah">
                        <center>
                            <h1>Edit Pre Order</h1>
                        <center>
                        <form method="POST" action="proses_edit.php" enctype="multipart/form-data">
                            <section class="base">
                                <!-- menampung nilai id produk yang akan di edit -->
                                <input type="hidden" name="id_preorder" value="<?php echo $dataTransaksi['id_preorder'] ?>">
                                
                                <div>
                                    <label for="id_preorder">ID PreOrder</label>
                                    <input type="text" name="id_preorder" id="id_preorder" value="<?php echo $dataTransaksi['id_preorder'] ?>" readonly />
                                </div>
                                <div>
                                    <label for="alamat">Alamat</label>
                                    <input type="text" name="alamat" id="alamat" value="<?php echo $dataTransaksi['alamat'] ?>" readonly />
                                </div>
                                <div>
                                    <label for="no_telp">Nomer Telepon</label>
                                    <input type="number" name="no_telp" id="no_telp" value="<?php echo $dataTransaksi['no_telp'] ?>" readonly />
                                </div>
                                <div class="table-responsive">
                                    <table width="100%">
                                        <tr>
                                            <th>ID</th>
                                            <th>Barang</th>
                                            <th>Harga</th>
                                            <th>Qty</th>
                                            <th>Subtotal</th>
                                        </tr>
                                
                                        <?php while ($item = mysqli_fetch_assoc($queryItem)) :
                                        $idb = $item['id_brg'];    
                                        $qty = $item['qty'];    
                                        $subtotal = $item['subtotal'];  
                                        $getDataBarang = mysqli_fetch_assoc(mysqli_query($koneksi, "SELECT * FROM data_brg WHERE id_brg = '$idb'"));
                                        $barang = $getDataBarang['barang'];
                                        $harga = $getDataBarang['hg_jual'];

                                        ?>
                                        <tr>
                                            <td><?= $idb; ?></td>
                                            <td><?= $barang; ?></td>
                                            <td><?= $harga; ?></td>
                                            <td><?= $qty; ?></td>
                                            <td><?= $subtotal; ?></td>
                                        </tr>
                                        <?php endwhile;?>
                                    </table>
                                </div>
                                <div>
                                    <label for="total_preorder">Total Harga</label>
                                    <input type="number" name="total_preorder" id="total_preorder" value="<?php echo $dataTransaksi['total_preorder'] ?>" readonly />
                                </div>
                                <div>
                                    <label for="status">Status</label>
                                    <select name="status" class="custom-select form-control" id="status" autofocus="" required="">
                                        <option value="<?php echo $dataTransaksi['status'];?>"><?php echo $dataTransaksi['status'];?></option>
                                        <option value="#">-- Pilih Option --</option>
                                        <option>Ditolak</option>
                                    </select>
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

