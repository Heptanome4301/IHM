package com.example.andri.bonappetit;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Charles on 07/01/2016.
 */
public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        /*Button button = (Button)getActivity().findViewById(R.id.button_date_pick);
        button.setText("WOW NICE!");*/
        TextView text = (TextView)getActivity().findViewById(R.id.time_picked_label);
        text.setText("Le "+dayOfMonth+"/"+(monthOfYear+1));
        DialogFragment myFragment = new TimeDialogFragment();
        myFragment.show(getActivity().getSupportFragmentManager(), "mdrlolxxd");

    }
}
