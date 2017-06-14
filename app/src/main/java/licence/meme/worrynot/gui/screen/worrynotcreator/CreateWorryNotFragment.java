package licence.meme.worrynot.gui.screen.worrynotcreator;

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

import licence.meme.worrynot.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateWorryNotFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateWorryNotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateWorryNotFragment extends Fragment implements View.OnClickListener {
    private static final String USER_NAME = "USER_NAME";
    private static final String BUNDLE = "CREATE_BUNDLE";
    private static final String TAG = CreateWorryNotFragment.class.getSimpleName();
    private static String mUserName;
    private OnFragmentInteractionListener mListener;
    private Button mBeginButton;
    private Bundle mBundle;
    public CreateWorryNotFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param userName
     * @return
     */
    public static CreateWorryNotFragment newInstance(String userName) {
        CreateWorryNotFragment fragment = new CreateWorryNotFragment();
        Bundle args = new Bundle();
        args.putString(USER_NAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mUserName = getArguments().getString(USER_NAME);
//            Log.e(TAG,mUserName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View outView = inflater.inflate(R.layout.fragment_create_worry_not, container, false);
        mBeginButton = (Button)outView.findViewById(R.id.begin_create_worry_not_fragment_btn);
        mBeginButton.setOnClickListener(this);
        Log.e(TAG,"SECOND");
        Log.e(TAG,"NAME HAS BEEN TRANSFERED"+mUserName);
        return outView;
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
        switch (v.getId()){
            case R.id.begin_create_worry_not_fragment_btn:
                Intent intent = new Intent(getContext(), CreateWorryNotInfoActivity.class);
                mBundle = new Bundle();
                mBundle.putString(USER_NAME,mUserName);
                intent.putExtra(BUNDLE,mBundle);
                startActivity(intent);
                break;
            default:break;
        }
    }
    public void updateName(String name){
        Log.e(TAG,"Here theare are NAME IS:" + name);
        this.mUserName = name;
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
