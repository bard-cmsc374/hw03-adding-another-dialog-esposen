package com.example.eliasposen.criminalintent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by eliasposen on 9/26/16.
 */

public class PickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.example.eliasposen.criminalintent.date";

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    public static PickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        PickerFragment fragment = new PickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_picker, null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year,month,day,null);
        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);
        mTimePicker.setHour(hour);
        mTimePicker.setMinute(minute);


        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        int hour = mTimePicker.getHour();
                        int minute = mTimePicker.getMinute();
                        Date date = new GregorianCalendar(year,month,day,hour,minute).getTime();
                        sendResult(Activity.RESULT_OK, date);

                    }
                })
                .create();
    }

    private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null){
            return;
        }
        else{
            Intent intent = new Intent();
            intent.putExtra(EXTRA_DATE, date);
            getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
        }
    }
}
