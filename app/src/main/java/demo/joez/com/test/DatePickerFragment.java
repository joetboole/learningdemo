package demo.joez.com.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import demo.joez.com.demolist.R;
import demo.joez.com.item.BaseFragment;


public class DatePickerFragment extends BaseFragment implements DatePicker.OnDateChangedListener{
    private DatePicker mDatePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        Button btnDatePicer = (Button) view.findViewById(R.id.plus_one_button);
        mDatePicker = (DatePicker)view.findViewById(R.id.date_picker);
        Calendar minCalendar = Calendar.getInstance();
        minCalendar.add(Calendar.DATE, -5);
        Calendar maxCalendar = Calendar.getInstance();
        maxCalendar.add(Calendar.DATE, 5);
        mDatePicker.setMinDate(minCalendar.getTimeInMillis());
        mDatePicker.setMaxDate(maxCalendar.getTimeInMillis());
        btnDatePicer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar minCalendar = Calendar.getInstance();
                minCalendar.add(Calendar.DATE, -2);
                Calendar maxCalendar = Calendar.getInstance();
                maxCalendar.add(Calendar.DATE, 5);
                mDatePicker.setMinDate(minCalendar.getTimeInMillis());
                mDatePicker.setMaxDate(maxCalendar.getTimeInMillis());
            }
        });
        Calendar today = Calendar.getInstance();
        mDatePicker.setCalendarViewShown(false);
//        mDatePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE), this);
        return view;
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}
