package licence.meme.worrynot.licence.meme.worrynot.licence.meme.worrynot.licence.meme.worrynot.fragments.obsolated;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import licence.meme.worrynot.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfile extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView content1TextView;
    private TextView title1TextView;

    private Animation collapseAnimation;
    private Animation expandAnimation;


    private float experience;
    private int level;
    private Button submitButton;
    private RatingBar method1RatingBar;
    private RatingBar method2RatingBar;
    private ProgressBar experienceProgressBar;
    private EditText levelEditText;

    private DatabaseReference ref;

    public MyProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfile newInstance(String param1, String param2) {
        MyProfile fragment = new MyProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_profile, container, false);

        content1TextView = (TextView)view.findViewById(R.id.content_text_1);
        title1TextView = (TextView)view.findViewById(R.id.title_text_1);
        content1TextView.setVisibility(View.GONE);


        expandAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.expand);
        collapseAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.collapse);

        levelEditText = (EditText)view.findViewById(R.id.level_my_profile_et);
        submitButton = (Button)view.findViewById(R.id.submit_my_profile_btn);
        method1RatingBar = (RatingBar)view.findViewById(R.id.ratingMethod1_my_profile_rb);
        method2RatingBar = (RatingBar)view.findViewById(R.id.ratingMethod2_my_profile_rb);
        experienceProgressBar = (ProgressBar)view.findViewById(R.id.experience_my_profile_pb);


//        ref = FirebaseDatabase.getInstance().getReference().child("users").child("uid1000");
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                float exp = dataSnapshot.child("experience").getValue(Float.class);
//                int lvl = dataSnapshot.child("level").getValue(Integer.class);
//                experience = exp;
//                level = lvl;
//                experienceProgressBar.setProgress((int)exp%100);
//                levelEditText.setText("Current level :" + lvl);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



        addListenerOnSubmitButton();
        addListenerOnRatingBars();


        title1TextView.setOnClickListener(this);
        return view;
    }

    private void addListenerOnSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experienceProgressBar.setProgress((int) experience);
                ref = FirebaseDatabase.getInstance().getReference().child("users").child("uid1000");;
                ref.child("experience").setValue(experience);
                ref.child("level").setValue(level);

            }
        });
    }
    private void addListenerOnRatingBars(){
        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        method1RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                experience+=Float.valueOf(rating);
                level = Math.abs((int)(experience/100));

            }
        });
        method2RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                experience+=Float.valueOf(rating);
                level = Math.abs((int)(experience/100));
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
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
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(content1TextView.isShown()){
            content1TextView.setVisibility(View.GONE);
            content1TextView.startAnimation(collapseAnimation);
        }
        else{
            content1TextView.setVisibility(View.VISIBLE);
            content1TextView.startAnimation(expandAnimation);
            ref = FirebaseDatabase.getInstance().getReference().child("methods").child("mid1000").child("details").child("steps");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("ERROR","Internal :D");
                    String userName = dataSnapshot.getValue().toString();
                    content1TextView.setText("Name:"+userName);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
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
