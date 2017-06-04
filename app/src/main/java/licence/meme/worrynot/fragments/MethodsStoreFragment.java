package licence.meme.worrynot.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import licence.meme.worrynot.R;
import licence.meme.worrynot.activities.MethodDetailsActivity;
import licence.meme.worrynot.adapter.RecycleViewItemAdapter;
import licence.meme.worrynot.models.RecycleViewItem;
import licence.meme.worrynot.models.RecycleViewMethodsData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MethodsStoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MethodsStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MethodsStoreFragment extends Fragment implements RecycleViewItemAdapter.ItemClickCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String METHOD_TITLE = "METHOD_TITLE";
    private static final String METHOD_AUTHOR = "METHOD_AUTHOR";
    private static final String BUNDLE = "BUNDLE";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    private RecycleViewItemAdapter mAdapter;

    private List<RecycleViewItem> mRecycleViewMethodsData;
    public MethodsStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MethodsStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MethodsStoreFragment newInstance(String param1, String param2) {
        MethodsStoreFragment fragment = new MethodsStoreFragment();
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
        View return_view = inflater.inflate(R.layout.fragment_methods_store, container, false);
        mRecyclerView = (RecyclerView)return_view.findViewById(R.id.methods_method_store_fragment_rv);


        mRecycleViewMethodsData = RecycleViewMethodsData.getListData();
        //RecycleViewMethodsData should be replaced with data from fire base
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new RecycleViewItemAdapter(mRecycleViewMethodsData,inflater);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setItemClickCallback(this);
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
    public void onItemClick(int position) {

        RecycleViewItem item= (RecycleViewItem)mRecycleViewMethodsData.get(position);
        Intent i = new Intent(this.getActivity(), MethodDetailsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(METHOD_TITLE,item.getMethodTitle());
        bundle.putString(METHOD_AUTHOR,item.getAuthor());

        i.putExtra(BUNDLE,bundle);
        startActivity(i);
    }

    @Override
    public void onFavouriteIconClick(int position) {

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
