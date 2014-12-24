package controllers;

import persistence.entity.User;
import persistence.helper.UserHelper;
import play.data.Form;
import play.db.jpa.Transactional;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.register;


public class Authentication extends Controller {

    public static class LoginForm {
        @Constraints.Required
        @Constraints.Email(message = "error.email")
        public String email;

        @Constraints.Required
        @Constraints.MinLength(value = 8, message = "error.password")
        public String password;

        public String validate() {
            if (!UserHelper.authenticate(email, password))
                return Messages.get("error.login");
            return null;
        }
    }

    public static final String SESSION_EMAIL = "email";

    public static Result login(){
        return ok(login.render(Form.form(LoginForm.class)));
    }

    @Transactional
    public static Result submitLogin() {
        Form<LoginForm> loginForm = Form.form(LoginForm.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        }
        else {
            setSessionData(loginForm.get().email);
            return redirect(routes.AgileBook.index());
        }
    }

    public static class RegisterForm {
        @Constraints.Required
        @Constraints.Email(message = "error.email")
        public String email;

        @Constraints.Required
        @Constraints.MinLength(value = 8, message = "error.password")
        public String password;

        public String validate() {
            if (UserHelper.userExists(email))
                return Messages.get("error.register.exists");
            return null;
        }
    }

    public static Result registration() {
        return ok(register.render(Form.form(RegisterForm.class)));
    }

    @Transactional
    public static Result submitRegistration(){
        Form<RegisterForm> registerForm = Form.form(RegisterForm.class).bindFromRequest();

        if (registerForm.hasErrors()) {
            return badRequest(register.render(registerForm));
        }

        User user = UserHelper.createNewUser(registerForm.get().email, registerForm.get().password);
        setSessionData(user.getEmail());
        return redirect(routes.AgileBook.index());
    }

    private static void setSessionData(String email) {
        session().clear();
        session(SESSION_EMAIL, email);
    }

    public static Result logout() {
        session().clear();
        return redirect(routes.AgileBook.index());
    }
}
