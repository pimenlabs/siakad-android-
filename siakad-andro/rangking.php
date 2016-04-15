<?php
include("koneksi.php");
$q = mysql_query('select substring(n.nim,1,4) as angkatan from eva_tr_nilai as n where substring(n.nim,1,2) = 11 or substring(n.nim,1,2) = 31 group by angkatan');
$v = '{"angk" : [';
while($r=mysql_fetch_array($q))
{
	if(strlen($v)<12)
	{
		$v .= '{"angkatan" : "'.$r['angkatan'].'"}';
	}
	else
	{
		$v .= ',{"angkatan" : "'.$r['angkatan'].'"}';
	}

}
$v .= ']}';
	echo $v;
?>