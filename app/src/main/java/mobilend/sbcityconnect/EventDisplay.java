package mobilend.sbcityconnect;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by bjtke_000 on 3/8/2017.
 */

public class EventDisplay extends RelativeLayout {

    public EventDisplay(Context context){
        super(context);
        initComponent(context);
    }

    public EventDisplay(Context context,AttributeSet attrs){
        super(context,attrs);
        initComponent(context);
    }

    public EventDisplay(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        initComponent(context);
    }

    private void initComponent(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.event_display,this);
        //this.addView(v);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
    }

    public void setTitle(String title){
        TextView titleText=(TextView) findViewById(R.id.title);
        titleText.setText(title);
    }

    public void setTime(String time){
        TextView timeText=(TextView) findViewById(R.id.time);
        //update text
    }

    public void setLocation(String loc){
        TextView locText=(TextView) findViewById(R.id.location);
        locText.setText(loc);
    }

}
