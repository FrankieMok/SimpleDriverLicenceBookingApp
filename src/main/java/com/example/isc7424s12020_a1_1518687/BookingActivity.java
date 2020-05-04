package com.example.isc7424s12020_a1_1518687;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

	private static final String TAG = "BookingActivity";
//	private MaterialDialog materialDialog;
//	private BottomSheetMaterialDialog materialBtnDialog;

	DatabaseHelper myDb;

	private TextView licenceTextView;
	private RadioGroup radioGroupDate;
	private  RadioButton radBtnDate_button;

	private RadioGroup radioGroupTime;
	private RadioButton radBtnTime_button;

	private Button submitBtn_button;

	private String DateText, TimeText;
	private String LicNumText;

	// For Custom Dialog
	private TextView dayText, timeSlotText;
	Button positiveBtn, negativeBtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking);

		myDb = new DatabaseHelper(this);  // to Create the SQLite database

		licenceTextView = (TextView)findViewById(R.id.licTextView);

		String LicText = this.getIntent().getStringExtra("licKey");
		licenceTextView.setText(LicText);

		radioGroupDate = (RadioGroup)findViewById(R.id.radioGroupDate);
		radioGroupTime = (RadioGroup)findViewById(R.id.radioGroupTime);
		submitBtn_button = (Button)findViewById(R.id.submitBtn);

		onClickRadioBtn();


	}

	public void onClickRadioBtn() {
		submitBtn_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "Submit action");

				LicNumText = licenceTextView.getText().toString();
				int selected_Date = radioGroupDate.getCheckedRadioButtonId();
				radBtnDate_button = (RadioButton)findViewById(selected_Date);

				int selected_Time = radioGroupTime.getCheckedRadioButtonId();
				radBtnTime_button = (RadioButton)findViewById(selected_Time);

				final AlertDialog.Builder customAlert = new AlertDialog.Builder(BookingActivity.this);
				View mView = getLayoutInflater().inflate(R.layout.activity_custom_dialog, null);

				dayText = (TextView) mView.findViewById(R.id.dayTextView_message);
				timeSlotText = (TextView) mView.findViewById(R.id.timeSlotTextView_message);
				positiveBtn = (Button) mView.findViewById(R.id.button_positive);
				negativeBtn = (Button) mView.findViewById(R.id.button_negative);


				customAlert.setView(mView);

				// Check Radio button clicked or not
				Log.d(TAG, "Check Radio button clicked or not");
				if ((selected_Date == -1) || (selected_Time == -1)) {
					showMessage("Missing information", "Field can't be empty");
					return;
				}

				LicNumText = licenceTextView.getText().toString();
				DateText = radBtnDate_button.getText().toString();
				TimeText = radBtnTime_button.getText().toString();

				// Check if available date of booking within the Licence
				Cursor countDateOf = myDb.getDateOfLic(LicNumText, DateText);

				// Check if available Time slot of booking within the Date
				Cursor countTimeOf = myDb.getTimeOfDate(DateText, TimeText);

				// Check if specific licence be booked at the same day already
				if ( countDateOf.getCount() >=1)
					showMessage("Booking issue", "Appointment already booked at the same day!!");
				// Check if related Time slot be booked more than 10 times
				else if ( countTimeOf.getCount() >=10 )
					showMessage("Booking issue", "This time slot is more than 10 times!!");
				else {
//					Toast.makeText(BookingActivity.this, DateText+TimeText+licNum, Toast.LENGTH_SHORT).show();

					final AlertDialog alertDialog = customAlert.create();
					alertDialog.setCanceledOnTouchOutside(false);
					negativeBtn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							alertDialog.dismiss();
						}
					});

					positiveBtn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {

							boolean isInserted = myDb.insertData(LicNumText, DateText, TimeText);
							if (isInserted == true)
								Toast.makeText(BookingActivity.this, "Booking Submitted!", Toast.LENGTH_SHORT).show();
							else
								Toast.makeText(BookingActivity.this, "Booking not Submitted!", Toast.LENGTH_SHORT).show();

							Intent intent = new Intent(BookingActivity.this, MainActivity.class);
							startActivity(intent);
						}

					});
					dayText.setText(DateText);
					timeSlotText.setText(TimeText);
					alertDialog.show();
				}
			}
		});
	}

	public void showMessage(String Title, String Message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(Title);
		builder.setMessage(Message);
		builder.show();
	}

}
