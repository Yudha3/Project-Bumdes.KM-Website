<?php

ob_start();
require('koneksi.php');
require('models/database.php');
include "models/m_barang.php";
$connection = new Database($host, $user, $pass, $nama_db);
session_start();
$sesName = $_SESSION['name'];
$rsl = new Reseller($connection);

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
                <a href="mitra.php" class="">
                    <i class='bx bx-badge-check'></i>
                    <span class="links_name">Mitra</span>
                </a>
            </li>
            <li>
                <a href="reseller.php" class="active">
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
                <span class="dashboard">Reseller</span>
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
                        <h3>Recent Reseller</h3>

                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#tambah">
                            New Reseller
                            <span class="bx bx-right-arrow-alt"></span>
                        </button>
                        <!-- <button>
                            <a href="reseller/newReseller.php" style="text-decoration: none;">New Reseller</a>
                            <span class="bx bx-right-arrow-alt"></span>
                        </button> -->
                    </div>
                    <div class="card-body1">
                        <div class="table-responsive">
                            <table width="100%" class="table">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Nama Reseller</th>
                                        <th>Alamat</th>
                                        <th>Nomer Telepon</th>
                                        <th>Tanggal Gabung</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <?php
                                    $no = 1;
                                    $tampil = $rsl->tampil();
                                    while ($data = $tampil->fetch_object()) {
                                    ?>
                                        <tr>
                                            <td align="center"><?php echo $no++ . "."; ?></td>
                                            <td><?php echo $data->nama_reseller; ?></td>
                                            <td><?php echo $data->alamat; ?></td>
                                            <td><?php echo $data->no_tlp; ?></td>
                                            <td><?php echo $data->tgl_gabung; ?></td>
                                            <td align="center">
                                                <a id="edit_reseller" data-toggle="modal" data-target="#edit" data-id="<?php echo $data->id; ?>"
                                                    data-nama="<?php echo $data->nama_reseller; ?>" data-alamat="<?php echo $data->alamat; ?>" data-nomer="<?php echo $data->no_tlp; ?>"
                                                    data-gabung="<?php echo $data->tgl_gabung; ?>" >
                                                    <button class="btn btn-info btn-xs"> <i class="bx bx-reset"></i> Edit </button></a>
                                                <a href="?page=reseller&act=del&id=<?php echo $data->id; ?>" onclick="return confirm('Yakin akan menghapus data ini?')">
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
                                    <h4 class="modal-title">Tambah Reseller</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="" method="post" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label class="control-label" for="nama_reseller">Nama Reseller</label>
                                            <input type="text" name="nama_reseller" class="form-control" id="nama_reseller" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="alamat">Alamat</label>
                                            <input type="text" name="alamat" class="form-control" id="alamat" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="no_tlp">Nomer Telepon</label>
                                            <input type="number" name="no_tlp" class="form-control" id="no_tlp" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="tgl_gabung">Tanggal Gabung</label>
                                            <input type="date" name="tgl_gabung" class="form-control" id="tgl_gabung" autofocus="" required="" />
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-success" name="tambah" value="Simpan">
                                        <!-- <button type="submit" class="btn btn-success" name="tambah" value="Simpan">Save changes</button> -->
                                    </div>
                                </form>
                                <?php
                                if (@$_POST['tambah']) {
                                    $nama_reseller = $connection->conn->real_escape_string($_POST['nama_reseller']);
                                    $alamat = $connection->conn->real_escape_string($_POST['alamat']);
                                    $no_tlp = $connection->conn->real_escape_string($_POST['no_tlp']);
                                    $tgl_gabung = $connection->conn->real_escape_string($_POST['tgl_gabung']);
                                    $rsl->tambah($nama_reseller, $alamat, $no_tlp, $tgl_gabung);
                                    header("location: ?page=reseller");
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
                                    <h4 class="modal-title">Edit Reseller</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form id="form" enctype="multipart/form-data">
                                    <div class="modal-body" id="modal-edit">
                                        <div class="form-group">
                                            <label class="control-label" for="nama_reseller">Nama Reseller</label>
                                            <input type="hidden" name="id" id="id">
                                            <input type="text" name="nama_reseller" class="form-control" id="nama_reseller" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="alamat">Alamat</label>
                                            <input type="text" name="alamat" class="form-control" id="alamat" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="no_tlp">Nomer Telepon</label>
                                            <input type="number" name="no_tlp" class="form-control" id="no_tlp" autofocus="" required="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="tgl_gabung">Tanggal Gabung</label>
                                            <input type="date" name="tgl_gabung" class="form-control" id="tgl_gabung" autofocus="" required="" />
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
                        $(document).on("click", "#edit_reseller", function(){
                            var id = $(this).data('id');
                            var namarsl = $(this).data('nama');
                            var alamatrsl = $(this).data('alamat');
                            var nomerrsl = $(this).data('nomer');
                            var gabungrsl = $(this).data('gabung');

                            $("#modal-edit #id").val(id);
                            $("#modal-edit #nama_reseller").val(namarsl);
                            $("#modal-edit #alamat").val(alamatrsl);
                            $("#modal-edit #no_tlp").val(nomerrsl);
                            $("#modal-edit #tgl_gabung").val(gabungrsl);
                        })

                        $(document).ready(function(e){
                            $("#form").on("submit", (function(e){
                                e.preventDefault();
                                $.ajax({
                                    url : 'models/proses_editReseller.php',
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
            $('table').DataTable({
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
<?php
} else if(@$_GET['act'] == 'del') {
    $rsl->hapus($_GET['id']);
    header("location: ?page=reseller");
}