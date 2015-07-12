package demo.joez.com.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import demo.joez.com.demolist.R;
import demo.joez.com.widget.DragerAdapter;

/**
 * Created by dell on 2015/7/12.
 */
public class ViewDragerFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drager,container,false);
        ListView lvDrager = (ListView)rootView.findViewById(R.id.lv_drager);
        lvDrager.setAdapter(new DragerAdapter(getActivity()));
        return rootView;
    }

}
