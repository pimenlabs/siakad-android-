<?php
include("koneksi.php");
$q = mysql_query('select * from tbl_event');
$v = '{"info" : [';
while($r=mysql_fetch_array($q))
{
	$ob = array("<br>","<b>","</b>");
	if(strlen($v)<12)
	{
		$v .= '{"id" : "'.$r['id'].'", "judul" : "'.$r['title'].'", "tgl" : "'.$r['posting_date'].'", "isi" : "'.str_replace($ob,"",$r['content']).'"}';
	}
	else
	{
		$v .= ',{"id" : "'.$r['id'].'", "judul" : "'.$r['title'].'", "tgl" : "'.$r['posting_date'].'", "isi" : "'.str_replace($ob,"",$r['content']).'"}';
	}

}
$v .= ']}';
	echo $v;
?>