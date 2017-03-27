package mobilend.sbcityconnect;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BudgetBuilderActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton menuButton;
    private Spinner categorySpinner, subCategorySpinner;
    String user = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_builder);

        user = getIntent().getStringExtra("USERNAME");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        menuButton = (ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new OnClickListener() {
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
        addItemsCategorySpinner();
        addItemsSubCategorySpinner();
    }

    public void addItemsCategorySpinner(){
        categorySpinner = (Spinner) findViewById(R.id.CategorySpinner);
        List<String> list = new ArrayList<String>();
        list.add("Education");
        list.add("Food");
        list.add("Rent");
        list.add("Utilities");
        list.add("Leisure");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(dataAdapter);
    }


    public void addItemsSubCategorySpinner(){
        subCategorySpinner = (Spinner) findViewById(R.id.topSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Education");
        list.add("Food");
        list.add("Rent");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subCategorySpinner.setAdapter(dataAdapter);
    }

    public boolean onMenuItemClick(MenuItem item) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuIcon:
                PopupMenu popup = new PopupMenu(BudgetBuilderActivity.this, menuButton);
                popup.setOnMenuItemClickListener(BudgetBuilderActivity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                return true;
        }
        return false;
    }
    public void goToDeleteTEMP(View view){
        Intent intent = new Intent(this, DeleteBudgetActivity.class);
        startActivity(intent);
    }
}