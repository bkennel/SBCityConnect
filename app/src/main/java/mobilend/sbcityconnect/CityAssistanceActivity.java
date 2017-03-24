package mobilend.sbcityconnect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CityAssistanceActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private ImageButton menuButton;
    private Spinner requestSpinner, issueSpinner;
    private LinearLayout requests;
    private int numRequests;
    private Context context;
    String user="";

    static final int REQUEST_IMAGE_CAPTURE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_assistance);
        context=this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        menuButton = (ImageButton) findViewById(R.id.menuButton);
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
        addItemsRequestSpinner();
        //set items in issue spinner depending on first selection

        requests=(LinearLayout) findViewById(R.id.requestLayout);
        numRequests=0;
    }

    public void addItemsRequestSpinner(){
        requestSpinner = (Spinner) findViewById(R.id.requestTypeSpinner);
        issueSpinner = (Spinner) findViewById(R.id.issueSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Garbage");
        list.add("Graffiti");
        list.add("Parks and Trees");
        list.add("Police and Fire");
        list.add("Road Damage");
        list.add("Streets and Sidewalks");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        requestSpinner.setAdapter(dataAdapter);

        requestSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item=(String) parent.getItemAtPosition(position);
                List<String> options=new ArrayList<>();
                switch(item){
                    case "Garbage":
                        options.add("Debris Pickup");
                        break;
                    case "Graffiti":
                        options.add("Graffiti");
                        break;
                    case "Parks and Trees":
                        options.add("Fallen tree");
                        break;
                    case "Police and Fire":
                        options.add("Request police assistance");
                        options.add("Request fire department");
                        break;
                    case "Road Damage":
                        options.add("Pothole");
                        options.add("Road flooding");
                        break;
                    case "Streets and Sidewalks":
                        options.add("Obstructed sidewalk");
                        break;
                    default:
                        break;
                }
                final ArrayAdapter<String> issueAdapter=new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,options);
                issueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                issueSpinner.setAdapter(issueAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void takePicture(View view){
        dispatchPictureIntent();
    }

    public void makeRequest(View view){
        RequestDisplay rd=new RequestDisplay(getApplicationContext());
        //inflate display into LinearLayout
        LinearLayout requestLayout=(LinearLayout) findViewById(R.id.requestLayout);
        switch(numRequests){
            case 0:
                requestLayout=(LinearLayout) findViewById(R.id.request1);
                break;
            case 1:
                requestLayout=(LinearLayout) findViewById(R.id.request2);
                break;
            case 2:
                requestLayout=(LinearLayout) findViewById(R.id.request3);
                break;
            case 3:
                requestLayout=(LinearLayout) findViewById(R.id.request4);
                break;
            case 4:
                requestLayout=(LinearLayout) findViewById(R.id.request5);
                break;
            case 5:
                requestLayout=(LinearLayout) findViewById(R.id.request6);
                break;
            case 6:
                requestLayout=(LinearLayout) findViewById(R.id.request7);
                break;
            case 7:
                requestLayout=(LinearLayout) findViewById(R.id.request8);
                break;
            default:
                break;
        }
        if(numRequests<8) {
            rd.inflateComponent(getApplicationContext(), requestLayout);
            String date = getDate();
            rd.setDateText(date);
            rd.setRequest(issueSpinner.getSelectedItem().toString());
            rd.setStatus(RequestDisplay.Status.SUBMITTED);
            numRequests++;
        }

    }

    public String getDate(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat d=new SimpleDateFormat("MM/dd", Locale.US);
        return d.format(cal.getTime());
    }

    private void dispatchPictureIntent(){
        Intent pictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(pictureIntent,REQUEST_IMAGE_CAPTURE);
        }else{
            Toast.makeText(this, "Error taking photo",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap imageBitmap=(Bitmap) extras.get("data");
            ImageView thumbnail=(ImageView) findViewById(R.id.imageThumbnail);
            thumbnail.setImageBitmap(imageBitmap);
        }
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.life:
                //TODO - implement submenu
                Toast.makeText(this, "Life", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.calendar:
                startActivity(new Intent(this, CalendarActivity.class));
                return true;
            case R.id.payments:
                //go to payments
                startActivity(new Intent(this, EPaymentActivity.class));
                return true;
            case R.id.moneyManagement:
                startActivity(new Intent(this, MoneyManagementHomeActivity.class));
                return true;
            case R.id.work:
                Toast.makeText(this, "Work", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.business:
                Toast.makeText(this, "Business", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.government:
                Toast.makeText(this, "Government", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                startActivity(new Intent(this, MainActivity.class));
                return true;

        }
        return false;
    }
}
