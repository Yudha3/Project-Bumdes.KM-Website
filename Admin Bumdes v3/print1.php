<?php
function generateRow(){
 $contents = '';
 include('koneksi.php');

 $from=@$_GET['dari'];
 $end=@$_GET['ke'];
 if (empty ($from) or empty ($end)) {
        $query = mysqli_query($koneksi,"SELECT * FROM report_msk ORDER BY id DESC");
        $no = 1;
        while($row = $query->fetch_assoc()){
            $contents .= "
            <tr>
            <td>".$no++."</td>
            <td>".$row['id_transaksi']."</td>
            <td>".$row['tgl_msk']."</td>
            <td>".$row['id_brg']."</td>
            <td>".$row['barang']."</td>
            <td>".$row['pengirim']."</td>
            <td>".number_format($row['hg_beli'],0,',','.')."</td>
            <td>".$row['jml_masuk']."</td>
            <td>".number_format($row['total_hrg'],0,',','.')."</td>
            </tr>
            ";
        }
        return $contents;

    } else {
        $query = mysqli_query($koneksi,"SELECT * FROM report_msk WHERE (tgl_msk BETWEEN '$from' AND '$end')");
        $no = 1;
        while($row = $query->fetch_assoc()){
            $contents .= "
            <tr>
            <td>".$no++."</td>
            <td>".$row['id_transaksi']."</td>
            <td>".$row['tgl_msk']."</td>
            <td>".$row['id_brg']."</td>
            <td>".$row['barang']."</td>
            <td>".$row['pengirim']."</td>
            <td>".number_format($row['hg_beli'],0,',','.')."</td>
            <td>".$row['jml_masuk']."</td>
            <td>".number_format($row['total_hrg'],0,',','.')."</td>
            </tr>
            ";
        }
        return $contents;

    }
    // return $contents;
}

require_once('assets/tcpdf/tcpdf.php');  
$pdf = new TCPDF('P', PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);  
$pdf->SetCreator(PDF_CREATOR);  
$pdf->SetTitle("Generated PDF using TCPDF");  
$pdf->SetHeaderData('', '', PDF_HEADER_TITLE, PDF_HEADER_STRING);  
$pdf->setHeaderFont(Array(PDF_FONT_NAME_MAIN, '', PDF_FONT_SIZE_MAIN));  
$pdf->setFooterFont(Array(PDF_FONT_NAME_DATA, '', PDF_FONT_SIZE_DATA));  
$pdf->SetDefaultMonospacedFont('helvetica');  
$pdf->SetFooterMargin(PDF_MARGIN_FOOTER);  
$pdf->SetMargins(PDF_MARGIN_LEFT, '10', PDF_MARGIN_RIGHT);  
$pdf->setPrintHeader(false);  
$pdf->setPrintFooter(false);  
$pdf->SetAutoPageBreak(TRUE, 10);  
$pdf->SetFont('helvetica', '', 11);  
$pdf->AddPage();  
$content = '';  
$content .= '
<h2 align="center">Laporan Transaksi Barang Masuk</h2>
<table border="1" cellspacing="0" cellpadding="3">  
<tr>  
<th >No</th>
<th >ID Transaksi</th>
<th >Tanggal Transaksi</th>
<th >Kode Barang</th>
<th >Barang</th>
<th >Pengirim</th> 
<th >Harga Beli</th> 
<th >Jumlah Stok</th> 
<th >Total Harga</th> 
</tr>  
';   
$content .= generateRow();  
$content .= '</table>';  
$pdf->writeHTML($content);  
$pdf->Output('Laporan Transaksi Barang Masuk.pdf', 'I');
