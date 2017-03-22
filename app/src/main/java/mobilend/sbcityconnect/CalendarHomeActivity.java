package mobilend.sbcityconnect;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CalendarHomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Calendar cal;
    ImageButton addEventButton;
    ArrayList<CalendarEvent> events;
    ImageButton menuButton;
    String user = "";

    //For dropdown menu
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> headerList;
    HashMap<String, List<String>> childList;

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

        //setup popup menu
        menuButton=(ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*PopupMenu popup = new PopupMenu(CalendarHomeActivity.this, menuButton);
                popup.setOnMenuItemClickListener(CalendarHomeActivity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();*/

                LayoutInflater inflater=(LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View menuView=inflater.inflate(R.layout.dropdown_menu,null);
                final PopupWindow menuWindow=new PopupWindow(menuView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

                expListView=(ExpandableListView) menuView.findViewById(R.id.dropdown);
                prepareListData();
                listAdapter=new ExpandableListAdapter(menuView.getContext(),headerList,childList);
                expListView.setAdapter(listAdapter);
                expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        String selection=childList.get(headerList.get(groupPosition)).get(childPosition);
                        processMenuSelection(selection);
                        //Toast.makeText(getApplicationContext(),selection, Toast.LENGTH_SHORT).show();
                        menuWindow.dismiss();
                        return false;
                    }
                });

                menuWindow.setFocusable(true);
                menuWindow.update();
                menuWindow.showAsDropDown(menuButton);
            }
        });

        user = getIntent().getStringExtra("USERNAME");

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

    private void prepareListData(){
        headerList=new ArrayList<String>();
        childList=new HashMap<String, List<String>>();
        //add headers
        headerList.add("LIFE");
        headerList.add("WORK");
        headerList.add("BUSINESS");
        headerList.add("GOVERNMENT");
        headerList.add("SETTINGS");
        //add subcategories
        List<String> life=new ArrayList<String>();
        life.add("Calendar");
        life.add("City Assistance");
        life.add("E-Payment");
        life.add("Mobile Banking");
        life.add("Money Management");
        List<String> work=new ArrayList<String>();
        work.add("Open Positions");
        work.add("Job Resources");
        List<String> business=new ArrayList<String>();
        business.add("Business Directory");
        business.add("Business Resources");
        business.add("Chamber of Commerce");
        List<String> government=new ArrayList<String>();
        government.add("Calendar");
        government.add("Upcoming Meetings");
        government.add("Recent Meetings");
        List<String> settings=new ArrayList<String>();
        settings.add("Logout");
        //Add other submenus or app crashes

        childList.put(headerList.get(0),life);
        childList.put(headerList.get(1),work);
        childList.put(headerList.get(2),business);
        childList.put(headerList.get(3),government);
        childList.put(headerList.get(4),settings);
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
        TextView tasks=(TextView) findViewById(R.id.toDoItems);
        String taskText="";
        for(int i=0;i<events.size();i++){
            taskText+=events.get(i).getTitle()+'\n';
        }
        tasks.setText(taskText);
    }

    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.life:
                //TODO - implement submenu
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

    public boolean processMenuSelection(String s){
        Intent intent;
        switch(s){
            case "Calendar":
                intent = new Intent(this, CalendarHomeActivity.class);
                intent.putExtra("USERNAME", user);
                startActivity(intent);
                return true;
            case "City Assistance":
                intent = new Intent(this, CityAssistanceActivity.class);
                intent.putExtra("USERNAME", user);
                startActivity(intent);
                return true;
            case "E-Payment":
                intent = new Intent(this, EPaymentActivity.class);
                intent.putExtra("USERNAME", user);
                startActivity(intent);
                return true;
            case "Money Management":
                intent = new Intent(this, MoneyManagementHomeActivity.class);
                intent.putExtra("USERNAME", user);
                startActivity(intent);
                return true;
            case "Logout":
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return false;
        }
    }

    public void goToMonthlyCalendar(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
        intent.putExtra("USERNAME", user);
        startActivity(intent);
    }
}
