package ViewModels;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import API.FirebaseRepository;
import API.Strings;
import Models.Mod;

public class UserOrderViewModel {

    private MutableLiveData<List<Mod>> orders = null;

    public LiveData<List<Mod>> getOrders() {
        if (orders == null) {
            orders = new MutableLiveData<List<Mod>>();
            loadOrders();
        }
        return orders;
    }

    private void loadOrders() {
        // Do an asynchronous operation to fetch users.

        final FirebaseRepository fire = FirebaseRepository.getFire();


        CollectionReference docRef = fire.getUserOrderDataAddress();

        //Ordering by the timestamp

        docRef.orderBy(Strings.TIMESTAMP, Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    System.out.println(e);
                }
                List<DocumentSnapshot> ls = queryDocumentSnapshots.getDocuments();

                final List<Mod> ordersLs = new ArrayList<Mod>();

                for (final DocumentSnapshot d : ls) {
                    if (d.exists()) {


                        final Timestamp timestamp = d.getTimestamp(Strings.TIMESTAMP);

                        String data = d.getString(Strings.NAME);


                        Mod o = new Mod(data);
                        String id = d.getString(Strings.DOCUMENTID);
                        o.setID(id);
                        o.setTimestamp(timestamp);



                        ordersLs.add(o);


                    }

                }

                orders.setValue(ordersLs);

            }
        });
    }

}
