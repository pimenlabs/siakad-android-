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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AkunActivity extends Activity {
	String var_pass_lama,var_pass,nim;
	EditText psw_lama, psw_baru;
	JSONArray str_login = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.panel_akun);
        
        Bundle b = getIntent().getExtras();
		nim = b.getString("par_kode");
        
        psw_lama = (EditText) findViewById(R.id.txt_pass_lama);
        psw_baru = (EditText) findViewById(R.id.txt_pass_baru);
        
        Button reset = (Button) findViewById(R.id.btn_hapus_pass);
        reset.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		psw_lama.setText("");
        		psw_baru.setText("");
        	}
        });
        
        Button submit = (Button) findViewById(R.id.btn_simpan_pass);
        submit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				var_pass_lama = psw_lama.getText().toString();
				var_pass = psw_baru.getText().toString();
				String stts = "";
				String link_url = "http://10.0.2.2/siakad-andro/cek-pass.php?usr="+nim+"&psw="+var_pass_lama;
				JSONParser jParser = new JSONParser();
				JSONObject json = jParser.AmbilJson(link_url);

				try {
					str_login = json.getJSONArray("statuslogin");
					
					for(int i = 0; i < str_login.length(); i++){
						JSONObject ar = str_login.getJSONObject(i);
						TextView st = (TextView) findViewById(R.id.txt_alert_pass);
						String alrt = ar.getString("hasil");
						stts = ar.getString("st");
				        st.setText(alrt);
				        
						if(stts.trim().equals("ok"))
						{
							String url_updt = "http://10.0.2.2/siakad-andro/update-pass.php?usr="+nim+"&psw="+var_pass;
							jParser.AmbilJson(url_updt);
					        st.setText("Berhasil menyimpan password...!!!");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
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