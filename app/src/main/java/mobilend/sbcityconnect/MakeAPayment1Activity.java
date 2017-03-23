package mobilend.sbcityconnect;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
    Boolean Credit = true;
    Boolean Bank = true;
    private int month;
    private int year;
    private int day;

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
        final EditText edit =  (EditText) findViewById(R.id.editText);

        edit.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String text = edit.getText().toString();
                        TextView tv2 = (TextView) findViewById(R.id.addAmount);
                        tv2.setText("$" + text);
                    }
                });

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

        paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Credit Card"))
                {
                    Credit = true;
                    Bank = false;
                }
                else if (selectedItem.equals("Bank Account")) {
                    Bank = true;
                    Credit = false;
                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
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

        addressSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Home: Diamond Ave"))
                {
                    TextView tv1 = (TextView) findViewById(R.id.billingAddress);
                    tv1.setText("735 Diamond Ave\nSB, IN 46628");
                }
                else {
                    TextView tv2 = (TextView) findViewById(R.id.billingAddress);
                    tv2.setText("");
                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
                TextView tv3 = (TextView) findViewById(R.id.billingAddress);
                tv3.setText("");
            }
        });
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

        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Visa ending in 6789"))
                {
                    TextView tv1 = (TextView) findViewById(R.id.addAccount);
                    tv1.setText("Visa ending in 6789");
                }
                else if (selectedItem.equals("Bank Account ending in 4333")){
                    TextView tv3 = (TextView) findViewById(R.id.addAccount);
                    tv3.setText("Bank Account ending in 4333");
                }
                else {
                    TextView tv2 = (TextView) findViewById(R.id.addAccount);
                    tv2.setText("");
                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
                TextView tv3 = (TextView) findViewById(R.id.billingAddress);
                tv3.setText("");
            }
        });
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
