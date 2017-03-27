package mobilend.sbcityconnect;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class BudgetTrackerActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton menuButton;
    String user = "";
    String pressed = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker);

        Intent intent = getIntent();
        user= intent.getStringExtra("USERNAME");
        pressed = intent.getStringExtra("PRESSED");
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

        if(pressed.equals("false")) {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress1);

            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 88);
            animation.setDuration(3500); // 3.5 second
            animation.setInterpolator(new DecelerateInterpolator());

            ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progress2);

            ObjectAnimator animation2 = ObjectAnimator.ofInt(progressBar2, "progress", 0, 40);
            animation2.setDuration(3500); // 3.5 second
            animation2.setInterpolator(new DecelerateInterpolator());

            ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progress3);

            ObjectAnimator animation3 = ObjectAnimator.ofInt(progressBar3, "progress", 0, 100);
            animation3.setDuration(3500); // 3.5 second
            animation3.setInterpolator(new DecelerateInterpolator());

            ProgressBar progressBar4 = (ProgressBar) findViewById(R.id.progress4);

            ObjectAnimator animation4 = ObjectAnimator.ofInt(progressBar4, "progress", 0, 100);
            animation4.setDuration(3500); // 3.5 second
            animation4.setInterpolator(new DecelerateInterpolator());

            ProgressBar progressBar5 = (ProgressBar) findViewById(R.id.progress5);

            ObjectAnimator animation5 = ObjectAnimator.ofInt(progressBar5, "progress", 0, 80);
            animation5.setDuration(3500); // 3.5 second
            animation5.setInterpolator(new DecelerateInterpolator());

            ProgressBar progressBar6 = (ProgressBar) findViewById(R.id.progressBar);

            ObjectAnimator animation6 = ObjectAnimator.ofInt(progressBar6, "progress", 0, 75);
            animation6.setDuration(3500); // 3.5 second
            animation6.setInterpolator(new DecelerateInterpolator());

            animation.start();
            animation2.start();
            animation3.start();
            animation4.start();
            animation5.start();
            animation6.start();
        }
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
                PopupMenu popup = new PopupMenu(BudgetTrackerActivity.this, menuButton);
                popup.setOnMenuItemClickListener(BudgetTrackerActivity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                return true;
        }
        return false;
    }
    public void plusButtonPressed(View view){
        Intent intent = new Intent(getApplicationContext(), BudgetTracker2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("USERNAME", user);

        startActivity(intent);
        overridePendingTransition (0, 0);
    }
}