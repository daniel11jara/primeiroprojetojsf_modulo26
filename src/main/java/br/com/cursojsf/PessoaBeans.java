package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;

 
@ManagedBean(name = "pessoaBeans")
@ViewScoped
public class PessoaBeans {
	
	private String nome;
	private List<String> nomes = new ArrayList<String>();
	private HtmlCommandButton commandButton;
	
	
	public String addNome() {
		nomes.add(nome);//adicionando o nomes na lista
		
		if (nomes.size() > 3) {//quando for maior que 3
			commandButton.setDisabled(true);//disabilita o botão
			return "paginanavegada?faces-redirect=true";//vai para a página -- navegação dinâmica aula 33
		}
		
		return "";//e vai ficar na mesma página
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

	public HtmlCommandButton getCommandButton() {
		return commandButton;
	}

	public void setCommandButton(HtmlCommandButton commandButton) {
		this.commandButton = commandButton;
	}

	
	
	

}
