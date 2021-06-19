package API;

import com.google.firebase.auth.FirebaseAuth;

import Models.AC;
import Models.Mod;
import Models.User;

public class IslahManzil {


    private User user = null;
    private static IslahManzil islahManzil = null;
    public static int screennum;
    public Mod []M= new Mod[10];

    private IslahManzil(){
        for(int i = 0; i<10;i++)
            this.M[i] = new Mod("");

    }

    public void logout(){
        user = null;
        FirebaseAuth.getInstance().signOut();
    }

    public static IslahManzil getIslah(){
        if(islahManzil == null) {
            islahManzil = new IslahManzil();

        }
        return islahManzil;
    }

    public User getUser() {


        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
