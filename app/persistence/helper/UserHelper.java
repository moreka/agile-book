package persistence.helper;

import persistence.entity.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

/**
 * Created by root on 12/23/14.
 */
public class UserHelper {
    public static final String DATABASE_NAME = "users";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    public static boolean authenticate(String email, String password){
        if (email == null || email.equals("") || password == null || email.equals(""))
            return false;

        Integer count = (Integer) JPA.em().createQuery("SELECT COUNT(*) FROM " + DATABASE_NAME + " " +
                "WHERE " + EMAIL + " = :" + EMAIL + " AND " + PASSWORD + " = :" + PASSWORD)
                .setParameter(EMAIL, email).setParameter(PASSWORD, password).getSingleResult();

        return count != null && count > 0;
    }

    public static boolean userExists(String email) {
        if (email == null || email.equals(""))
            return false;

        Integer count = (Integer) JPA.em().createQuery("SELECT COUNT(*) FROM " + DATABASE_NAME + " " +
                "WHERE " + EMAIL + " = :" + EMAIL).setParameter(EMAIL, email).getSingleResult();

        return count != null && count > 0;
    }

    @Transactional
    public static User createNewUser(String email, String password) {
        User user = new User(email, password);
        JPA.em().persist(user);
        return user;
    }
}
