package mobilend.sbcityconnect;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ActionBar ab=getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);

        calendar=(CalendarView) findViewById(R.id.mainCalendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view,int year,int month,int day){
                Toast.makeText(getApplicationContext(),month+"/"+day+"/"+year,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
