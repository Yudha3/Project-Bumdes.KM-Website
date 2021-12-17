<?php

ob_start();
require('koneksi.php');
require ('models/database.php');
include "models/m_barang.php";

$connection = new Database($host, $user, $pass, $nama_db);
session_start();
$sesName = $_SESSION['name'];
$brg = new Barang($connection);

if (@$_GET['act'] == '') {

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
    <!-- <link rel="stylesheet" type="text/css" href="assets/dataTable/DataTables-1.11.3/css/dataTables.bootstrap4.min.css">
    <script type="text/javascript" src="assets/dataTable/DataTables-1.11.3/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="assets/dataTable/DataTables-1.11.3/js/dataTables.bootstrap4.min.js"></script> -->
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
                <a href="barang.php" class="active">
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
                <span class="dashboard">Barang</span>
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
            <div class="sales-boxes">
                <div class="recent-sales1 box">
                    <div class="card-header1">
                        <h3>Recent Barang</h3>

                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#tambah">
                            New Produk
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>

                        <!-- <button>
                            <a href="barang/newBarang.php" style="text-decoration: none;">New Produk</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button> -->
                    </div>
                    <div class="card-body1">
                        <div class="table-responsive">
                            <table width="100%" class="table">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Nama Barang</th>
                                        <th>Harga Barang</th>
                                        <th>Stok Barang</th>
                                        <th>Gambar Barang</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    $no = 1;
                                    $tampil = $brg->tampil();
                                    while ($data = $tampil->fetch_object()) {
                                    ?>
                                        <tr>
                                            <td align="center"><?php echo $no++."."; ?></td>
                                            <td><?php echo $data->nama_brg; ?></td>
                                            <td>Rp <?php echo $data->harga_brg; ?></td>
                                            <td><?php echo $data->stok_brg; ?></td>
                                            <td style="text-align: center;"><img src="images/barang/<?php echo $data->gambar_brg; ?>" style="width: 120px;"></td>
                                            <td align="center">
                                                <a id="edit_brg" data-toggle="modal" data-target="#edit" data-id="<?php echo $data->id; ?>"
                                                    data-nama="<?php echo $data->nama_brg; ?>" data-jenis="<?php echo $data->jenis_brg; ?>" data-harga="<?php echo $data->harga_brg; ?>"
                                                    data-stok="<?php echo $data->stok_brg; ?>" data-deskripsi="<?php echo $data->deskripsi_brg; ?>" data-gambar="<?php echo $data->gambar_brg; ?>">
                                                    <button class="btn btn-info btn-xs"> <i class="bx bx-reset"></i> Edit </button></a>
                                                <a href="?page=barang&act=del&id=<?php echo $data->id; ?>" onclick="return confirm('Yakin akan menghapus data ini?')">
                                                    <button class="btn btn-danger btn-xs"> <i class="bx bxs-trash-alt"></i> Hapus </button>
                                                </a>
                                            </td>
                                        </tr>
                                    <?php
                                        // $no++; //untuk nomor urut terus bertambah 1
                                    }
                                    ?>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!-- Modal Tambah -->
                    <div id="tambah" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">Tambah Barang</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="" method="post" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label class="control-label" for="nama_brg">Nama Barang</label>
                                            <input type="text" name="nama_brg" class="form-control" id="nama_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="jenis_brg">Jenis Barang</label>
                                            <input type="text" name="jenis_brg" class="form-control" id="jenis_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="harga_brg">Harga Barang</label>
                                            <input type="number" name="harga_brg" class="form-control" id="harga_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="stok_brg">Stok Barang</label>
                                            <input type="number" name="stok_brg" class="form-control" id="stok_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="deskripsi_brg">Deskripsi Barang</label>
                                            <input type="text" name="deskripsi_brg" class="form-control" id="deskripsi_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="gambar_brg">Gambar Barang</label>
                                            <input type="file" name="gambar_brg" class="form-control" id="gambar_brg" autofocus="" required="" />
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-success" name="tambah" value="Simpan">
                                        <!-- <button type="submit" class="btn btn-success" name="tambah" value="Simpan">Save changes</button> -->
                                    </div>
                                </form>
                                <?php
                                    if(@$_POST['tambah']) {
                                        $nama_brg = $connection->conn->real_escape_string($_POST['nama_brg']);
                                        $jenis_brg = $connection->conn->real_escape_string($_POST['jenis_brg']);
                                        $harga_brg = $connection->conn->real_escape_string($_POST['harga_brg']);
                                        $stok_brg = $connection->conn->real_escape_string($_POST['stok_brg']);
                                        $deskripsi_brg = $connection->conn->real_escape_string($_POST['deskripsi_brg']);

                                        $extensi = explode(".", $_FILES['gambar_brg']['name']);
                                        $gambar_brg = "brg-".round(microtime(true)).".".end($extensi);
                                        $sumber = $_FILES['gambar_brg']['tmp_name'];

                                        $upload = move_uploaded_file($sumber, "images/barang/".$gambar_brg);
                                        if($upload) {
                                            $brg->tambah($nama_brg, $jenis_brg, $harga_brg, $stok_brg, $deskripsi_brg, $gambar_brg);
                                            header("location: ?page=barang");
                                        } else {
                                            echo "<script>alert('Upload gambar gagal!')</script>";
                                        }
                                    }
                                ?>
                            </div>
                        </div>
                    </div>

                    <!-- Modal Edit -->
                    <div id="edit" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">Edit Barang</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form id="form" enctype="multipart/form-data">
                                    <div class="modal-body" id="modal-edit">
                                        <div class="form-group">
                                            <label class="control-label" for="nama_brg">Nama Barang</label>
                                            <input type="hidden" name="id" id="id">
                                            <input type="text" name="nama_brg" class="form-control" id="nama_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="jenis_brg">Jenis Barang</label>
                                            <input type="text" name="jenis_brg" class="form-control" id="jenis_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="harga_brg">Harga Barang</label>
                                            <input type="number" name="harga_brg" class="form-control" id="harga_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="stok_brg">Stok Barang</label>
                                            <input type="number" name="stok_brg" class="form-control" id="stok_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="deskripsi_brg">Deskripsi Barang</label>
                                            <input type="text" name="deskripsi_brg" class="form-control" id="deskripsi_brg" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="gambar_brg">Gambar Barang</label>
                                            <div style="padding-bottom:5px">
                                                <img src="" width="80px" id="pict">
                                            </div>
                                            <input type="file" name="gambar_brg" class="form-control" id="gambar_brg">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-success" name="edit" value="Simpan">
                                        <!-- <button type="submit" class="btn btn-success" name="tambah" value="Simpan">Save changes</button> -->
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <script src="assets/js/jquery-1.10.2.js"></script>
                    <script type="text/javascript">
                        $(document).on("click", "#edit_brg", function(){
                            var id = $(this).data('id');
                            var namabrg = $(this).data('nama');
                            var jenisbrg = $(this).data('jenis');
                            var hargabrg = $(this).data('harga');
                            var stokbrg = $(this).data('stok');
                            var deskripsibrg = $(this).data('deskripsi');
                            var gambarbrg = $(this).data('gambar');

                            $("#modal-edit #id").val(id);
                            $("#modal-edit #nama_brg").val(namabrg);
                            $("#modal-edit #jenis_brg").val(jenisbrg);
                            $("#modal-edit #harga_brg").val(hargabrg);
                            $("#modal-edit #stok_brg").val(stokbrg);
                            $("#modal-edit #deskripsi_brg").val(deskripsibrg);
                            $("#modal-edit #pict").attr("src", "images/barang/"+gambarbrg);
                        })

                        $(document).ready(function(e){
                            $("#form").on("submit", (function(e){
                                e.preventDefault();
                                $.ajax({
                                    url : 'models/proses_editBarang.php',
                                    type : 'POST',
                                    data : new FormData(this),
                                    contentType : false,
                                    cache : false,
                                    processData : false,
                                    success : function(msg) {
                                        $('.table').html(msg);
                                    }
                                });
                            }));
                        })

                    </script>

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
            $('#table').DataTable({
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

<?php
} else if(@$_GET['act'] == 'del') {
    $gambar_awal = $brg->tampil($_GET['id'])->fetch_object()->gambar_brg;
    unlink("images/barang/".$gambar_awal);

    $brg->hapus($_GET['id']);
    header("location: ?page=barang");
}