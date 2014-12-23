package persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Map;

public final class SessionManager {

    private static SessionFactory sessionFactory;

    public static Session getSession() {
        if (sessionFactory == null) {
            Configuration cfg = getConfiguration(null);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    cfg.getProperties()).build();
            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory.getCurrentSession();
    }

    static public Session getSession(Map<String, String> map) {
        Configuration cfg = getConfiguration(map);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);

        return sessionFactory.getCurrentSession();
    }

    private static Configuration getConfiguration(Map<String, String> map) {
        Configuration cfg = new Configuration().configure();

        if (map != null) {
            for (String key : map.keySet())
                cfg.setProperty(key, map.get(key));
        }

        return cfg;
    }

    private SessionManager() {
    }
}
