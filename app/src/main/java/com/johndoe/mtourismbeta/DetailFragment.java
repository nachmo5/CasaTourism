package com.johndoe.mtourismbeta;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity parent;
    Integer[] img=new Integer[5];
    String[] titre=new String[5];
    String[] tel=new String[5];
    String[] location=new String[5];
    Integer position;
    LatLng[] latlong=new LatLng[5];
    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
    public void onStart() {
        super.onStart();
        parent=(MainActivity)getActivity();
        String mode =parent.getMode();
      final  int position = parent.getPosition();

        if(mode.equals("hotels")) {
            Integer[] image = {R.drawable.barcelo, R.drawable.kenzi, R.drawable.mavenpick, R.drawable.anfa, R.drawable.gray};
            String[] item = {"Hotel Barcelo", "Kenzi Tower", "Hotel Mavenpick", "La palace d'Anfa", "Gray Boutique Hotel and Spa"};
            String[] adresse = {"Boulevard D Anfa et rue Hammou,139/20000 Casablanca", "Boulevard Zerktouni, 20100 Casablanca"
                    , " Avenue Hassan II, 242842 Casablanca", "Boulevard de la Corniche, 94642 Casablanca,", "Rues Ain El Aouda / Abou Kacem Kotbari, Quartier R, 14 - 20050 Casablanca"};
            String[] telephone = {"+212522208000", "+212522970100", "05224-82300", "212 5227-97070", "054945200"};
            LatLng[] lato={new LatLng(33.591153,-7.635485),new LatLng(33.586568,-7.631562),new LatLng(33.583698,-7.623902),new LatLng(33.5898884,-7.6380245),new LatLng(33.593146,-7.640916)};
            img=image.clone();
            titre=item.clone();
            tel=telephone.clone();
            location=adresse.clone();
            latlong=lato.clone();
        }
        else if(mode.equals("restos")) {
            Integer[] image = {R.drawable.brasserie, R.drawable.iloli, R.drawable.blend, R.drawable.lasqala, R.drawable.tula};
            String[] item = {"Brasserie Le Relais Gourmet", "Restaurant Iloli", "Blend Gourmet Burger", "Restaurant LasQala", "Tula comida latina"};
            String[] adresse = {"16 rue Damir El Kabir, Casablanca", " Najib Mahfoud, Casablanca", " 9 rue Theophile Gauthier, Casablanca",
                    ": Boulevard des Almohades, Casablanca", "Hay Chrifa Rue 13, Casablanca"};
            String[] telephone = {"+2126647275", "+2126146834", "+2126852163", "+2126579256", "+2126859743"};
            LatLng[] lato={new LatLng(33.591123,-7.635410),new LatLng(33.586569,-7.631563),new LatLng(33.583695,-7.623907),new LatLng(33.5898886,-7.6380242),new LatLng(33.593110,-7.640916)};

            img=image.clone();
            titre=item.clone();
            tel=telephone.clone();
            location=adresse.clone();
            latlong=lato.clone();
        }
        LatLng localisation=latlong[position];
        parent.setLatLng(localisation);
        parent.setTitle(titre[position]);
        parent.changeAction(titre[position]);
        String[] title={"Adresse","Telephone"};
        String[] description={location[position],tel[position]};
        CustomAdapter2 cad=new CustomAdapter2(getActivity(),title,description);
        ListView lv=(ListView)getView().findViewById(R.id.listView);
        ImageView imag=(ImageView) getView().findViewById(R.id.imageView);

        imag.setBackgroundResource(img[position]);
        lv.setAdapter(cad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                if(pos==1) {
                    String numero = tel[position];
                    Uri call = Uri.parse("tel:" + numero);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
            }


        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
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
