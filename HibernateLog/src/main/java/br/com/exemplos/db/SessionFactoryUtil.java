package br.com.exemplos.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

public final class SessionFactoryUtil {

  private static SessionFactory sessionFactory;

	private SessionFactoryUtil() {
	}

	static{
		try {
        	Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            ServiceRegistry serviceRegistry1 =((SessionFactoryImpl)sessionFactory).getServiceRegistry();
            //http://www.programcreek.com/java-api-examples/index.php?api=org.hibernate.event.service.spi.EventListenerRegistry
            final EventListenerRegistry eventListenerRegistry1=serviceRegistry1.getService(EventListenerRegistry.class);
            eventListenerRegistry1.setListeners(EventType.PRE_UPDATE, new MyPreUpdateEventListener());

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
  }

	public static SessionFactory getInstance() {
		return sessionFactory;
	}
	
	public static void close(){
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;
	
	}
}
