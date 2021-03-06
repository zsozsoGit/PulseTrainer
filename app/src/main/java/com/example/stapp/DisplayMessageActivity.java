package com.example.stapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends Activity {
    private final float cRecGoldenRatio = (float) ((float) 2 / (1 + Math.sqrt(5)));
    TextView textView;
    private Context context;
	private double previousSpeedInMinutesPerKm = 0;
	private float currentSpeed = 0;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
		final Vibrator instantVibrator;
        instantVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        /* Get the message from the intent */
		Intent intent = getIntent();
		final double speed = intent.getDoubleExtra(MainActivity.SPEED_MESSAGE, 0);
		if (speed == 0) {
			System.exit(2);
		}

		// Create the text view
        textView = (TextView) findViewById(R.id.textView1);
        textView.setTextSize(120);
		textView.setText(Double.toString(speed));

		// Set the text view as the activity layout
		final LocationManager locationManager = (LocationManager) super.getSystemService(Context.LOCATION_SERVICE);

		final LocationListener locationListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
                currentSpeed = (cRecGoldenRatio * location.getSpeed()) + (currentSpeed * (1 - cRecGoldenRatio));
				if (currentSpeed > 0.1) {
					double speedInMinutesPerKm = (1000 / 60) / currentSpeed;
                    textView.setText(String.format("Speed: %.2f", speedInMinutesPerKm));
                    // Faster means smaller value in this case
					if ((speedInMinutesPerKm <= speed) && (previousSpeedInMinutesPerKm > speed)) {
						textView.setTextColor(Color.GREEN);
						
						instantVibrator.vibrate(new long[]{0, 100, 50, 100}, -1);
					}
					if ((speedInMinutesPerKm > speed) && (previousSpeedInMinutesPerKm <= speed)) {
						textView.setTextColor(Color.RED);
						instantVibrator.vibrate(1000);
					}
					previousSpeedInMinutesPerKm = speedInMinutesPerKm;
				}
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {
				Toast.makeText(context, "New GPS State: " + provider.toString(), Toast.LENGTH_SHORT).show();
			}

			public void onProviderEnabled(String provider) {
				Toast.makeText(context, "GPS Start: " + provider.toString(), Toast.LENGTH_SHORT).show();
			}

			public void onProviderDisabled(String provider) {
				Toast.makeText(context, "GPS Stop:" + provider.toString(), Toast.LENGTH_SHORT).show();
			}
		};
		try {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
		} catch (Exception e) {

		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
