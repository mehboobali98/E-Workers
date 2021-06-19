package com.example.islahmanzilagain;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import API.IslahManzil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AcMaintainence#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcMaintainence extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public MainCat myContext;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AcMaintainence() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AcMaintainence.
     */
    // TODO: Rename and change types and number of parameters
    public static AcMaintainence newInstance(String param1, String param2) {
        AcMaintainence fragment = new AcMaintainence();
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
        // Inflate the layout for this fragment
       final View root = inflater.inflate(R.layout.fragment_ac_maintainence, container, false);
        Spinner sp44 = (Spinner) root.findViewById(R.id.numofunits1);

        ArrayList<String> str = new ArrayList<>();
        for(int i = 0; i< 100; i++) {
            str.add(Integer.toString(i + 1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, str);
        sp44.setAdapter(adapter);
        Button butt = (Button)  root.findViewById(R.id.next);
        butt.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                if (IslahManzil.screennum==1)
                {

                    Spinner sp = (Spinner) root.findViewById(R.id.area1);
                    Spinner sp2 = (Spinner) root.findViewById(R.id.service1);
                    Spinner sp3 = (Spinner) root.findViewById(R.id.unitype1);
                    Spinner sp4 = (Spinner) root.findViewById(R.id.numofunits1);
                    String newData = "AC MAINTAINENCE!@#" + sp.getSelectedItem().toString()+"!@#"+sp2.getSelectedItem().toString()+"!@#"+sp3.getSelectedItem().toString()+"!@#"+sp4.getSelectedItem().toString()+"^";
                    IslahManzil.getIslah().M[IslahManzil.screennum - 1].setSting(IslahManzil.getIslah().M[IslahManzil.screennum - 1].getSting() + newData);
                }
                Map mp = new Map();
                mp.myContext = myContext;
                myContext.getSupportFragmentManager().beginTransaction().replace(R.id.fml, mp).commit();
            }
        });



        return root;
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
