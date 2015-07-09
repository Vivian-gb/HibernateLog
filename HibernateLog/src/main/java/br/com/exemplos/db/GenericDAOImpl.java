package br.com.exemplos.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDAOImpl <T> {

	private Session session;
	private Transaction tx;
	
	public GenericDAOImpl() {
	}
	
	public Session getSession() {
		return session;
	}
	
	protected void begin() {	
		session = SessionFactoryUtil.getInstance().getCurrentSession();	
        tx = session.beginTransaction();
	}
	
	protected void commit() {
		tx.commit();
	}
	
	protected void rollback() {
		tx.rollback();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(String strQuery) throws Exception {
		begin();
        List<T> lista = null;
        try {            
            Query query = session.createQuery(strQuery);
            lista = query.list();
            commit();
        } catch (Exception e) {
        	rollback();
           throw e;
        }
        return lista;
    }
	
	public void insert(T al) throws Exception{        
		begin();
        try {            
        	session.persist(al);
            commit();       
        } catch (Exception e) {
        	rollback();
            throw e;
         }      
    }
    
    @SuppressWarnings("unchecked")
	public T update(T al) throws Exception{        
    	begin();
        try {            
        	T aux = (T) session.merge(al);
        	commit();     
            al = aux;
        } catch (Exception e) {
        	rollback();
            throw e;
         }
        return al;
    }
    
    public void delete(T al) throws Exception{        
    	begin();
        try {            
        	session.delete(al);
        	commit();           
        }  catch (Exception e) {
        	rollback();
            throw e;
         }    
    }
}
