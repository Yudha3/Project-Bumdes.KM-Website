<?php
function generateRow(){
 $contents = '';
 include('koneksi.php');

 $from=$_GET['dari'];
 $end=$_GET['ke'];
 if (empty ($from) or empty ($end)) {
    $query = mysqli_query($koneksi,"select * from report_klr ");
    $no = 1;
    while($row = $query->fetch_assoc()){
        $contents .= "
        <tr>
        <td>".$no++."</td>
        <td>".$row['id_transaksi']."</td>
        <td>".$row['tgl_keluar']."</td>
        <td>".$row['barang']."</td>
        <td>".$row['hg_jual']."</td>
        <td>".$row['jml_keluar']."</td>
        <td>".$row['total_hrg']."</td>
        <td>".$row['penerima']."</td>
        <td>".$row['keterangan']."</td>
        </tr>
        ";
    }
    return $contents;

} else {
    $query = mysqli_query($koneksi,"select * from report_klr WHERE (tgl_keluar BETWEEN '$from' AND '$end')");
    $no = 1;
    while($row = $query->fetch_assoc()){
        $contents .= "
        <tr>
        <td>".$no++."</td>
        <td>".$row['id_transaksi']."</td>
        <td>".$row['tgl_keluar']."</td>
        <td>".$row['barang']."</td>
        <td>".$row['hg_jual']."</td>
        <td>".$row['jml_keluar']."</td>
        <td>".$row['total_hrg']."</td>
        <td>".$row['penerima']."</td>
        <td>".$row['keterangan']."</td>
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
<h2 align="center">Laporan Transaksi Barang Keluar</h2>
<table border="1" cellspacing="0" cellpadding="3">  
<tr>  
<th >No</th>
<th >ID Transaksi</th>
<th >Tanggal Transaksi</th>
<th >Barang</th> 
<th >Harga Jual</th>
<th >Jumlah Stok</th>
<th >Total Harga</th> 
<th >Penerima</th>
<th >Keterangan</th> 
</tr>  
';   
$content .= generateRow();  
$content .= '</table>';  
$pdf->writeHTML($content);  
$pdf->Output('Laporan Transaksi Barang Keluar.pdf', 'I');


?>