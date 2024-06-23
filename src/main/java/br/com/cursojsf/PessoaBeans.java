package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ViewScoped 
@ManagedBean(name = "pessoaBeans")

public class PessoaBeans {
	
	private String nome;
	private List<String> nomes = new ArrayList<String>();
	
	public String addNome() {
		nomes.add(nome);//adicionando o nomes na lista
		return "";
	}
	
	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}
	
	public List<String> getNomes() {
		return nomes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
	

}
