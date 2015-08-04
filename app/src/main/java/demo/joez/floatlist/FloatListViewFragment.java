package demo.joez.floatlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.joez.com.demolist.R;
import demo.joez.com.item.BaseFragment;

/**
 * Created by joez on 2015/8/4.
 */
public class FloatListViewFragment extends BaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_floatlist,container,false);
        return rootView;
    }
}
