package demo.joez.com.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.joez.com.demolist.R;
import demo.joez.com.item.BaseFragment;
import demo.joez.com.widget.PathView;

/**
 * Created by dell on 2015/9/8.
 */
public class PathViewFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_path,container,false);
        PathView pathView = (PathView) rootView.findViewById(R.id.pathview);
        pathView.init();
        return rootView;
    }
}
