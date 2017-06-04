package licence.meme.worrynot.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.greenrobot.eventbus.EventBus;

import de.hdodenhof.circleimageview.CircleImageView;
import licence.meme.worrynot.R;
import licence.meme.worrynot.activities.MainActivity;
import licence.meme.worrynot.activities.QuestionnaireActivity;
import licence.meme.worrynot.activities.ResetPasswordActivity;
import licence.meme.worrynot.models.FirebaseService;
import licence.meme.worrynot.models.Method;
import licence.meme.worrynot.models.User;
import licence.meme.worrynot.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = HomeFragment.class.getSimpleName() ;
    private static final String TAG_FRAGMENT = "HOME_FRAGMENT" ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button mMenuHomeButton;
    private Button mInfoButton;
    private Button mChangePasswordButton;
    private Button mLogoutButton;


    private CircleImageView mAvatarImageView;
    private ProgressBar mExperienceProgressBar;
    private TextView mUserNameTextView;


    private Button mEvaluateButton;
    private Method mReceivedMethod;
    private EventBus bus;


    private View mFragmentView;
    private LayoutInflater mInflater;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"On Create\n");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG,"On Create View\n");

        mInflater=inflater;
        mFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        mMenuHomeButton = (Button)mFragmentView.findViewById(R.id.menu_home_fragment_btn);
        mMenuHomeButton.setOnClickListener(this);
        mInfoButton = (Button)mFragmentView.findViewById(R.id.info_fragment_btn);
        mChangePasswordButton = (Button)mFragmentView.findViewById(R.id.change_password_fragment_btn);
        mLogoutButton = (Button)mFragmentView.findViewById(R.id.logout_fragment_btn);
        mEvaluateButton = (Button)mFragmentView.findViewById(R.id.evaluate_home_fragment_btn);
        mInfoButton.setVisibility(View.GONE);
        mChangePasswordButton.setVisibility(View.GONE);
        mLogoutButton.setVisibility(View.GONE);
        mLogoutButton.setOnClickListener(this);
        mInfoButton.setOnClickListener(this);
        mEvaluateButton.setOnClickListener(this);
        mAvatarImageView = (CircleImageView) mFragmentView.findViewById(R.id.avatar_home_fragment_iv);
        mUserNameTextView = (TextView)mFragmentView.findViewById(R.id.name_home_fragment_tv);
        mExperienceProgressBar = (ProgressBar)mFragmentView.findViewById(R.id.experience_bar_home_fragment_pb);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/"+uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mAvatarImageView.setImageBitmap(Utils.decodeFromFirebaseBase64(user.getImage()));
                mUserNameTextView.setText(user.getUsername());
                mExperienceProgressBar.setProgress(user.getExperience());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"Fail database");
            }
        });

            FirebaseService firebaseService = FirebaseService.getInstance();
            firebaseService.getActiveMethod(mFragmentView.getContext(),inflater,mFragmentView);


        return mFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG,"On Attach\n");
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.e(TAG,"On Detach\n");
        super.onDetach();
        mListener = null;
    }
    public void toggleView(View view){
        if(view.getVisibility()==View.GONE)
            view.setVisibility(View.VISIBLE);
        else if(view.getVisibility()==View.VISIBLE)
            view.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.evaluate_home_fragment_btn:
                startActivity(new Intent(getContext(), QuestionnaireActivity.class));
                getActivity().finish();
                break;
            case R.id.menu_home_fragment_btn:
                toggleView(mInfoButton);
                toggleView(mLogoutButton);
                toggleView(mChangePasswordButton);
                break;
            case R.id.logout_fragment_btn:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
                break;
            case R.id.info_fragment_btn:
                FirebaseService firebaseService = FirebaseService.getInstance();
                firebaseService.getActiveMethod(mFragmentView.getContext(),mInflater,mFragmentView);
                break;
            case R.id.change_password_fragment_btn:
                startActivity(new Intent(getContext(), ResetPasswordActivity.class));
                getActivity().finish();
                break;
            default:
                break;
        }
    }
    public String getTagFragment(){
        return TAG_FRAGMENT;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
