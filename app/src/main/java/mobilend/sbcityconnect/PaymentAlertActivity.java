package mobilend.sbcityconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PaymentAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_alert);
    }
    public void goToSummary(View view){
        Intent intent = new Intent(this, AlertSummaryActivity.class);
        startActivity(intent);
    }
}
