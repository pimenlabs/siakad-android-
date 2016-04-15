<?php
include("koneksi.php");
$nim=$_GET['nim'];
$q = mysql_query("SELECT semester_ditempuh FROM `eva_tr_nilai` WHERE nim=".$nim." group by semester_ditempuh");
$v = '{"khs" : [';
while($r=mysql_fetch_array($q))
{
	if(strlen($v)<12)
	{
		$v .= '{"smt" : "'.$r['semester_ditempuh'].'"}';
	}
	else
	{
		$v .= ',{"smt" : "'.$r['semester_ditempuh'].'"}';
	}

}
$v .= ']}';
	echo $v;
?>