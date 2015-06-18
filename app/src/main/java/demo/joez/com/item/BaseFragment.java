package demo.joez.com.item;

import android.app.Activity;
import android.support.v4.app.Fragment;

import demo.joez.com.demolist.FragmentInteractionListener;

/**
 * Created by JoeZ on 2015/6/18.
 */
public class BaseFragment extends Fragment{
    protected FragmentInteractionListener mInteractionListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof  FragmentInteractionListener){
            mInteractionListener=(FragmentInteractionListener)activity;
        }
    }
}
