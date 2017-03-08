package mobilend.sbcityconnect;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class CalendarHomeActivity extends AppCompatActivity {
    Calendar cal;
    ImageButton addEventButton;
    ArrayList<CalendarEvent> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_home);
        //get current date, set date text field
        cal=Calendar.getInstance();
        SimpleDateFormat d=new SimpleDateFormat("dd, yyyy");
        String date=d.format(cal.getTime());
        String month=cal.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.ENGLISH);
        TextView dateText=(TextView) findViewById(R.id.date);
        dateText.setText(month+" "+date);

        events=new ArrayList<CalendarEvent>();

        //prepare listener for popup window
        addEventButton=(ImageButton) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v){
                LayoutInflater inflater=(LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View eventView=inflater.inflate(R.layout.add_event_popup,null);
                eventView.setBackgroundColor(Color.WHITE);
                final PopupWindow eventWindow=new PopupWindow(eventView,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
                final DatePicker picker=(DatePicker) eventView.findViewById(R.id.datePicker);
                //set on date change listener
                Button okButton=(Button) eventView.findViewById(R.id.okButton);
                okButton.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //get values of fields
                        EditText titlebox=(EditText) eventView.findViewById(R.id.titleBox);
                        String title= titlebox.getText().toString();
                        EditText locBox=(EditText) eventView.findViewById(R.id.locationBox);
                        String location=locBox.getText().toString();
                        int day=picker.getDayOfMonth();
                        int month=picker.getMonth();
                        int year=picker.getYear();
                        CalendarEvent event=new CalendarEvent();
                        event.setTitle(title);
                        event.setLocation(location);
                        event.setDate(year,month,day);
                        //Add to list of events
                        addEvent(event);
                        eventWindow.dismiss();
                    }
                });
                Button cancelButton=(Button) eventView.findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        eventWindow.dismiss();
                    }
                });
                eventWindow.setFocusable(true);
                eventWindow.update();
                eventWindow.showAsDropDown(addEventButton,0,100);

            }
        });
    }

    public void addEvent(CalendarEvent e){
        events.add(e);
        Collections.sort(events,new Comparator<CalendarEvent>(){
            @Override
            public int compare(CalendarEvent c1,CalendarEvent c2){
                return c1.getTime().compareTo(c2.getTime());
            }
        });
        TextView taskHeader=(TextView) findViewById(R.id.taskHeader);
        taskHeader.setText(events.get(0).getTitle());
    }

    public void goToMonthlyCalendar(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
