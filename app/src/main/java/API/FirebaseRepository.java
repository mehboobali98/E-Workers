package API;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseRepository {

    private static FirebaseRepository fire = null;


    private FirebaseRepository(){

    }

    public static FirebaseRepository getFire(){
        if(fire == null)
            fire = new FirebaseRepository();
        return fire;
    }

    public DocumentReference getUserDataAddress(){
        FirebaseFirestore fire = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DocumentReference documentReference = fire.collection(Strings.USERDATA).document(firebaseAuth.getCurrentUser().getUid());
        return documentReference;
    }

    public DocumentReference getIsEnabledAddress(String name){
        FirebaseFirestore fire = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DocumentReference documentReference = fire.collection(Strings.Enabled).document(name);
        return documentReference;
    }

    public CollectionReference getUserOrderDataAddress(){
        FirebaseFirestore fire = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        CollectionReference cReference = fire.collection(Strings.USERDATA).document(firebaseAuth.getCurrentUser().getUid()).collection(Strings.ORDER);
        return cReference;
    }
    public CollectionReference getOrderDataAddress(){
        FirebaseFirestore fire = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        CollectionReference cReference = fire.collection(Strings.ORDER);
        return cReference;
    }


}
