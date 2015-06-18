package demo.joez.com.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import demo.joez.com.demolist.R;

/**
 * Created by JoeZ on 2015/6/18.
 */
public class TextViewFragment extends BaseFragment {

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_textview,container,false);
        return rootView;
    }
}
