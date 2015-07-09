package br.com.exemplos.db;
import org.hibernate.StatelessSession;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.exemplos.model.LogTableColumn;

public class MyPreUpdateEventListener implements PreUpdateEventListener {
	
	private static final long serialVersionUID = 8140358242901801576L;
	private static final Logger logger = LoggerFactory.getLogger(MyPreUpdateEventListener.class);

	public boolean onPreUpdate(PreUpdateEvent event) {
        final Object entity = event.getEntity();
    	final Object[] oldValues = event.getOldState();
        final Object[] state = event.getState();
        final String[] properties = event.getPersister().getPropertyNames();

        // Iterate through all fields of the updated object
        for ( int i = 0; i < properties.length; i++ ) {
            if (oldValues != null && !oldValues[i].equals(state[i])) {
            	LogTableColumn log = createLog(event, properties[i], oldValues[i].toString(), 
            			state[i].toString(), entity.getClass().getCanonicalName(), (Long) event.getId());
                logger.debug("Valor diferente: {}", log.toString());
            }
        }
        return false;
	}
	
	private LogTableColumn createLog(PreUpdateEvent event, String column, String oldValue, 
			String newValue, String entityName, long id){
		LogTableColumn log = new LogTableColumn();
		log.setRecordId(id);
    	log.setColumn(column);
    	log.setTable(entityName);
    	log.setOldValue(oldValue.toString());
    	log.setNewValue(newValue.toString());
    	
    	//http://tapestry5.wikidot.com/auditoria-com-hibernate
    	StatelessSession session = event.getPersister().getFactory().openStatelessSession();
        session.beginTransaction();
        session.insert(log);

        session.getTransaction().commit();
        session.close();
        return log;
	}
}