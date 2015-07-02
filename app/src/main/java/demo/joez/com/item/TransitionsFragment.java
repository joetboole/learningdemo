package demo.joez.com.item;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
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
    private Scene mSceneFirst,mSceneSecond,mSceneThird;
    private boolean mIsSecond;
    private int mFlag=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transitions,container,false);
        TextView tvControll = (TextView)rootView.findViewById(R.id.title);
        tvControll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlag++;
                Scene scene=null;
                if(mFlag == 3){
                    scene = mSceneThird;
                }else if(mFlag == 2){
                    scene = mSceneSecond;
                }else{
                    mFlag = 1;
                    scene = mSceneFirst;
                }
//                Transition fadeTransition = new Fade();
//                Transition fadeTransition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.fade_transition);
                TransitionSet set = new TransitionSet().addTransition(new Fade());
//                TransitionSet set = (TransitionSet) TransitionInflater.from(getActivity()).inflateTransition(R.transition.set_transition);
                TransitionManager.go(scene,set);
                TransitionManager m=null;;
                mIsSecond=!mIsSecond;
            }
        });

        ViewGroup scene_root = (ViewGroup)rootView.findViewById(R.id.scene_root);
        mSceneFirst = Scene.getSceneForLayout(scene_root,R.layout.scene1,getActivity());
        mSceneSecond = Scene.getSceneForLayout(scene_root,R.layout.scene2,getActivity());
        mSceneThird = new Scene(scene_root,inflater.inflate(R.layout.scene3,null));
        return rootView;
    }
}
