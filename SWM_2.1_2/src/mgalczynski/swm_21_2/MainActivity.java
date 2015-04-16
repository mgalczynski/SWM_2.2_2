package mgalczynski.swm_21_2;

import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.provider.Settings;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
				if (arguments[0].equals(item)) {
					PackageManager manager = getApplicationContext()
							.getPackageManager();
					try {
						Intent i = manager
								.getLaunchIntentForPackage("mgalczynski.swm_21_1");
						if (i == null) {
							Toast.makeText(getApplicationContext(),
									R.string.anotherAppFail, Toast.LENGTH_SHORT)
									.show();
						}
						i.addCategory(Intent.CATEGORY_LAUNCHER);
						startActivityForResult(i, 0);
					} catch (NullPointerException ex) {
						Toast.makeText(getApplicationContext(),
								R.string.anotherAppFail, Toast.LENGTH_SHORT)
								.show();
					}
				} else if (arguments[1].equals(item)) {
					Intent intent = new Intent(Intent.ACTION_SEARCH);
					intent.putExtra(SearchManager.QUERY, "Android");
					try {
						startActivity(intent);
					} catch (ActivityNotFoundException ex) {
						Toast.makeText(getApplicationContext(), R.string.error,
								Toast.LENGTH_SHORT).show();
					}
				} else if (arguments[2].equals(item)) {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType(HTTP.PLAIN_TEXT_TYPE);
					intent.putExtra("sms_body", "Wiadomoœæ");
					try {
						startActivity(intent);
					} catch (ActivityNotFoundException ex) {
						Toast.makeText(getApplicationContext(), R.string.error,
								Toast.LENGTH_SHORT).show();
					}
				} else if (arguments[3].equals(item)) {
					Uri webpage = Uri.parse("http://www.pwr.edu.pl");
					Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
					try {
						startActivity(intent);
					} catch (ActivityNotFoundException ex) {
						Toast.makeText(getApplicationContext(), R.string.error,
								Toast.LENGTH_SHORT).show();
					}
				}
			};
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(getApplicationContext(), R.string.resume,
				Toast.LENGTH_SHORT).show();
	};

	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(getApplicationContext(), R.string.pause,
				Toast.LENGTH_SHORT).show();
	};

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
