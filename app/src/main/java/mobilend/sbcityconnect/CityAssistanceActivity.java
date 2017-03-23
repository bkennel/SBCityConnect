package mobilend.sbcityconnect;

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

public class CityAssistanceActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private ImageButton menuButton;
    private Spinner requestSpinner, issueSpinner;
    private LinearLayout requests;
    private int numRequests;
    String user="";

    static final int REQUEST_IMAGE_CAPTURE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_assistance);

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
                menuWindow.showAsDropDown(menuButton);
            }
        });
        addItemsRequestSpinner();
        //set items in issue spinner depending on first selection

        requests=(LinearLayout) findViewById(R.id.requestLayout);
        numRequests=0;
    }

    public void addItemsRequestSpinner(){
        requestSpinner = (Spinner) findViewById(R.id.requestTypeSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Garbage");
        list.add("Graffiti");
        list.add("Parks and Trees");
        list.add("Police and Fire");
        list.add("Road Damage");
        list.add("Streets and Sidewalks");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        requestSpinner.setAdapter(dataAdapter);
    }

    public void takePicture(View view){
        dispatchPictureIntent();
    }

    public void makeRequest(View view){
        RequestDisplay rd=new RequestDisplay(getApplicationContext());
        //inflate display into linearlayout
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
            default:
                break;
        }
        rd.inflateComponent(getApplicationContext(),requestLayout);

        //rd.inflateComponent(getApplicationContext(),(RelativeLayout)findViewById(R.id.activity_city_assistance));
        //requests.addView(rd);
        //still identifies first requestLayout after second is added
        String date=getDate();
        rd.setDateText(date);
        rd.setRequest(requestSpinner.getSelectedItem().toString());
        rd.setStatus(RequestDisplay.Status.SUBMITTED);
        numRequests++;

    }

    public String getDate(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat d=new SimpleDateFormat("MM/dd");
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
