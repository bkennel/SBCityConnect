package mobilend.sbcityconnect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CityAssistanceActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private ImageButton menuButton;
    private Spinner requestSpinner, issueSpinner;

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
                PopupMenu popup = new PopupMenu(CityAssistanceActivity.this, menuButton);
                popup.setOnMenuItemClickListener(CityAssistanceActivity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
            }
        });
        addItemsRequestSpinner();
        //set items in issue spinner depending on first selection
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
        //Make request
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
