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
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class CekDosenActivity extends Activity {

		TextView ket;
		@Override
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.panel_cek_dosen);

	        ket = (TextView) findViewById(R.id.txt_keterangan);
	        ket.setText("Under Construction\nBelum sempat ane buat gan, silahkan diisi sendiri :p");

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
