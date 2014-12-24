package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Security;

import views.html.*;


public class AgileBook extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render("Welcome to our homepage"));
    }
}