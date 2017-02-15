package mobilend.sbcityconnect;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class LifeActivity extends Activity implements OnItemClickListener {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.life_page_list);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

        if (position == 4) {
            Intent myIntent = new Intent(this, MoneyManagement.class);
            startActivity(myIntent);
        }
    }
}
