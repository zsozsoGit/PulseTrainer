/**
 * 
 */
package com.example.stapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * @author zs
 *
 *//*
public class TrainingRun {
	LocationManager locationManager = (LocationManager) super.getSystemService(Context.LOCATION_SERVICE);
	LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			location.getLatitude();

			textView.setText("Current speed:" + location.getSpeed());
			Vibrator instantVibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			instantVibr.vibrate(3000);

		}

		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			Toast.makeText(context,
					"New GPS State: " + provider.toString(),
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String provider) {
			Toast.makeText(context,
					"GPS Start: " + provider.toString(),
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(context,
					"GPS Stop:" + provider.toString(),
					Toast.LENGTH_SHORT).show();
		}
	};
	try {
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);
	} catch (Exception e) {
		Toast.makeText(context, "Exception: " + e.toString(),Toast.LENGTH_LONG).show();
	}
}
*/