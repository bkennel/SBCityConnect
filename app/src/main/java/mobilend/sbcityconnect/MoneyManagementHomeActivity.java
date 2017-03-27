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
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MoneyManagementHomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton menuButton;
    String user = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_management_home);

        user = getIntent().getStringExtra("USERNAME");

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        menuButton=(ImageButton) findViewById(R.id.menuButton);
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
                PopupMenu popup = new PopupMenu(MoneyManagementHomeActivity.this, menuButton);
                popup.setOnMenuItemClickListener(MoneyManagementHomeActivity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                return true;
        }
        return false;
    }
    public void goToBudgetBuilder(View view){
        Intent intent = new Intent(this, BudgetBuilderActivity.class);
        intent.putExtra("USERNAME", user);
        startActivity(intent);
    }

    public void goToMonthlySpending(View view){
        Intent intent = new Intent(this, MonthlySpending1Activity.class);
        intent.putExtra("USERNAME", user);
        startActivity(intent);
    }
    public void goToBudgetTracker(View view){
        Intent intent = new Intent(this, BudgetTrackerActivity.class);
        intent.putExtra("USERNAME", user);
        intent.putExtra("PRESSED", "false");
        startActivity(intent);
    }
}
