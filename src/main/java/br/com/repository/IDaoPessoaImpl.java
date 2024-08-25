package br.com.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import antlr.collections.List;
import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

public class IDaoPessoaImpl implements IDaoPessoa {//aula 29.13

	@Override
//	public Pessoa consultarPessoa(String login, String senha) {
//	    Pessoa pessoa = null;
//	    try {
//	        EntityManager entityManager = null;
//			pessoa = (Pessoa) entityManager
//	            .createQuery("select p from Pessoa p where p.login = :login and p.senha = :senha", Pessoa.class)
//	            .setParameter("login", login)
//	            .setParameter("senha", senha)
//	            .getSingleResult();
//	    } catch (NoResultException e) {
//	        pessoa = null; // Nenhum resultado encontrado, atribuir null ou realizar outra lógica de tratamento
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//
//	    return pessoa;
//	}


	public Pessoa consultarPessoa(String login, String senha) {//faz a consulta ao banco de dados
		
		Pessoa pessoa = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'").getSingleResult();
		
		entityTransaction.commit();
		entityManager.close();
		
		return pessoa;
	}

}
