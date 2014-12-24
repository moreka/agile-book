package controllers;

import persistence.entity.User;
import persistence.helper.UserHelper;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.registration;

/**
 * Created by root on 12/23/14.
 */
public class Authentication extends Controller {

    public static final String SESSION_EMAIL = "email";

    public static Result login(){
        return ok(login.render(false));
    }

    @Transactional
    public static Result submitLogin(String email, String password){
        try{
            if(UserHelper.authenticate(email, password)){
                setSessionData(email);
                return redirect(routes.AgileBook.index());
            }else
                return error();
        }catch (Exception e){
            return error();
        }

        return null;
    }

    public static Result registration(String email, String password){
        if (email == null)
            email = "";

        if (password == null)
            password = "";

        boolean userExists = UserHelper.userExists(email);

        return ok(registration.render(userExists, email, password));
    }

    public static Result submitRegistration(String email, String password){
        if (email == null || email.equals("") || password == null || password.equals("") || UserHelper.userExists(email))
            return ok(registration.render(false, email, password));

        User user = UserHelper.createNewUser(email, password);
        setSessionData(user.getEmail());
        return redirect(routes.AgileBook.index());
    }

    private static void setSessionData(String email) {
        session().clear();
        session(SESSION_EMAIL, email);
    }

    private static Status error() {
        return ok(login.render(true));
    }
}
