package mobilend.sbcityconnect;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by bjtke_000 on 3/7/2017.
 */

public class CalendarEvent {
    private String title;
    private String location;
    private GregorianCalendar date;

    public CalendarEvent(){
        title="Event title";
        location="Event location";
        date=new GregorianCalendar();
    }

    public String getTitle(){return this.title;}
    public String getLocation(){return this.location;}
    public Date getTime(){return this.date.getTime();}

    public void setTitle(String title){
        this.title=title;
    }
    public void setLocation(String loc){
        this.location=loc;
    }
    public void setDate(int y,int m,int d){
        this.date.set(y,m,d);
    }
}
