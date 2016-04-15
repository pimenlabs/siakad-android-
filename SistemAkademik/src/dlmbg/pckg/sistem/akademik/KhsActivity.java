package dlmbg.pckg.sistem.akademik;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class KhsActivity extends ListActivity {

		public String nim;
		JSONArray str_login = null;
		ArrayList<HashMap<String, String>> angkatan = new ArrayList<HashMap<String, String>>();
		@Override
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.panel_khs);
	        
	        Bundle b = getIntent().getExtras();
			nim = b.getString("par_kode");
	        
	        JSONParser jParser = new JSONParser();
	        String link_url = "http://10.0.2.2/siakad-andro/khs.php?nim="+nim;
			JSONObject json = jParser.AmbilJson(link_url);

			try {
				str_login = json.getJSONArray("khs");
				
				for(int i = 0; i < str_login.length(); i++){
					JSONObject ar = str_login.getJSONObject(i);
					
					String smt = "Kartu Hasil Studi Semester "+ar.getString("smt");
					
					HashMap<String, String> map = new HashMap<String, String>();

					map.put("semester", smt);
					map.put("kd_smt", ar.getString("smt"));

					angkatan.add(map);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			this.adapter_listview();

		}
		
		public void adapter_listview() {

			ListAdapter adapter = new SimpleAdapter(this, angkatan,R.layout.list_item,new String[] { "semester", "kd_smt"}, new int[] {R.id.tangk, R.id.kodeangk});

			setListAdapter(adapter);
			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					String kode_ang = ((TextView) view.findViewById(R.id.kodeangk)).getText().toString();
					Intent in = new Intent(getApplicationContext(), DetailKhsActivity.class);
					in.putExtra("kd_smt", kode_ang);
					in.putExtra("par_kode", nim);
					startActivity(in);
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
