package com.example.isc7424s12020_a1_1518687;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import static com.example.isc7424s12020_a1_1518687.R.id;
import static com.example.isc7424s12020_a1_1518687.R.layout;
import static com.example.isc7424s12020_a1_1518687.R.string.licIsEmpty;
import static com.example.isc7424s12020_a1_1518687.R.string.licIsTooLong;
import static com.example.isc7424s12020_a1_1518687.R.string.menu_Limitation;
import static com.example.isc7424s12020_a1_1518687.R.string.menu_TimeSlot;
import static com.example.isc7424s12020_a1_1518687.R.string.nzta_info;

public class MainActivity extends AppCompatActivity {


	private static final String TAG = "MainActivity";

	private TextInputLayout textInputLicense;
	private Button makeAppBtn, summaryPersonalBtn, summaryOverallBtn;
	private String licNumValue;

	DatabaseHelper myDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_main);


		makeAppBtn = (Button) findViewById(id.makeAppBtn);
		summaryPersonalBtn = (Button) findViewById(id.summaryPersonalBtn);
		textInputLicense = (TextInputLayout) findViewById(id.textInputEditText);
		licNumValue = textInputLicense.getEditText().getText().toString().trim();

		myDb = new DatabaseHelper(this);


	}


	// Check the License Availability - Cannot be empty, Cannot more than 8 words
	public boolean validateLicense() {
		String licenseInput = textInputLicense.getEditText().getText().toString().trim();
		Log.d(TAG, "Get driving license.");
//		Toast.makeText(MainActivity.this, licenseInput, Toast.LENGTH_SHORT).show();

		if (licenseInput.isEmpty()) {
			textInputLicense.setError(getString(licIsEmpty));
			return false;
		} else if (licenseInput.length() > 8) {
			textInputLicense.setError(getString(licIsTooLong));
			return false;
		} else {
			textInputLicense.setError(null);
			return true;
		}

	}

	// Make appointment - Check the Licence before
	public void makeAppListener(View view) {
		if (!validateLicense()) {
			return;
		}
		licNumValue = textInputLicense.getEditText().getText().toString().trim();
		Intent intent = new Intent(MainActivity.this, BookingActivity.class);
		intent.putExtra("licKey", licNumValue);
		startActivity(intent);

	}

	public void checkAppPersonalListener(View view) {
		// Check if driving licence input
		if (!validateLicense()) {
			return;
		}

		//get database
		licNumValue = textInputLicense.getEditText().getText().toString().trim();
		Cursor result = myDb.getLic(licNumValue);
//		Cursor result = myDb.getAllData();
//		// Check if available and empty data
		if (result.getCount() == 0) {
			showMessage("Warning!", "No available appointment!!");
			return;
		}

			Intent intent = new Intent(MainActivity.this, SummaryPersonalActivity.class);
			intent.putExtra("licKey", licNumValue);
			startActivity(intent);

	}

	public void showMessage(String Title, String Message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(Title);
		builder.setMessage(Message);
		builder.show();
	}

	public void checkAppOverallListener(View view) {
		Intent intent = new Intent(MainActivity.this, SummaryOverallActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_booking_scrolling,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case id.item1:
				showMessage("NZTA", getString(nzta_info));
				return true;
			case id.menuTimeSlot:
				showMessage("Available Time Slot", getString(menu_TimeSlot));
				return true;
			case id.menuLimit:
				showMessage("Limitation", getString(menu_Limitation));
				return true;

			default:return super.onOptionsItemSelected(item);
		}
	}

}
