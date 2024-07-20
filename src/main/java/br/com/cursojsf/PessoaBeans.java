package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;

import br.com.DAO.DaoGeneric;
import br.com.entidades.Pessoa;

 
@ManagedBean(name = "pessoaBeans")
@ViewScoped
public class PessoaBeans {
	
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	
	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		//pessoa = new Pessoa();//instancia uma nova pessoa depois que salva - o formulário fica limpo
		return "";
	}
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	public String remove() {//aula 28.15
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();//deixando os inputs em branco depois que remove
		return "";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	

	
	
	

}
