<?php
include("koneksi.php");
$kode = $_GET['kode'];
$q = mysql_query('select * from tbl_event where id='.$kode.'');
$v = '{"info" : [';
while($r=mysql_fetch_array($q))
{
	$ob = array("<br>","<b>","</b>");
	$v .= '{"id" : "'.$r['id'].'", "judul" : "'.$r['title'].'", "tgl" : "'.$r['posting_date'].'", "isi" : "'.str_replace($ob,"",$r['content']).'"}';

}
$v .= ']}';
	echo $v;
?>