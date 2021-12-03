<?php

include('../koneksi.php'); //agar index terhubung dengan database, maka koneksi sebagai penghubung harus di include

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
    <link rel="stylesheet" href="../css/barang.css">
    <link rel="stylesheet" href="../1.3.0/css/line-awesome.min.css">

</head>

<body>

    <input type="checkbox" id="nav-toggle">
    <div class="sidebar">
        <div class="sidebar-brand">
            <h2><span class="lab la-amazon"></span> <span>Bumdes.KM</span></h2>
        </div>

        <div class="sidebar-menu">
            <ul>
                <li>
                    <a href="../dasboard.php" class=""><span class="las la-igloo"></span>
                        <span>Dasboard</span></a>
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
                    <a href="barang.php" class="active"><span class="las la-box"></span>
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

            <div class="user-wrapper">
                <div>
                    <a href=""><span class="las la-bell"></span></a>
                </div>
                <div>
                    <a href=""><span class="las la-envelope"></span></a>
                </div>
                <img src="../img/avatar.svg" width="40px" height="40px" alt="">
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
                        </div>

                        <div class="tambah">

                            <center>
                                <h1>Tambah Produk</h1>
                            <center>
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
                    </div>
                </div>
            </div>
        </main>
    </div>

</body>

</html>