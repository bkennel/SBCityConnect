package mobilend.sbcityconnect;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    CalendarView calendar;
    GregorianCalendar currentDate;
    ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        menuButton=(ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CalendarActivity.this, menuButton);
                popup.setOnMenuItemClickListener(CalendarActivity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
            }
        });


        calendar=(CalendarView) findViewById(R.id.mainCalendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view,int year,int month,int day){
                Toast.makeText(getApplicationContext(),month+"/"+day+"/"+year,Toast.LENGTH_SHORT).show();
                currentDate=new GregorianCalendar(year,month,day);
            }
        });
    }

    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.life:
                //TODO - implement submenu
                Toast.makeText(this,"Life",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.calendar:
                startActivity(new Intent(this, CalendarActivity.class));
                return true;
            case R.id.payments:
                //go to payments
                startActivity(new Intent(this, EPaymentActivity.class));
                return true;
            case R.id.work:
                Toast.makeText(this,"Work",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.business:
                Toast.makeText(this,"Business",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.government:
                Toast.makeText(this,"Government",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                startActivity(new Intent(this, MainActivity.class));
                return true;

        }
        return false;
    }


    public void addEvent(View view){
        /*Intent event=new Intent(Intent.ACTION_INSERT_OR_EDIT);
        event.setType("vnd.android.cursor.item/event");
        event.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,currentDate.getTimeInMillis());
        event.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,currentDate.getTimeInMillis());
        event.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY,true);
        event.putExtra(CalendarContract.Events.TITLE,"Event");
        event.putExtra(CalendarContract.Events.EVENT_LOCATION,"112 Notre Dame Ave.");
        event.putExtra(CalendarContract.Events.DESCRIPTION,"event description");

        startActivity(event);*/
    }
}
