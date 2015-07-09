package br.com.exemplos.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.exemplos.db.GenericDAOImpl;
import br.com.exemplos.model.Person;

public class TestExample {

	final static Logger logger = LoggerFactory.getLogger(TestExample.class);
	private GenericDAOImpl<Person> dao = new GenericDAOImpl<Person>();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		(new TestExample()).run();
	}

	public void run() throws Exception {
		Person person = new Person();
		person.setFirstName("Alice");
		person.setLastName("Liddell");
		
		dao.insert(person);
		
		person.setLastName("Pleasance Liddell");
		
		dao.update(person);
	}
}
