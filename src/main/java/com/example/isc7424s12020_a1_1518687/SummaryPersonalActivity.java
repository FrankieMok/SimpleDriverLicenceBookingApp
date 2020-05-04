package com.example.isc7424s12020_a1_1518687;


import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SummaryPersonalActivity extends AppCompatActivity {

	private static final String TAG = "SummaryPersonalActivity";

	DatabaseHelper myDb;

	TextView licenceTextView;
	String licNumValue;

	ListView myListView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary_personal);

		licenceTextView = (TextView) findViewById(R.id.licTextView);
		String licNumText = this.getIntent().getStringExtra("licKey");
		if (licNumText == null) licNumText = "DM123456";
		licenceTextView.setText(licNumText);
		myListView = (ListView) findViewById(R.id.summaryListView);

		myDb = new DatabaseHelper(this);

		// get specific lic data
		Cursor result = myDb.getAllData();
		ArrayList<String> listLicDate = new ArrayList<>();
		ArrayList<String> listLicTime = new ArrayList<>();

		licNumValue = licenceTextView.getText().toString().trim();
		while (result.moveToNext()) {
			//get the value from the database in column 1
			//then add it to the ArrayList
			if (result.getString(1).contains(licNumValue)) {
				listLicDate.add(result.getString(2));
				listLicTime.add(result.getString(3));
			}
			Log.d(TAG, "get an appointment!!");

		}
//		toastMessage(listLicDate.toString() + listLicTime.toString());

		//create the list adapter and set the adapter
		CustomAdapter customAdapter = new CustomAdapter(SummaryPersonalActivity.this, listLicDate, listLicTime);
		myListView.setAdapter(customAdapter);

		

	}
	/**
	 * customizable toast
	 * @param message
	 */
	private void toastMessage(String message){
		Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
	}
}
