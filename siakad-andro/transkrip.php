<?php
include("koneksi.php");
$nim = $_GET['nim'];
$q = mysql_query('
		SELECT t_n.kode_mk, t_n.nama_mk, t_n.semester_ditempuh, t_n.jum_sks, t_n.grade, b.bobot, (
		t_n.jum_sks * b.bobot) AS NxB FROM
		(SELECT n.nim, n.kode_mk, mk.nama_mk, mk.jum_sks, n.semester_ditempuh, n.grade
		FROM eva_tr_nilai as n LEFT JOIN ja_mst_mk as mk ON n.kode_mk = mk.kode_mk
		WHERE n.nim = "'.$nim.'") as t_n
		LEFT JOIN eva_mst_bobot as b ON b.nilai = t_n.grade
		LEFT JOIN pw_mst_mahasiswa as m ON t_n.nim = m.nim
		ORDER BY t_n.semester_ditempuh
');
$v = '{"trns" : [';
while($r=mysql_fetch_array($q))
{
	if(strlen($v)<12)
	{
		$v .= '{"mk" : "'.$r['nama_mk'].'", "sks" : "'.$r['jum_sks'].'", "smt" : "'.$r['semester_ditempuh'].'", "nilai" : "'.$r['grade'].'", "bobot" : "'.$r['NxB'].'"}';
	}
	else
	{
		$v .= ',{"mk" : "'.$r['nama_mk'].'", "sks" : "'.$r['jum_sks'].'", "smt" : "'.$r['semester_ditempuh'].'", "nilai" : "'.$r['grade'].'", "bobot" : "'.$r['NxB'].'"}';
	}

}
$v .= ']}';
	echo $v;
?>