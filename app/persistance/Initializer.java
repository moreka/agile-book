package persistance;

import org.hibernate.Session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Initializer {
    public static void main(String[] args){
        new Initializer().initialize();
    }


    public void initialize() {
        System.out.println("initialize started");

        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "create-drop");
        doInitializing(SessionManager.getSession(map));

        System.out.println("initialize finished");
    }

    private void doInitializing(Session sesssion){

    }




}
