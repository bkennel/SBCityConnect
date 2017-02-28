package mobilend.sbcityconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epayment);

        Intent intent = getIntent();
    }
    public void goToPayItForward1(View view){
        Intent intent = new Intent(this, PayItForward1Activity.class);
        startActivity(intent);

    }
    public void goToMakeAPayment1(View view){
        Intent intent = new Intent(this, MakeAPayment1Activity.class);
        startActivity(intent);

    }
}
