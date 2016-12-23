package com.johndoe.mtourismbeta;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MonumentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MonumentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonumentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity parent;
    private OnFragmentInteractionListener mListener;

    public MonumentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonumentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonumentFragment newInstance(String param1, String param2) {
        MonumentFragment fragment = new MonumentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart(){
        super.onStart();

        parent=(MainActivity)getActivity();
        String mode =parent.getMode();
        int position = parent.getPosition();

        Integer[] details={R.string.mosquee,R.string.twin,R.string.cathedrale,R.string.corniche,R.string.moroccomall,R.string.amedina,R.string.placemv};
        Integer[] images={R.drawable.mosquee,R.drawable.twin,R.drawable.cathedrale,R.drawable.corniche,R.drawable.moroccomall,R.drawable.amedina,R.drawable.placemv};
        String[] item = {"Mosquée Hassan II", "Twin Center", "Cathédrale du Sacre Coeur", "Corniche", "Morocco Mall",
                "Ancienne Medina", "Place Mohamed V"};
        LatLng[] latlong={new LatLng(33.6073889,-7.6323399),new LatLng(33.5866087,-7.632392),new LatLng(33.5917486,-7.6242794)
                ,new LatLng(33.594165,-7.6777267),new LatLng(33.5757568,-7.7091354),new LatLng(33.6009834,-7.6302486),new LatLng(33.5935,-7.6058)};


        ImageView img=(ImageView) getView().findViewById(R.id.imageViewDetail);
        TextView detail=(TextView) getView().findViewById(R.id.textViewDetail);
        String titre=item[position];
        img.setBackgroundResource(images[position]);
        String mystring = getResources().getString(details[position]);
        detail.setText(mystring);
        LatLng localisation=latlong[position];
        parent.setLatLng(localisation);
        parent.setTitle(item[position]);
        parent.changeAction(item[position]);
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
        return inflater.inflate(R.layout.fragment_monument, container, false);
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
