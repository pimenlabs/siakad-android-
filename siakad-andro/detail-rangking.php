<?php
include("koneksi.php");
$jur=$_GET['kode'];
$q = mysql_query("select nama_mhs,eva_tr_nilai.nim, round(SUM((eva_mst_bobot.bobot * ja_mst_mk.jum_sks))/ SUM(ja_mst_mk.jum_sks), 2) as ip 
		FROM eva_tr_nilai LEFT JOIN (ja_mst_mk,eva_mst_bobot,pw_mst_mahasiswa) 
		ON eva_tr_nilai.kode_mk=ja_mst_mk.kode_mk
		and eva_mst_bobot.nilai = eva_tr_nilai.grade
		and eva_tr_nilai.nim=pw_mst_mahasiswa.nim
 		WHERE  substring(eva_tr_nilai.nim, 1, 4)='".$jur."' AND eva_tr_nilai.grade NOT IN('T') group by eva_tr_nilai.nim ORDER BY ip DESC LIMIT 0, 20");
$v = '{"angk" : [';
while($r=mysql_fetch_array($q))
{
	if(strlen($v)<12)
	{
		$v .= '{"nama" : "'.$r['nama_mhs'].'", "nilai" : "'.$r['ip'].'"}';
	}
	else
	{
		$v .= ',{"nama" : "'.$r['nama_mhs'].'", "nilai" : "'.$r['ip'].'"}';
	}

}
$v .= ']}';
	echo $v;
?>