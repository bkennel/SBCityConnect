package mobilend.sbcityconnect;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bjtke_000 on 3/22/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> headerList;
    private HashMap<String, List<String>> childList;

    public ExpandableListAdapter(Context context,List<String> headers,HashMap<String,List<String>> childData){
        this.context=context;
        this.headerList=headers;
        this.childList=childData;
    }

    @Override
    public Object getChild(int groupPosition,int childPosition){
        return this.childList.get(this.headerList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition,int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int groupPos, final int childPos, boolean isLastChild, View convertView, ViewGroup parent){
        final String childText=(String) getChild(groupPos,childPos);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_item,null);
        }
        TextView childLabel=(TextView) convertView.findViewById(R.id.itemLabel);
        childLabel.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPos){
        if(this.childList.get(this.headerList.get(groupPos))!=null)
            return this.childList.get(this.headerList.get(groupPos)).size();
        else
            return 0;
    }

    @Override
    public Object getGroup(int groupPos){
        return this.headerList.get(groupPos);
    }

    @Override
    public int getGroupCount(){
        return this.headerList.size();
    }

    @Override
    public long getGroupId(int groupPos){
        return groupPos;
    }

    @Override
    public View getGroupView(int groupPos,boolean isExpanded,View convertView,ViewGroup parent){
        String headerTitle=(String) getGroup(groupPos);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_group,null);
        }
        TextView headerLabel=(TextView) convertView.findViewById(R.id.headerLabel);
        headerLabel.setTypeface(null, Typeface.BOLD);
        headerLabel.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPos,int childPos){
        return true;
    }

}
