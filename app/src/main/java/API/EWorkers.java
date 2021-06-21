package API;

import com.google.firebase.auth.FirebaseAuth;

import Models.Mod;
import Models.User;

public class EWorkers {

    private User user = null;
    private static EWorkers EWorkers = null;
    public static int screennum;
    public Mod[] M = new Mod[10];

    private EWorkers() {
        for (int i = 0; i < 10; i++)
            this.M[i] = new Mod("");
    }

    public void logout() {
        user = null;
        FirebaseAuth.getInstance().signOut();
    }

    public static EWorkers getIslah() {
        if (EWorkers == null) {
            EWorkers = new EWorkers();
        }
        return EWorkers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
