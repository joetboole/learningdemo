package demo.joez.com.item;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.joez.com.demolist.R;

/**
 * Created by JoeZ on 2015/7/2.
 */
public class TransitionsFragment extends  BaseFragment {
    private Scene mSceneFirst,mSceneSecond;
    private boolean mIsSecond;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transitions,container,false);
        TextView tvControll = (TextView)rootView.findViewById(R.id.title);
        tvControll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition fadeTransition = new Fade();
                TransitionSet set = new TransitionSet().addTransition(new AutoTransition());
                TransitionManager.go(mIsSecond?mSceneFirst:mSceneSecond,set);
                mIsSecond=!mIsSecond;
            }
        });

        ViewGroup scene_root = (ViewGroup)rootView.findViewById(R.id.scene_root);
        mSceneFirst = Scene.getSceneForLayout(scene_root,R.layout.scene1,getActivity());
        mSceneSecond = Scene.getSceneForLayout(scene_root,R.layout.scene2,getActivity());
        return rootView;
    }
}
