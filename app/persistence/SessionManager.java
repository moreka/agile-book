package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Map;

public final class SessionManager {

    private static SessionFactory sessionFactory;

    public static Session getSession() {
        if (sessionFactory == null) {
            Configuration cfg = getConfiguration(null);

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    cfg.getProperties()).buildServiceRegistry();
            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory.getCurrentSession();
    }

    static public Session getSession(Map<String, String> map) {
        Configuration cfg = getConfiguration(map);

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                cfg.getProperties()).buildServiceRegistry();
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
