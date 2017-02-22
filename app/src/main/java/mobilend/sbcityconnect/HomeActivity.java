package mobilend.sbcityconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuButton=(ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(HomeActivity.this, menuButton);
                popup.setOnMenuItemClickListener(HomeActivity.this);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
            }
        });

    }

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

        }
        return false;
    }

    public void goToCalendar(){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void goToCalendar(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

}
