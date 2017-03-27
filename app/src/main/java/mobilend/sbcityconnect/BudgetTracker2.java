package mobilend.sbcityconnect;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import static mobilend.sbcityconnect.R.id.progressBar;

public class BudgetTracker2 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton menuButton;
    String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker2);

        user = getIntent().getStringExtra("USERNAME");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        menuButton = (ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add these seven lines inside OnClickListener for menuButton in each activity
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View menuView = inflater.inflate(R.layout.dropdown_menu, null);
                final DropdownMenu menuWindow = new DropdownMenu(menuView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                menuWindow.setUser(user);

                menuWindow.setFocusable(true);
                menuWindow.update();
                menuWindow.showAsDropDown(menuButton);
            }
        });


        ProgressBar progressBar = (ProgressBar) findViewById(R.id.newProgress1);

        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 90);
        animation.setDuration(3500); // 3.5 second
        animation.setInterpolator(new DecelerateInterpolator());

        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.newProgress2);

        ObjectAnimator animation2 = ObjectAnimator.ofInt(progressBar2, "progress", 0, 33);
        animation2.setDuration(3500); // 3.5 second
        animation2.setInterpolator(new DecelerateInterpolator());

        animation.start();
        animation2.start();
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }*/

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuIcon:
                PopupMenu popup = new PopupMenu(BudgetTracker2.this, menuButton);
                popup.setOnMenuItemClickListener(BudgetTracker2.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                return true;
        }
        return false;
    }
    public void minusButtonPressed(View view){
        String pressed = "true";
        Intent intent = new Intent(getApplicationContext(), BudgetTrackerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("USERNAME", user);
        intent.putExtra("PRESSED", pressed);

        startActivity(intent);
        overridePendingTransition (0, 0);
    }
}