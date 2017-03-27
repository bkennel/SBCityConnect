package mobilend.sbcityconnect;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    CalendarView calendar;
    GregorianCalendar currentDate;
    ImageButton menuButton;
    String user = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        user = getIntent().getStringExtra("USERNAME");

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        menuButton=(ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add these seven lines inside OnClickListener for menuButton in each activity
                LayoutInflater inflater=(LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View menuView=inflater.inflate(R.layout.dropdown_menu,null);
                final DropdownMenu menuWindow=new DropdownMenu(menuView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                menuWindow.setUser(user);

                menuWindow.setFocusable(true);
                menuWindow.update();
                menuWindow.showAsDropDown(menuButton,-50,0);
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
                return true;
            case R.id.calendar:
                Intent intent = new Intent(this, CalendarActivity.class);
                intent.putExtra("USERNAME", user);
                startActivity(intent);
                return true;
            case R.id.payments:
                //go to payments
                Intent intent2 = new Intent(this, EPaymentActivity.class);
                intent2.putExtra("USERNAME", user);
                startActivity(intent2);
                return true;
            case R.id.moneyManagement:
                Intent intent3 = new Intent(this, MoneyManagementHomeActivity.class);
                intent3.putExtra("USERNAME", user);
                startActivity(intent3);
                return true;
            case R.id.work:
                return true;
            case R.id.business:
                return true;
            case R.id.government:
                return true;
            case R.id.settings:
                return true;
            case R.id.logout:
                startActivity(new Intent(this, MainActivity.class));
                return true;

        }
        return false;
    }


    public void addEvent(View view){
        Calendar c=Calendar.getInstance();
        Intent event=new Intent(Intent.ACTION_EDIT);
        event.setType("vnd.android.cursor.item/event");
        event.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,c.getTimeInMillis());
        event.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,c.getTimeInMillis());
        event.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY,true);
        event.putExtra(CalendarContract.Events.TITLE,"Event");
        event.putExtra(CalendarContract.Events.EVENT_LOCATION,"112 Notre Dame Ave.");
        event.putExtra(CalendarContract.Events.DESCRIPTION,"event description");

        startActivity(event);
    }
}
