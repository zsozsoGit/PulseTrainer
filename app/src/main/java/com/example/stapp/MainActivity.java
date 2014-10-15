package com.example.stapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String NUMREPS_MESSAGE = "com.example.myfirstapp.NUMREPS_MESSAGE";
	public final static String RUNLEN_MESSAGE = "com.example.myfirstapp.RUNLEN_MESSAGE";
	public final static String RESTLEN_MESSAGE = "com.example.myfirstapp.RESTLEN_MESSAGE";
	public final static String SPEED_MESSAGE = "com.example.myfirstapp.SPEED_MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		int numReps = Integer
				.parseInt(((EditText) findViewById(R.id.NumofReps)).getText()
						.toString());
		int runL = Integer.parseInt(((EditText) findViewById(R.id.RunLenght))
				.getText().toString());
		int restL = Integer.parseInt(((EditText) findViewById(R.id.RestLength))
				.getText().toString());
		double speed = Double.parseDouble(((EditText) findViewById(R.id.Speed))
				.getText().toString());
		intent.putExtra(NUMREPS_MESSAGE, numReps);
		intent.putExtra(RUNLEN_MESSAGE, runL);
		intent.putExtra(RESTLEN_MESSAGE, restL);
		intent.putExtra(SPEED_MESSAGE, speed);
		startActivity(intent);
	}

}
