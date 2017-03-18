package mobilend.sbcityconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MakeAPayment1Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageButton menuButton;
    private Spinner paymentTypeSpinner, addressSpinner, accountSpinner;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_make_apayment1);
            //Intent intent = getIntent();

            Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);

            menuButton=(ImageButton) findViewById(R.id.menuButton);
            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(MakeAPayment1Activity.this, menuButton);
                    popup.setOnMenuItemClickListener(MakeAPayment1Activity.this);
                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                    popup.show();
                }
            });
        addItemsPaymentTypeSpinner();
        addItemsAddressSpinner();
        addItemsAccountSpinner();
    }
    public void addItemsPaymentTypeSpinner(){
        paymentTypeSpinner = (Spinner) findViewById(R.id.PaymentTypeSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Payment Type");
        list.add("Credit Card");
        list.add("Paypal");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentTypeSpinner.setAdapter(dataAdapter);
    }
    public void addItemsAddressSpinner(){
        addressSpinner = (Spinner) findViewById(R.id.BillingAddressSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Billing Address");
        list.add("Address 1");
        list.add("Address 2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressSpinner.setAdapter(dataAdapter);
    }
    public void addItemsAccountSpinner(){
        accountSpinner = (Spinner) findViewById(R.id.PaymentAccountSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Payment Account");
        list.add("Account 1");
        list.add("Account 2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(dataAdapter);
    }
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }*/

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
            case R.id.moneyManagement:
                startActivity(new Intent(this, MoneyManagementHomeActivity.class));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuIcon:
                PopupMenu popup = new PopupMenu(MakeAPayment1Activity.this, menuButton);
                popup.setOnMenuItemClickListener(MakeAPayment1Activity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                return true;
        }
        return false;
    }
    public void goToThankYou(View view){
        Intent intent = new Intent(this, MakeAPaymentLastActivity.class);
        startActivity(intent);
    }
}
