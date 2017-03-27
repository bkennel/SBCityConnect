package mobilend.sbcityconnect;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    Boolean warning = false;
    Boolean Bank = true;
    private int month;
    private int year;
    private int day;
    private String inputAmount = "";

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
                LayoutInflater inflater=(LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View menuView=inflater.inflate(R.layout.dropdown_menu,null);
                final DropdownMenu menuWindow=new DropdownMenu(menuView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                menuWindow.setUser(user);

                menuWindow.setOutsideTouchable(true);
                menuWindow.setFocusable(true);
                menuWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                menuWindow.update();
                menuWindow.showAsDropDown(menuButton,-50,0);
            }
        });
        addItemsPaymentTypeSpinner();
        addItemsAddressSpinner();
        addItemsAccountSpinner();

        EditText edit =  (EditText) findViewById(R.id.editText);

        edit.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        final EditText edit =  (EditText) findViewById(R.id.editText);
                        inputAmount = edit.getText().toString();
                        TextView tv2 = (TextView) findViewById(R.id.addAmount);
                        tv2.setText(inputAmount);

                    }
                });

    }

    public void addItemsPaymentTypeSpinner(){
        paymentTypeSpinner = (Spinner) findViewById(R.id.PaymentTypeSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Payment Type");
        list.add("City Utilities");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentTypeSpinner.setAdapter(dataAdapter);

        paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("City Utilities"))
                {
                    EditText myTextBox = (EditText) findViewById(R.id.editText);
                    myTextBox.setText("$75.00");
                    TextView amount = (TextView) findViewById(R.id.addAmount);
                    amount.setText("$75.00");
                }
                else{
                    EditText myTextBox = (EditText) findViewById(R.id.editText);
                    myTextBox.setText("$");

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
        if(inputAmount.equals("$80.00")){
            Intent intent = new Intent(this, PaymentAlertActivity.class);
            intent.putExtra("USERNAME", user);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, MakeAPaymentLastActivity.class);
            intent.putExtra("USERNAME", user);
               startActivity(intent);
        }
    }
}
