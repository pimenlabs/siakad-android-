<?php
include("koneksi.php");
$usr = $_GET['usr'];
$psw = md5($_GET['psw']);
$q = mysql_query('
SELECT * FROM pw_mst_login where username="'.$usr.'" and pass="'.$psw.'"
');
if(mysql_num_rows($q)>0)
{
	$v = '{"statuslogin" : [';
	$v .= '{"st" : "ok", "id" : "'.$usr.'", "hasil" : ""}';
	$v .= ']}';
	echo $v;
}
else
{
	$v = '{"statuslogin" : [';
	$v .= '{"st" : "gagal", "id" : "'.$usr.'", "hasil" : "Gagal menyimpan password..!!!"}';
	$v .= ']}';
	echo $v;
}
?>