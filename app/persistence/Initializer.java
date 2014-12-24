package persistence;

import persistence.entity.User;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Initializer extends Controller{

    public static void main(String[] args){
        new Initializer().initialize();
    }


    public Result initialize() {
        System.out.println("initialize started");

        Map<String, Object> configOverrides = new HashMap<>();
        configOverrides.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernatePersistence", configOverrides);

        EntityManager em = emf.createEntityManager();

        doInitializing(em);
        em.close();

        emf.close();

        System.out.println("initialize finished");

        return ok("Success");
    }


    private void doInitializing(EntityManager em){
        User user = new User("psycho@psycho.com", "12345678");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }




}
