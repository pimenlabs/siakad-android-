package dlmbg.pckg.sistem.akademik;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class DashBoardActivity extends Activity {
	
	public String nim;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.panel_dashboard);
        
        Bundle b = getIntent().getExtras();
		nim = b.getString("par_kode");
		
        Button btn_jadwal = (Button) findViewById(R.id.btn_jadwal);
        Button btn_khs = (Button) findViewById(R.id.btn_khs);
        Button btn_transkrip = (Button) findViewById(R.id.btn_transkrip);
        Button btn_rangking = (Button) findViewById(R.id.btn_rangking);
        Button btn_info = (Button) findViewById(R.id.btn_info);
        Button btn_keuangan = (Button) findViewById(R.id.btn_keuangan);
        Button btn_akun = (Button) findViewById(R.id.btn_akun);
        Button btn_logout = (Button) findViewById(R.id.btn_logout);

        btn_jadwal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {						
				Intent i = new Intent(getApplicationContext(), JadwalKuliahActivity.class);
				Bundle b = new Bundle();
				b.putString("par_kode", nim);
				i.putExtras(b);
				startActivity(i);
			}
		});

        btn_transkrip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {						
				Intent i = new Intent(getApplicationContext(), TranskripActivity.class);
				Bundle b = new Bundle();
				b.putString("par_kode", nim);
				i.putExtras(b);
				startActivity(i);
			}
		});

        btn_akun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {						
				Intent i = new Intent(getApplicationContext(), AkunActivity.class);
				Bundle b = new Bundle();
				b.putString("par_kode", nim);
				i.putExtras(b);
				startActivity(i);
			}
		});

        btn_info.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {						
				Intent i = new Intent(getApplicationContext(), InfoKampusActivity.class);
				startActivity(i);
			}
		});

        btn_keuangan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {						
				Intent i = new Intent(getApplicationContext(), CekDosenActivity.class);
				startActivity(i);
			}
		});

        btn_khs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {						
				Intent i = new Intent(getApplicationContext(), KhsActivity.class);
				Bundle b = new Bundle();
				b.putString("par_kode", nim);
				i.putExtras(b);
				startActivity(i);
			}
		});

        btn_rangking.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {						
				Intent i = new Intent(getApplicationContext(), RangkingActivity.class);
				startActivity(i);
			}
		});

        btn_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {	
					Intent exit = new Intent(Intent.ACTION_MAIN);
					exit.addCategory(Intent.CATEGORY_HOME); exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					DashBoardActivity.this.finish();
					startActivity(exit);
			}
		});
	}
 
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opt_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.url:
        		Intent intent = null;
        		intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://stikombanyuwangi.ac.id"));
        		startActivity(intent);
        		return true;
        	case R.id.tentang:
        		AlertDialog alertDialog;
        		alertDialog = new AlertDialog.Builder(this).create();
        		alertDialog.setTitle("SIAKAD STIKOM BANYUWANGI");
        		alertDialog.setMessage("Aplikasi SIAKAD berbasis Android ini merupakan salah satu dari sekian banyak proyek 2M" +
        				" serta segelintir penelitian yang saya kerjakan di kampus. Semoga aplikasi ini bisa bermanfaat untuk " +
        				" kita semua.\n\nSalam, Gede Lumbung\nhttp://gedelumbung.com");
        		alertDialog.setButton("#OKOK", new DialogInterface.OnClickListener() {
        		    @Override
        		    public void onClick(DialogInterface dialog, int which) {
        		        dialog.dismiss();
        		    }
        		});
        		alertDialog.show();
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
        }
    }

}
