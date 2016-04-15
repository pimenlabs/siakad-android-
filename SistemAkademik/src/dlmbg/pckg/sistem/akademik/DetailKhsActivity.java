package dlmbg.pckg.sistem.akademik;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class DetailKhsActivity extends Activity {

		public String smt,nim;
		JSONArray str_login = null;
		@Override
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.panel_detail_khs);
	        
	        Bundle b = getIntent().getExtras();
			smt = b.getString("kd_smt");
			nim = b.getString("par_kode");


			String link_url = "http://10.0.2.2/siakad-andro/detail-khs.php?nim="+nim+"&smt="+smt;
			//String link_url = "http://10.0.2.2/siakad-andro/detail-khs.php?nim=1109100350&smt="+smt;
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.AmbilJson(link_url);

			try {
				str_login = json.getJSONArray("khs");
				String transkrip = "";
				TextView isi = (TextView) findViewById(R.id.dkhs);
				for(int i = 0; i < str_login.length(); i++){
					JSONObject ar = str_login.getJSONObject(i);
					transkrip += "MK : "+ar.getString("mk")+"\n"+"Jumlah SKS : "+ar.getString("sks")+"\n"+"Semester : "+ar.getString("smt")+"\n"+"Nilai : "+ar.getString("nilai")+"\n\n";
				}
        		isi.setText(transkrip);

				
			} catch (JSONException e) {
				e.printStackTrace();
			}

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
