package mgalczynski.swm_21_2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv = (ListView) findViewById(R.id.listView);
		final String[] arguments = getResources().getStringArray(
				R.array.mainMenu);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arguments));
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								if (arguments[0].equals(item)) {
									PackageManager manager = getApplicationContext()
											.getPackageManager();
									Intent i = manager
											.getLaunchIntentForPackage("mgalczynski.swm_21_1");
									if (i == null) {
										Toast.makeText(getApplicationContext(),
												R.string.anotherAppFail, 2)
												.show();
									}
									i.addCategory(Intent.CATEGORY_LAUNCHER);
									getApplicationContext().startActivity(i);
								}
								// }else if(arguments[1].equals(item)){
								// Intent in = new Intent(MainActivity.this,
								// ActivityLinear2.class);
								// startActivity(in);
								// }
							}
						});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
