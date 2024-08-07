package br.com.repository;

import br.com.entidades.Pessoa;

public interface IDaoPessoa {//aula 29.13
	
	Pessoa consultarPessoa(String login, String senha);

}
