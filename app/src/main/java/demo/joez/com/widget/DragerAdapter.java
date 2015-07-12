package demo.joez.com.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.joez.com.demolist.R;

/**
 * Created by dell on 2015/7/12.
 */
public class DragerAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    public DragerAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.calendar_swipeitem,null);
            holder = new ViewHolder();
            holder.tvContent = (TextView)convertView.findViewById(R.id.tv_front_content);
            convertView.setTag(holder);
        }
        else{
            holder =(ViewHolder)convertView.getTag();
        }

        holder.tvContent.setText("aaa");
        return convertView;
    }
    class ViewHolder{
        TextView tvContent;
    }
}
