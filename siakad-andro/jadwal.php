<?php
include("koneksi.php");
$nim = $_GET['nim'];
$q = mysql_query('
select * from pw_tr_perwalian_detail as a left join ja_mst_mk as b on a.kode_mk=b.kode_mk left join ja_mst_dosen as c on a.kode_dosen=c.kode_dosen where nim="'.$nim.'"
');
$v = '{"jdw" : [';
while($r=mysql_fetch_array($q))
{
	if(strlen($v)<11)
	{
		$v .= '{"mk" : "'.$r['nama_mk'].'", "jadwal" : "'.str_replace("/","-",$r['jadwal']).'", "dosen" : "'.$r['nama_dosen'].'", "sks" : "'.$r['jum_sks'].'", "smt" : "'.$r['semester'].'"}';
	}
	else
	{
		$v .= ',{"mk" : "'.$r['nama_mk'].'", "jadwal" : "'.str_replace("/","-",$r['jadwal']).'", "dosen" : "'.$r['nama_dosen'].'", "sks" : "'.$r['jum_sks'].'", "smt" : "'.$r['semester'].'"}';
	}

}
$v .= ']}';
	echo $v;
?>