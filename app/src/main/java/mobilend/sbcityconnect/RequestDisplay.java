package mobilend.sbcityconnect;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by bjtke_000 on 3/21/2017.
 */

public class RequestDisplay extends RelativeLayout {

    public enum Status{SUBMITTED,RECEIVED,INPROGRESS,COMPLETED};
    private Status status;
    private TextView dateText, requestText, statusText;

    public RequestDisplay(Context context){
        super(context);
        initComponent(context);
    }

    public RequestDisplay(Context context,AttributeSet attrs){
        super(context,attrs);
        initComponent(context);
    }

    public RequestDisplay(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        initComponent(context);
    }

    private void initComponent(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflater.inflate(R.layout.request_display,this);
        //this.addView(v);
    }

    public void inflateComponent(Context context,ViewGroup root){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.request_display,root);
        dateText=(TextView) v.findViewById(R.id.date);
        requestText=(TextView) v.findViewById(R.id.requestType);
        statusText=(TextView) v.findViewById(R.id.status);
    }

    public void setDateText(String date){
        //TextView dateText=(TextView) this.findViewById(R.id.date);
        dateText.setText(date);
    }

    public void setRequest(String req){
        //TextView requestText=(TextView) findViewById(R.id.requestType);
        requestText.setText(req);
    }

    public void setStatus(Status s){
        //TextView statusText=(TextView) findViewById(R.id.status);
        switch(s){
            case SUBMITTED:
                statusText.setText("SUBMITTED");
                statusText.setTextColor(Color.parseColor("#1fae92"));
                break;
            case RECEIVED:
                statusText.setText("RECEIVED");
                statusText.setTextColor(Color.parseColor("#1fae92"));
                break;
            case INPROGRESS:
                statusText.setText("IN PROGRESS");
                statusText.setTextColor(Color.parseColor("#1fae92"));
                break;
            case COMPLETED:
                statusText.setText("COMPLETED");
                statusText.setTextColor(Color.parseColor("#1fae92"));
                break;
        }
    }


}
