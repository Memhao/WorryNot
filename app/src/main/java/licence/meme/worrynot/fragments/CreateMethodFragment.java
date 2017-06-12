package licence.meme.worrynot.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import licence.meme.worrynot.R;
import licence.meme.worrynot.gui.logic.adapter.RecycleViewEntryAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateMethodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateMethodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateMethodFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mStepsRecyclerView;
    private RecycleViewEntryAdapter mStepsAdapter;
    private Button mStepButton;
    public CreateMethodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateMethodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateMethodFragment newInstance(String param1, String param2) {
        CreateMethodFragment fragment = new CreateMethodFragment();
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
        View parentView  = inflater.inflate(R.layout.fragment_create_method, container, false);
        mStepsRecyclerView = (RecyclerView)parentView.findViewById(R.id.steps_create_method_fragment_rv);
        mStepButton = (Button)parentView.findViewById(R.id.add_steps_create_method_btn);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mStepsRecyclerView.setLayoutManager(linearLayoutManager);

        mStepsAdapter = new RecycleViewEntryAdapter(inflater,mStepsRecyclerView,this.getActivity());
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(newHelperCallback());
//        itemTouchHelper.attachToRecyclerView(mStepsRecyclerView);
        mStepsRecyclerView.setAdapter(mStepsAdapter);
        mStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(String s:mStepsAdapter.getEntries()){
                    Log.e("CreateMethodFragment","[onCreate]"+s);
                }
                for(int i = 0; i < mStepsRecyclerView.getChildCount();i++){
                    RecycleViewEntryAdapter.EntryHolder holder = (RecycleViewEntryAdapter.EntryHolder) mStepsRecyclerView.findViewHolderForAdapterPosition(i);
                    EditText et = holder.getEntryEditText();
                    Log.e("LOG:", et.getText().toString());
                }

            }
        });

        mStepsRecyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        return parentView;
    }

    private void hideKeyboard(View v){
        InputMethodManager inputMethodManager =(InputMethodManager)this.getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private ItemTouchHelper.Callback newHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleEntryTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        moveEntry(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        deleteEntry(viewHolder.getAdapterPosition());
                    }
                };
                return simpleEntryTouchCallback;
    }
    public void moveEntry(int oldPosition, int newPosition){

    }
    public void deleteEntry(int position){

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
