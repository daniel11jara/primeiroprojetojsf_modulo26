package br.com.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<E> {

	public void salvar(E entidade) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(entidade);

		entityTransaction.commit();

		entityManager.close();

	}

	public E merge(E entidade) {// aula 28.14

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E retorno = entityManager.merge(entidade);// vai retornar uma entidade

		entityTransaction.commit();

		entityManager.close();

		return retorno;

	}

	public void delete(E entidade) {// aula 28.15

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.remove(entidade);

		entityTransaction.commit();

		entityManager.close();

	}
	
	/*
	 * public void deletePorId(E entidade) {// aula 28.15
	 * 
	 * EntityManager entityManager = JPAUtil.getEntityManager(); EntityTransaction
	 * entityTransaction = entityManager.getTransaction();
	 * entityTransaction.begin();
	 * 
	 * Object id = JPAUtil.getPrimaryKey(entidade);
	 * entityManager.createQuery("delete from " +
	 * entidade.getClass().getCanonicalName() + " where id  = " +
	 * id).executeUpdate();
	 * 
	 * entityManager.remove(entidade);
	 * 
	 * entityTransaction.commit();
	 * 
	 * entityManager.close();
	 * 
	 * }
	 */
	
	public void deletePorId(E entidade) {

	    EntityManager entityManager = JPAUtil.getEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();

	    try {
	        Object id = JPAUtil.getPrimaryKey(entidade);

	        // Buscar a entidade pelo ID
	        E managedEntity = (E) entityManager.find(entidade.getClass(), id);

	        if (managedEntity != null) {
	            entityManager.remove(managedEntity);
	        }

	        entityTransaction.commit();
	    } catch (Exception e) {
	        entityTransaction.rollback();
	        e.printStackTrace();
	    } finally {
	        entityManager.close();
	    }
	}
	
	public List<E> getListEntity(Class<E> entidade){ //aula 28.17
			
		EntityManager entityManager = JPAUtil.getEntityManager();
		    EntityTransaction entityTransaction = entityManager.getTransaction();
		    entityTransaction.begin();
		    
		    List<E> retorno  = entityManager.createQuery("from " + entidade.getName()).getResultList();//vai retornar uma lista
		    //fazendo de forma genérica, sem precisar selecionar os atributos
		    
		    entityTransaction.commit();
		    entityManager.close();
		    
		    return retorno;
		
	}

}
