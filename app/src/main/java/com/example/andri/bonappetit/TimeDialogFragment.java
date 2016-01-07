package com.example.andri.bonappetit;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Charles on 07/01/2016.
 */
public class TimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of DatePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView text = (TextView)getActivity().findViewById(R.id.time_picked_label);
        text.setText(text.getText()+" à "+hourOfDay+"h"+minute);
        text.setVisibility(View.VISIBLE);
    }
}
