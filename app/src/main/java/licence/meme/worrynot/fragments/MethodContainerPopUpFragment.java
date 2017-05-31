package licence.meme.worrynot.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;
import licence.meme.worrynot.models.Method;
import licence.meme.worrynot.models.MethodChangedEvent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MethodContainerPopUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MethodContainerPopUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MethodContainerPopUpFragment extends DialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String TAG_FRAGMENT = "METHOD_CONTAINER_POP_UP_FRAGMENT" ;
    private OnFragmentInteractionListener mListener;
    private ListView mListView;
    private Button mSelectedButton;
    private FirebaseService mFireBFirebaseService;
    private Method mCurrentMethod;

    public MethodContainerPopUpFragment() {
        // Required empty public constructor
    }
    public String getTagFragment(){
        return TAG_FRAGMENT;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MethodContainerPopUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MethodContainerPopUpFragment newInstance(String param1, String param2) {
        MethodContainerPopUpFragment fragment = new MethodContainerPopUpFragment();
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
        View return_view = inflater.inflate(R.layout.fragment_method_container_pop_up, container, false);
        mFireBFirebaseService = FirebaseService.getInstance();
        mListView =(ListView) return_view.findViewById(R.id.list_fragment_methods_container_lv);
        mSelectedButton = (Button)return_view.findViewById(R.id.cancel_fragment_methods_container_btn);
        mFireBFirebaseService.populateMethodsContainer(mListView,return_view.getContext());
        mCurrentMethod = null;
        mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentMethod = null;
                Method method = (Method)parent.getItemAtPosition(position);
                mCurrentMethod = method;
                mSelectedButton.setText(method.getMetadata().getName());
            }
        });
        mSelectedButton.setOnClickListener(this);
        return return_view;
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
            case R.id.cancel_fragment_methods_container_btn:
//                EventBus bus = EventBus.getDefault();
//                bus.post(new MethodChangedEvent(mCurrentMethod));
                FirebaseService.getInstance().setActiveMethod(getActivity(),mCurrentMethod);
                break;
            default:break;
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
