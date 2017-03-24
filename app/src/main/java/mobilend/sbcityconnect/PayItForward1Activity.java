package mobilend.sbcityconnect;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PayItForward1Activity extends AppCompatActivity implements android.widget.PopupMenu.OnMenuItemClickListener  {
    ImageButton menuButton;
    private Spinner addressSpinner, accountSpinner;
    String user = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payitforward1);

        NumberPicker np = (NumberPicker) findViewById(R.id.np);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        np.setMinValue(0);
        np.setMaxValue(2);
        np.setDisplayedValues( new String[] { "Neighbor", "Future Self", "Community" } );

        user = getIntent().getStringExtra("USERNAME");

        menuButton=(ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add these seven lines inside OnClickListener for menuButton in each activity
                LayoutInflater inflater=(LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View menuView=inflater.inflate(R.layout.dropdown_menu,null);
                final DropdownMenu menuWindow=new DropdownMenu(menuView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                menuWindow.setUser(user);

                menuWindow.setFocusable(true);
                menuWindow.update();
                menuWindow.showAsDropDown(menuButton);
            }
        });
        addItemsAddressSpinner();
        addItemsAccountSpinner();

        EditText edit =  (EditText) findViewById(R.id.editText);

        edit.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        final EditText edit =  (EditText) findViewById(R.id.editText);
                        String inputAmount = edit.getText().toString();
                        TextView tv2 = (TextView) findViewById(R.id.addAmount);
                        tv2.setText(inputAmount);

                    }
                });
    }
    public void addItemsAddressSpinner(){
        addressSpinner = (Spinner) findViewById(R.id.BillingAddressSpinner);
        List<String> list = new ArrayList<String>();
        if(user.equals("Robert")) {
            list.add("Billing Address");
            list.add("Home: Churchill Dr");
        }
        else {
            list.add("Billing Address");
            list.add("Address 1");
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
                if(selectedItem.equals("Home: Churchill Dr"))
                {
                    TextView tv1 = (TextView) findViewById(R.id.billingAddress);
                    tv1.setText("1625 Churchill Drive\nSB, IN 46617");
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
        if(user.equals("Robert")){
            list.add("Payment Account");
            list.add("Visa ending in 6789");
            list.add("Bank Account ending in 5432");
        }
        else {
            list.add("Payment Account");
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
                else if (selectedItem.equals("Bank Account ending in 5432")){
                    TextView tv3 = (TextView) findViewById(R.id.addAccount);
                    tv3.setText("Bank Account ending in 5432");
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
                android.widget.PopupMenu popup = new android.widget.PopupMenu(PayItForward1Activity.this, menuButton);
                popup.setOnMenuItemClickListener(PayItForward1Activity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                return true;
        }
        return false;
    }


    public void goToThankYou(View view){
        Intent intent = new Intent(this, PayItForwardLastActivity.class);
        intent.putExtra("USERNAME", user);
        startActivity(intent);
    }
}
