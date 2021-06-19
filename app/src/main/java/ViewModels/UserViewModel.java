package ViewModels;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import API.FirebaseRepository;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import API.Strings;
import Models.User;

public class UserViewModel extends ViewModel{
    private MutableLiveData<User> user = null;

    public LiveData<User> getCurrenUser(String uid) {
        if (user == null) {
            user = new MutableLiveData<User>();
            loadUser(uid);
        }
        return user;
    }

    private void loadUser(String uid) {
        // Do an asynchronous operation to fetch users
        final FirebaseRepository fire = FirebaseRepository.getFire();

        final DocumentReference docRef = fire.getUserDataAddress();

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    User u = new User();

                    u.setName(documentSnapshot.getString(Strings.NAME));
                    u.setPhoneNumber(documentSnapshot.getString(Strings.PHONE));

                    user.setValue(u);

                }
            }
        });
    }



}
