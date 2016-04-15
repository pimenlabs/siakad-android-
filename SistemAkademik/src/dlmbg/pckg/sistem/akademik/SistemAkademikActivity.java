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

public class SistemAkademikActivity extends Activity {
	String var_usr,var_pass;
	EditText usr, psw;
	JSONArray str_login = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.panel_login);
        
        usr = (EditText) findViewById(R.id.txt_username);
        psw = (EditText) findViewById(R.id.txt_pass);
        
        Button reset = (Button) findViewById(R.id.btn_hapus);
        reset.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		usr.setText("");
        		psw.setText("");
        	}
        });
        
        Button submit = (Button) findViewById(R.id.btn_login);
        submit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				var_usr = usr.getText().toString();
				var_pass = psw.getText().toString();
				String stts = "";
				String kode = "";
				String link_url = "http://10.0.2.2/siakad-andro/login.php?usr="+var_usr+"&psw="+var_pass;
				JSONParser jParser = new JSONParser();
				JSONObject json = jParser.AmbilJson(link_url);

				try {
					str_login = json.getJSONArray("statuslogin");
					
					for(int i = 0; i < str_login.length(); i++){
						JSONObject ar = str_login.getJSONObject(i);
						TextView st = (TextView) findViewById(R.id.txt_alert);
						String alrt = ar.getString("hasil");
						stts = ar.getString("st");
						kode = ar.getString("id");
				        st.setText(alrt);
		        		usr.setText("");
		        		psw.setText("");
				        
						if(stts.trim().equals("ok"))
						{
							Intent ni = null;							
							ni = new Intent(SistemAkademikActivity.this, DashBoardActivity.class);
							Bundle b = new Bundle();
							b.putString("par_kode", kode);
							ni.putExtras(b);
							startActivity(ni);
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