package mobilend.sbcityconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MakeAPayment1Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageButton menuButton;
    private Spinner paymentTypeSpinner, addressSpinner, accountSpinner;
    String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_apayment1);
        //Intent intent = getIntent();

        user = getIntent().getStringExtra("USERNAME");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        menuButton = (ImageButton) findViewById(R.id.menuButton);
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

        /*
        Spinner spinner = (Spinner)findViewById(R.id.PaymentTypeSpinner);
        String paymentType = spinner.getSelectedItem().toString();

        Spinner spinner2 = (Spinner)findViewById(R.id.BillingAddressSpinner);
        String billingAddress = spinner.getSelectedItem().toString();

        Spinner spinner3 = (Spinner)findViewById(R.id.PaymentAccountSpinner);
        String paymentAccount = spinner.getSelectedItem().toString();

        TextView tv1 = (TextView) findViewById(R.id.billingAddress);
        tv1.setText(paymentType);

        final EditText edit =  (EditText) findViewById(R.id.editText);
        edit.getText().toString();

        TextView tv2 = (TextView) findViewById(R.id.addAmount);
        tv1.setText("$" + edit);

        if (billingAddress.equals("Home: Diamond Ave")) {
            //TextView tv1 = (TextView) findViewById(R.id.billingAddress);
            //tv1.setText("735 Diamond Ave\nSB, IN 46628");

        }*/

    }

    public void addItemsPaymentTypeSpinner(){
        paymentTypeSpinner = (Spinner) findViewById(R.id.PaymentTypeSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Payment Type");
        list.add("Credit Card");
        list.add("Bank Account");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentTypeSpinner.setAdapter(dataAdapter);
    }
    public void addItemsAddressSpinner(){
        addressSpinner = (Spinner) findViewById(R.id.BillingAddressSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Billing Address");
        if (user.equals("Janice")){
            list.add("Home: Diamond Ave");
        }
        else {
            list.add("Address 1");
            list.add("Address 2");
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressSpinner.setAdapter(dataAdapter);
    }
    public void addItemsAccountSpinner(){
        accountSpinner = (Spinner) findViewById(R.id.PaymentAccountSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Payment Account");
        if (user.equals("Janice")){
            list.add("Visa ending in 6789");
            list.add("Bank Account ending in 4333");
        }
        else {
            list.add("Account 1");
            list.add("Account 2");
        }
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
