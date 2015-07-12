package demo.joez.com.demolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import demo.joez.com.item.BaseFragment;
import demo.joez.com.item.TextViewFragment;
import demo.joez.com.item.TransitionsFragment;
import demo.joez.com.item.ViewDragerFragment;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,FragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        navigateToTextViewFragment();
//        navigateToTransitionsFragment();
        navigateToDragerListFragment();
    }


    @Override
    public void onClick(View v) {

    }

    public void addFragment(BaseFragment fragment){
        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).commit();
    }

    @Override
    public void navigateToTextViewFragment() {
       addFragment(new TextViewFragment());
    }

    @Override
    public void navigateToTransitionsFragment() {
        addFragment(new TransitionsFragment());
    }

    @Override
    public void navigateToDragerListFragment() {
        addFragment(new ViewDragerFragment());
    }
}
