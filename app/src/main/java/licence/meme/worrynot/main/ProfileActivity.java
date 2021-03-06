package licence.meme.worrynot.main;

import android.net.Uri;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import licence.meme.worrynot.R;
import licence.meme.worrynot.gui.screen.home.HomeFragment;
import licence.meme.worrynot.gui.screen.worrynotstore.MethodsStoreFragment;
import licence.meme.worrynot.gui.screen.usersworrynots.UserMethodsFragment;
import licence.meme.worrynot.gui.screen.worrynotcreator.CreateWorryNotFragment;

public class ProfileActivity extends AppCompatActivity implements
        HomeFragment.OnUserNameListener,
        MethodsStoreFragment.OnFragmentInteractionListener,
        UserMethodsFragment.OnFragmentInteractionListener,
        CreateWorryNotFragment.OnFragmentInteractionListener
{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private String userNameFromFragment;
    private static final String USER_NAME = "USER_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void receiveName(String name){
        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        if(mSectionsStatePagerAdapter != null){
            CreateWorryNotFragment createWorryNotFragment = (CreateWorryNotFragment) mSectionsStatePagerAdapter.getItem(1);
            if(createWorryNotFragment != null){
                Log.e("RECEIVE", "NAME is " + name);
                createWorryNotFragment.updateName(name);
            }
            else {
                Log.e("RECEIVE NAME",",CreateWorryNotFragment");
            }
        }else {
            Log.e("RECEIVE NAME",",Section Pager Adapeter is null");
        }
        if(mSectionsStatePagerAdapter != null){
            MethodsStoreFragment methodsStoreFragment = (MethodsStoreFragment) mSectionsStatePagerAdapter.getItem(2);
            if(methodsStoreFragment != null){
                Log.e("SET", "NAME is " + name);
                methodsStoreFragment.setUserName(name);
            }
            else {
                Log.e("SET NAME",",MethodsStoreFragment");
            }
        }else {
            Log.e("SET NAME",",Section Pager Adapeter is null");
        }
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

        public PlaceholderFragment() {
        }

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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    Fragment homeFragment =   HomeFragment.newInstance();
                    return homeFragment;
                case 1:
                    Fragment createMethodFragment = new CreateWorryNotFragment();
                    return createMethodFragment;
                case 2:
                    Fragment methodStoreFragment = new MethodsStoreFragment();
                    return methodStoreFragment;
                case 3:
                    Fragment userMethodsContainer = new UserMethodsFragment();
                    return userMethodsContainer;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
    public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {


        public SectionsStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    Fragment homeFragment = HomeFragment.newInstance();
                    return homeFragment;
                case 1:
                    Fragment methodsContainer = new CreateWorryNotFragment();
                    return methodsContainer;
                case 2:
                    Fragment methodStoreFragment = new MethodsStoreFragment();
                    return methodStoreFragment;
                case 3:
                    Fragment methodManagerFragment = new UserMethodsFragment();
                    return methodManagerFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
}
