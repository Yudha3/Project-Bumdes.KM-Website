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
    <link rel="stylesheet" href="../css/reseller.css">
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
                    <a href="reseller.php" class="active"><span class="las la-street-view"></span>
                        <span>Reseller</span></a>
                </li>
                <li>
                    <a href="../barang/barang.php" class=""><span class="las la-box"></span>
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
                Mitra
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
                            <h3>Recent Mitra</h3>
                        </div>

                        <div class="tambah">

                            <center >
                                <h1 cla>Tambah Reseller</h1>
                            <center>
                            <form method="POST" action="proses_tambahreseller.php" enctype="multipart/form-data bg-dark">
                                <section class="base">
                                    <div>
                                        <label>Nama Reseller</label>
                                        <input type="text" name="nama_reseller" autofocus="" required="" />
                                    </div>
                                    <div>
                                        <label>Alamat</label>
                                        <input type="text" name="alamat" required="" />
                                    </div>
                                    <div>
                                        <label>Nomer Telepon</label>
                                        <input type="number" name="no_tlp" required="" />
                                    </div>
                                    <div>
                                        <label>Tanggal Gabung</label>
                                        <input type="date" name="tgl_gabung" required="" />
                                    </div>
                                    <div>
                                        <button type="submit" name="bsimpan">Simpan Reseller</button>
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