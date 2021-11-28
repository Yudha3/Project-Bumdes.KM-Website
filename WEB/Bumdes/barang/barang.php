<?php
include('koneksi.php'); //agar index terhubung dengan database, maka koneksi sebagai penghubung harus di include
// require_once('koneksi.php');
// require_once('database.php');

// $connection = new Database($host, $user, $pass, $database);
// include "m_barang.php";
// $brg = new Barang($connection);

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1">
    <title>Bumdes Admin</title>
    <link rel="stylesheet" href="https://maxst.icons8.com/vue-
    static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="css/barang.css">
    <link rel="stylesheet" href="1.3.0/css/line-awesome.min.css">
    <!-- <link rel="stylesheet" href="assets/css/bootstrap.css"> -->

</head>

<body>

    <!-- <script type="text/javascript" src="assets/js/bootstrap.js"></script> -->

    <input type="checkbox" id="nav-toggle">
    <div class="sidebar">
        <div class="sidebar-brand">
            <h2><span class="lab la-amazon"></span> <span>Bumdes.KM</span></h2>
        </div>

        <div class="sidebar-menu">
            <ul>
                <li>
                    <a href="dasboard.html" class=""><span class="las la-igloo"></span>
                        <span>Dasboard</span></a>
                </li>
                <li>
                    <a href="Pages.html" class=""><span class="las la-tags"></span>
                        <span>Pages</span></a>
                </li>
                <li>
                    <a href="profile.html" class=""><span class="las la-user"></span>
                        <span>Profile</span></a>
                </li>
                <li>
                    <a href="../mitra/Mitra.php" class=""><span class="las la-users"></span>
                        <span>Mitra</span></a>
                </li>
                <li>
                    <a href="../reseller/reseller.php" class=""><span class="las la-street-view"></span>
                        <span>Reseller</span></a>
                </li>
                <li>
                    <a href="../barang/barang.php" class="active"><span class="las la-box"></span>
                        <span>Barang</span></a>
                </li>
                <li>
                    <a href="" class=""><span class="las la-shopping-cart"></span>
                        <span>Transaksi</span></a>
                </li>
                <li>
                    <a href="" class=""><span class="las la-file-alt"></span>
                        <span>Laporan</span></a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main-content">
        <header>
            <h2>
                <label for="nav-toggle">
                    <span class="las la-bars"></span>
                </label>
                Barang
            </h2>

            <div class="search-wrapper">
                <span class="las la-search"></span>
                <input type="search" placeholder="Search here..." />
            </div>

            <!-- <div class="icons">
                <div>
                    <a href=""><span class="las la-bell"></span></a>
                </div>
                <div>
                    <a href=""><span class="las la-envelope"></span></a>
                </div>
            </div> -->

            <div class="user-wrapper">
                <div>
                    <a href=""><span class="las la-bell"></span></a>
                </div>
                <div>
                    <a href=""><span class="las la-envelope"></span></a>
                </div>
                <img src="img/p.jpeg" width="40px" height="40px" alt="">
                <div>
                    <h4>Boy Dymas Hidayat</h4>
                    <small>Admin</small>
                </div>
            </div>
        </header>

        <main>

            <div class="recent-grid">
                <div class="projects">
                    <div class="card">
                        <div class="card-header">
                            <h3>Recent Barang</h3>

                            <button><a href="newBarang.php" style="text-decoration: none;">New Produk</a><span class="las la-arrow-right">
                                </span></button>
                        </div>

                        <div class="card-body">
                            <div>
                                <div class="search-wr">
                                    <span class="las la-search"></span>
                                    <input type="search" placeholder="Search here..." />
                                </div>
                                <!-- <div class="show">
                                    <div>
                                        <h4>Show</h4>
                                        <span>7</span>
                                    </div>
                                    <div>
                                        <span class="las la-arrows-alt-v"></span>
                                    </div>
                                </div> -->
                            </div>

                            <div class="table-responsive">
                                <table width="100%">
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
                                                <td style="text-align: center;"><img src="img/gambar/<?php echo $row['gambar_brg']; ?>" style="width: 120px;"></td>
                                                <td>
                                                    <a href="editBarang.php?id=<?php echo $row['id']; ?>">Edit</a> |
                                                    <a href="proses_hapus.php?id=<?php echo $row['id']; ?>" onclick="return confirm('Anda yakin akan menghapus data ini?')">Hapus</a>
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


            <!-- <div id="tambah" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"></button>
                            <h4 class="modal-title">Tambah Data Barang</h4>
                        </div>
                        <form method="post" enctype="multipart/form-data">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label" for="nama_brg">Nama Barang</label>
                                    <input type="text" name="nama_brg">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div> -->

            <!-- <div class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Tambah Data Barang</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form method="POST" action="proses_tambah.php" enctype="multipart/form-data">
                                <section class="base">
                                    <div>
                                        <label>Nama Barang</label>
                                        <input type="text" name="nama_brg" autofocus="" required="" />
                                    </div>
                                    <div>
                                        <label>Jenis Barang</label>
                                        <input type="text" name="jenis_brg" required="" />
                                    </div>
                                    <div>
                                        <label>Harga Barang</label>
                                        <input type="text" name="harga_brg" required="" />
                                    </div>
                                    <div>
                                        <label>Stok Barang</label>
                                        <input type="text" name="stok_brg" required="" />
                                    </div>
                                    <div>
                                        <label>Deskripsi</label>
                                        <input type="text" name="deskripsi_brg" />
                                    </div>
                                    <div>
                                        <label>Gambar Produk</label>
                                        <input type="file" name="gambar_brg" required="" />
                                    </div>
                                    <div>
                                        <button type="submit">Simpan Produk</button>
                                    </div>
                                </section>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div> -->


        </main>
    </div>

</body>

</html>