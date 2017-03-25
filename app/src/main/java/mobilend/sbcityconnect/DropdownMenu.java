package mobilend.sbcityconnect;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bjtke_000 on 3/22/2017.
 */

public class DropdownMenu extends PopupWindow {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> headerList;
    private HashMap<String, List<String>> childList;
    private HashMap<String, List<String>> child2List;

    private Activity host;
    private String user;

    //final PopupWindow menuWindow=new PopupWindow(menuView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public DropdownMenu(final View contentView, int width, int height){
        super(contentView,width,height);
        host=(Activity) contentView.getContext();

        expListView=(ExpandableListView) contentView.findViewById(R.id.dropdown);
        prepareListData();
        listAdapter=new ExpandableListAdapter(contentView.getContext(),headerList,childList);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selection=childList.get(headerList.get(groupPosition)).get(childPosition);
                processMenuSelection(selection);
                return false;
            }
        });
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(listAdapter.getChildrenCount(groupPosition)==0) {
                    String selection = headerList.get(groupPosition);
                    processMenuSelection(selection);
                }
            }
        });
    }

    public void setUser(String name){
        this.user=name;
    }

    private void prepareListData(){
        headerList=new ArrayList<String>();
        childList=new HashMap<String, List<String>>();
        child2List =new HashMap<String, List<String>>();
        //add headers
        headerList.add("HOME");
        headerList.add("LIFE");
        headerList.add("WORK");
        headerList.add("BUSINESS");
        headerList.add("GOVERNMENT");
        headerList.add("SETTINGS");
        headerList.add("LOGOUT");
        //add subcategories
        List<String> life=new ArrayList<String>();
        life.add("Calendar");
        life.add("City Assistance");
        life.add("E-Payment");
        life.add("Mobile Banking");
        life.add("Money Management");
        List<String> money=new ArrayList<>();
        money.add("Budget Builder");
        money.add("Monthly Budget");
        money.add("Monthly Spending");
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

        childList.put(headerList.get(1),life);
        childList.put(headerList.get(2),work);
        childList.put(headerList.get(3),business);
        childList.put(headerList.get(4),government);
    }

    private boolean processMenuSelection(String s){
        Intent intent;
        switch(s){
            case "HOME":
                intent = new Intent(host, HomeActivity.class);
                intent.putExtra("USERNAME", this.user);
                host.startActivity(intent);
                return true;
            case "Calendar":
                intent = new Intent(host, CalendarHomeActivity.class);
                intent.putExtra("USERNAME", this.user);
                host.startActivity(intent);
                return true;
            case "City Assistance":
                intent = new Intent(host, CityAssistanceActivity.class);
                intent.putExtra("USERNAME", this.user);
                host.startActivity(intent);
                return true;
            case "E-Payment":
                intent = new Intent(host, EPaymentActivity.class);
                intent.putExtra("USERNAME", this.user);
                host.startActivity(intent);
                return true;
            case "Money Management":
                intent = new Intent(host, MoneyManagementHomeActivity.class);
                intent.putExtra("USERNAME", this.user);
                host.startActivity(intent);
                return true;
            case "LOGOUT":
                host.startActivity(new Intent(host, MainActivity.class));
                return true;
            default:
                return false;
        }
    }

}
