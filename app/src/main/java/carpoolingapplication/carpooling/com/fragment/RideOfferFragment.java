package carpoolingapplication.carpooling.com.fragment;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import carpoolingapplication.carpooling.com.R;
import carpoolingapplication.carpooling.com.map.MapsActivity;
import carpoolingapplication.carpooling.com.map.SearchMapActivity;


public class RideOfferFragment extends Fragment {

    private TextView s1Text;
    private EditText sText;
    private TextView d1Text;
    private EditText dText;
    private TextView t1Text;
    private EditText tText;
    private TextView timePic;
    private TextView date1Text;
    private EditText dateText;
    private TextView datePic;
    private Button search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_ride_offer, container, false);


        s1Text = (TextView) view.findViewById(R.id.s1Text);
        sText = (EditText) view.findViewById(R.id.sText);
        d1Text = (TextView) view.findViewById(R.id.d1Text);
        dText = (EditText) view.findViewById(R.id.dText);
        t1Text = (TextView) view.findViewById(R.id.t1Text);
        tText = (EditText) view.findViewById(R.id.tText);
        timePic = (TextView) view.findViewById(R.id.time_pic);
        date1Text = (TextView) view.findViewById(R.id.date1Text);
        dateText = (EditText) view.findViewById(R.id.dateText);
        datePic = (TextView) view.findViewById(R.id.date_pic);
        search = (Button) view.findViewById(R.id.search);

        datePic.setOnClickListener(new View.OnClickListener() {
            public int mDay;
            public int mMonth;
            public int mYear;

            @Override
            public void onClick(View view) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        timePic.setOnClickListener(new View.OnClickListener() {
            public int mMinute;
            public int mHour;

            @Override
            public void onClick(View view) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tText.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchMapActivity.class));
            }
        });

        return view;

    }

}
