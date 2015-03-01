package com.techspardha.siddhant.techspardha;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    Context context;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d("MyApp", "LELELEEL");
        System.out.println("hellooooooooooooooo");
        try {
            context = getApplicationContext();
            findViewById(R.id.image_announce).setOnTouchListener(new MyTouchListener());
            findViewById(R.id.image_events).setOnTouchListener(new MyTouchListener());
            findViewById(R.id.image_exhibition).setOnTouchListener(new MyTouchListener());
            findViewById(R.id.image_india).setOnTouchListener(new MyTouchListener());
            findViewById(R.id.image_info).setOnTouchListener(new MyTouchListener());
            findViewById(R.id.image_navi).setOnTouchListener(new MyTouchListener());

           findViewById(R.id.image_logo).setOnDragListener(new MyDragListener());
            System.out.println("THIS IS DONE!!");
        }
        catch(Exception e)
        {
            System.out.println("THIS IS not DONE!!");
            System.out.println(e);
        }
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment= PlaceholderFragment.newInstance(position + 1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position)
        {

            case 1 : System.out.println("QUIZ FRAGMENT!");
                     fragment = new QuizzesFragment();
                     break;

        }
        //fragmentManager.beginTransaction()
        //        .replace(R.id.container,PlaceholderFragment.newInstance(position + 1))
        //        .commit();
        fragmentManager.beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        String s[] = getResources().getStringArray(R.array.drawer_contents);
        mTitle = s[number-1];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
    private class MyTouchListener implements View.OnTouchListener {


        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
            {
                try {


                    ClipData data = ClipData.newPlainText("Hio", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                }
                catch(Exception e)
                {



                    System.out.println("Touch Listener "+ e);
                }
                //setTitle(();
                // v.setVisibility(View.INVISIBLE);
                return true;
            }
            return false;
        }
    }
    private class MyDragListener implements View.OnDragListener {

        public  boolean onDrag(View v, DragEvent event )
        {
            int array[] = new int[2];
            int action = event.getAction();
            View dragView = (View) event.getLocalState();
            try {


                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        v.getLocationOnScreen(array);
                        System.out.println(array[0]);
                        System.out.println(array[1]);
                        System.out.println("DRAG started!!!");
                        //v.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        //      v.setBackgroundDrawable(enterShape);
                        System.out.println("DRAG Entered!!!");
                        //v.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        //    v.setBackgroundDrawable(normalShape);
                        System.out.println("DRAG EXIT!!!");
                        //v.invalidate();
                        return true;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        System.out.println("Drag Dropped!");
                        try {
                            Intent i = new Intent(MainActivity.this  , EventCategoryActivity.class);
                            System.out.println("Inside Intent!");
                            startActivity(i);
                            //Toast.makeText(context, "Got You!", Toast.LENGTH_LONG).show();
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        return true;
                   /*View view = (View) event.getLocalState();
                   ViewGroup owner = (ViewGroup) view.getParent();
                   owner.removeView(view);
                   LinearLayout container = (LinearLayout) v;
                   container.addView(view);
                   view.setVisibility(View.VISIBLE);
                    v.invalidate();
                    return true;
                    */
                    case DragEvent.ACTION_DRAG_ENDED:
                        //  v.setBackgroundDrawable(normalShape);
                        if (event.getResult() == false) {
                            try {
                                //  dragView.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }


                        System.out.println("DRAG Ended!!!");
                        //v.invalidate();
                        return true;
                    default:
                        break;
                }
            }
            catch(Exception e)
            {
                System.out.println("Drag" + e);
            }
            return false;
        }

    }


}
