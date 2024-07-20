package br.com.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {//classe responsável pela persistência
	
	private static EntityManagerFactory factory = null;
	
	static {//esse método só pode ser executado apenas uma vez
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
		}//método que vai garatir a persistencia dos dados
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {//aula 28.15
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
		
	}

}
