package com.example.isc7424s12020_a1_1518687;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class SummaryOverallActivity<timeText> extends AppCompatActivity {

	private static final String TAG = "SummaryOverallActivity";

	BarChart stackedChart;

	int[] colorClassArray = new int[]{Color.RED, Color.rgb(255,175,0), Color.YELLOW, Color.GREEN, Color.MAGENTA};

	DatabaseHelper myDb;
	private String day, timeSlot;
	private Button monBth, tueBtn, wedBtn, thuBtn, friBtn, overallBtn;
	private TextView overallNumText;
	private int sumOf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary_overall);

		myDb = new DatabaseHelper(this);

		stackedChart = (BarChart) findViewById(R.id.stackChart);
		monBth = (Button) findViewById(R.id.monBtn);
		tueBtn = (Button) findViewById(R.id.tueBtn);
		wedBtn = (Button) findViewById(R.id.wedBtn);
		thuBtn = (Button) findViewById(R.id.thuBtn);
		friBtn = (Button) findViewById(R.id.friBtn);
		overallBtn = (Button) findViewById(R.id.overallBtn);


		overallNumText = (TextView) findViewById(R.id.overallNum);

		int sum = sumDayValues("Monday") + sumDayValues("Tuesday")+ sumDayValues("Wednesday")+sumDayValues("Thursday")+sumDayValues("Friday");
		overallNumText.setText(Integer.toString(sum));
		makeChartAll();


		monBth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				overallNumText.setText(Integer.toString(sumDayValues("Monday")));
				makeChart("Monday", colorClassArray[0]);
			}
		});

		tueBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				overallNumText.setText(Integer.toString(sumDayValues("Tuesday")));
				makeChart("Tuesday", colorClassArray[1]);
			}
		});

		wedBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				overallNumText.setText(Integer.toString(sumDayValues("Wednesday")));
				makeChart("Wednesday", colorClassArray[2]);
			}
		});

		thuBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				overallNumText.setText(Integer.toString(sumDayValues("Thursday")));
				makeChart("Thursday", colorClassArray[3]);
			}
		});

		friBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				overallNumText.setText(Integer.toString(sumDayValues("Friday")));
				makeChart("Friday", colorClassArray[4]);
			}
		});

		overallBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int sum = sumDayValues("Monday") + sumDayValues("Tuesday")+ sumDayValues("Wednesday")+sumDayValues("Thursday")+sumDayValues("Friday");
				overallNumText.setText(Integer.toString(sum));
				makeChartAll();
			}
		});

	}


	private ArrayList<BarEntry> dataValuesAll() {

		Integer[] MonData = dayNum("Monday");
		Integer[] TueData = dayNum("Tuesday");
		Integer[] WedData = dayNum("Wednesday");
		Integer[] ThuData = dayNum("Thursday");
		Integer[] FriData = dayNum("Friday");

//		Log.d(TAG, MonData[0].toString());
		ArrayList<BarEntry> dataValues = new ArrayList<>();
		dataValues.add(new BarEntry(0, new float[]{MonData[0], TueData[0], WedData[0], ThuData[0], FriData[0]}));
		dataValues.add(new BarEntry(1, new float[]{MonData[1], TueData[1], WedData[1], ThuData[1], FriData[1]}));
		dataValues.add(new BarEntry(2, new float[]{MonData[2], TueData[2], WedData[2], ThuData[2], FriData[2]}));
		dataValues.add(new BarEntry(3, new float[]{MonData[3], TueData[3], WedData[3], ThuData[3], FriData[3]}));
		dataValues.add(new BarEntry(4, new float[]{MonData[4], TueData[4], WedData[4], ThuData[4], FriData[4]}));
		dataValues.add(new BarEntry(5, new float[]{MonData[5], TueData[5], WedData[5], ThuData[5], FriData[5]}));
		dataValues.add(new BarEntry(6, new float[]{MonData[6], TueData[6], WedData[6], ThuData[6], FriData[6]}));
		dataValues.add(new BarEntry(7, new float[]{MonData[7], TueData[7], WedData[7], ThuData[7], FriData[7]}));
		return dataValues;
	}

	private ArrayList<BarEntry> dayValues(String day) {

		Integer[] dayData = dayNum(day);
		ArrayList<BarEntry> dataValues = new ArrayList<>();
		dataValues.add(new BarEntry(0, dayData[0]));
		dataValues.add(new BarEntry(1, dayData[1]));
		dataValues.add(new BarEntry(2, dayData[2]));
		dataValues.add(new BarEntry(3, dayData[3]));
		dataValues.add(new BarEntry(4, dayData[4]));
		dataValues.add(new BarEntry(5, dayData[5]));
		dataValues.add(new BarEntry(6, dayData[6]));
		dataValues.add(new BarEntry(7, dayData[7]));
		return dataValues;
	}

	private ArrayList<String> timeOfText() {
		ArrayList<String> timeText = new ArrayList<>();
		timeText.add("09:00 - 10:00");
		timeText.add("10:00 - 11:00");
		timeText.add("11:00 - 12:00");
		timeText.add("12:00 - 13:00");
		timeText.add("13:00 - 14:00");
		timeText.add("14:00 - 15:00");
		timeText.add("15:00 - 16:00");
		timeText.add("16:00 - 17:00");
		return timeText;
	}

	// Set Lable and Axis
	private ArrayList<String> xAxisOfLabel() {
		ArrayList<String> xAxisLabel = new ArrayList<>();
		xAxisLabel.add("09:00");
		xAxisLabel.add("10:00");
		xAxisLabel.add("11:00");
		xAxisLabel.add("12:00");
		xAxisLabel.add("13:00");
		xAxisLabel.add("14:00");
		xAxisLabel.add("15:00");
		xAxisLabel.add("16:00");
		return xAxisLabel;
	}

	private Integer[] dayNum(String day) {
		Integer Data[] = new Integer[8];
		for (int i = 0; i < 8; i++) {
			Cursor countTimeOf = myDb.getTimeOfDate(day, timeOfText().get(i));
			Data[i] = countTimeOf.getCount();
		}
		return Data;
	}

	private void makeChart(String day, int col) {
		BarDataSet DateSet = new BarDataSet(dayValues(day), day +" Summary");
		BarData data = new BarData(DateSet);
		data.setBarWidth(0.8f);
		DateSet.setColor(col);
		DateSet.setValueTextSize(10);
		DateSet.setValueTextSize(10);
		stackedChart.setData(data);
		stackedChart.invalidate();
	}

	private void makeChartAll() {
		BarDataSet barDataSet = new BarDataSet(dataValuesAll(), "Driving appointment");
		barDataSet.setStackLabels(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri"});
		barDataSet.setValueTextSize(10);

		barDataSet.setColors(colorClassArray);
		XAxis xAxis = stackedChart.getXAxis();
		xAxis.setTextSize(10);
		xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisOfLabel()));
		BarData barData = new BarData(barDataSet);
		stackedChart.setData(barData);
		stackedChart.invalidate();
	}

	private int sumDayValues(String day) {
		Integer[] dayData = dayNum(day);
		int sumValue = 0;
		for (int i = 0; i < dayData.length;i++ ) {
			sumValue = sumValue + dayData[i];
		}
		return sumValue;
	}
}

