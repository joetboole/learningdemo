package demo.joez.com.design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.joez.com.demolist.R;
import demo.joez.com.item.BaseFragment;

/**
 * Created by dell on 2015/7/19.
 */
public class DesignSuportFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_design,container,false);
        return rootView;
    }
}
