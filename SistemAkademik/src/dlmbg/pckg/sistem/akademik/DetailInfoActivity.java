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

public class DetailInfoActivity extends Activity {

		public String kode;
		JSONArray str_login = null;
		@Override
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.panel_detail_info);
	        
	        Bundle b = getIntent().getExtras();
			kode = b.getString("par_kode");

			String link_url = "http://10.0.2.2/siakad-andro/detail-info.php?kode="+kode;
			//String link_url = "http://10.0.2.2/siakad-andro/detail-info.php?kode=1";
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.AmbilJson(link_url);

			try {
				str_login = json.getJSONArray("info");
				String jdwl = "";
				TextView isi = (TextView) findViewById(R.id.dinfo);
				for(int i = 0; i < str_login.length(); i++){
					JSONObject ar = str_login.getJSONObject(i);
					jdwl += ar.getString("judul")+"\n"+"Posting : "+ar.getString("tgl")+" | Oleh : Gede Lumbung\n\n"+ar.getString("isi")+"\n\n\n";
				}
        		isi.setText(jdwl);

				
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
