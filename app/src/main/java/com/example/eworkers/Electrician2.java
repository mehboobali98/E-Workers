package com.example.eworkers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import API.EWorkers;
import API.FirebaseRepository;
import API.Strings;
import Models.User;
import ViewModels.UserViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Electrician2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Electrician2 extends Fragment {

    public MainCat myContext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Electrician2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Electrician2.
     */
    // TODO: Rename and change types and number of parameters
    public static Electrician2 newInstance(String param1, String param2) {
        Electrician2 fragment = new Electrician2();
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

        final View root = inflater.inflate(R.layout.fragment_electrician2, container, false);
        Button bt = (Button) myContext.findViewById(R.id.sb2);
        bt.setBackgroundResource(R.drawable.notsel);

        Button bt2 = (Button) myContext.findViewById(R.id.sb1);
        bt2.setBackgroundResource(R.drawable.notsel);
        Button bt3 = (Button) myContext.findViewById(R.id.sb3);
        bt3.setBackgroundResource(R.drawable.rounded_yellow);

        final Button fin = (Button) root.findViewById(R.id.nexter);

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fin.setEnabled(false);
                UserViewModel uvm = new UserViewModel();
                uvm.getCurrenUser(FirebaseAuth.getInstance().getUid()).observe(getActivity(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        Spinner sp = (Spinner) root.findViewById(R.id.preftime);
                        EditText sp2 = (EditText) root.findViewById(R.id.anythingtosay);

                        String newData = sp.getSelectedItem().toString()+"!@#"+sp2.getText().toString()+"!@#"+ user.getPhoneNumber()+
                                "!@#"+ user.getName() + "^";
                        EWorkers.getIslah().M[EWorkers.screennum - 1].setSting(EWorkers.getIslah().M[EWorkers.screennum - 1].getSting() + newData);

                        final Map<String, Object> userData = new HashMap<>();
                        final Map<String, Object> orderAdminData = new HashMap<>();
                        final Map<String, Object> orderPermanentData = new HashMap<>();
                        userData.put(Strings.NAME, EWorkers.getIslah().M[EWorkers.screennum-1].getSting());
                        userData.put(Strings.TIMESTAMP, FieldValue.serverTimestamp());
                        orderAdminData.put(Strings.NAME, EWorkers.getIslah().M[EWorkers.screennum-1].getSting());
                        orderAdminData.put(Strings.TIMESTAMP, FieldValue.serverTimestamp());

                        orderPermanentData.put(Strings.NAME, EWorkers.getIslah().M[EWorkers.screennum-1].getSting());
                        orderPermanentData.put(Strings.TIMESTAMP, FieldValue.serverTimestamp());

                        DocumentReference doc = FirebaseRepository.getFire().getUserOrderDataAddress().document();
                        final DocumentReference doc1 = FirebaseFirestore.getInstance().collection("ORDERS PERMANENT").document();
                        userData.put(Strings.DOCUMENTID, doc.getId());
                        orderPermanentData.put(Strings.DOCUMENTID, doc1.getId());
                        doc.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {
                                DocumentReference docAd = FirebaseRepository.getFire().getOrderDataAddress().document();
                                orderAdminData.put(Strings.DOCUMENTID, docAd.getId());
                                docAd.set(orderAdminData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //Set intent for the next screen
                                        doc1.set(orderPermanentData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent intent = new Intent(getActivity(), OrderCompletition.class);
                                                myContext.finish();
                                                startActivity(intent);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                fin.setEnabled(true);
                                                Toast.makeText(getContext(), "ERROR! Order was not placed. Please check internet connection", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        fin.setEnabled(true);
                                        Toast.makeText(getContext(), "ERROR! Order was not placed. Please check internet connection", Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                fin.setEnabled(true);
                                Toast.makeText(getContext(), "ERROR! Order was not placed. Please check internet connection", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });


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
