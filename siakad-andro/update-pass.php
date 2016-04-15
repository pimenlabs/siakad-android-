<?php
include("koneksi.php");
$usr = $_GET['usr'];
$psw = md5($_GET['psw']);
$q = mysql_query('
update pw_mst_login set pass="'.$psw.'" where username="'.$usr.'"
');
	$v = '{"stts" : [';
		$v .= '{"st" : "ok"}';
	$v .= ']}';
	echo $v;
?>