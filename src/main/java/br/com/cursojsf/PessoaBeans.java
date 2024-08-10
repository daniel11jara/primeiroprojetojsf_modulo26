package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.DAO.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoPessoa;
import br.com.repository.IDaoPessoaImpl;

@ManagedBean(name = "pessoaBeans") // facilita a ligação entre usuário e a lógica de aplicação
@ViewScoped // preserva os dados do usuario enquanto permanece na página
public class PessoaBeans {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();// aula 28.17 - lista de pessoas

	private IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();// aula 29.13

	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		// pessoa = new Pessoa();//instancia uma nova pessoa depois que salva - o
		// formulário fica limpo
		carregarPessoas();
		return "";
	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public String remove() {// aula 28.15
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();// deixando os inputs em branco depois que remove
		carregarPessoas();
		return "";
	}

	@PostConstruct // centraliza a lógica de inicialização em único método
	public void carregarPessoas() {// aula 28.17
		pessoas = daoGeneric.getListEntity(Pessoa.class);
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String logar() {// aula 29.13

		Pessoa pessoaUser = iDaoPessoa.consultarPessoa(pessoa.getLogin(), pessoa.getSenha());

		if (pessoaUser != null) {// achou o usuario

			// adicionar o usuario na sessao usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			// externalContext.getSessionMap().put("usuarioLogado", pessoaUser);

			HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
			HttpSession session = req.getSession();

			session.setAttribute("usuarioLogado", pessoaUser);

			return "primeirapagina2.jsf";// redirecionando para a primeira página
		}

		return "index.jsf";
	}

	/*
	 * public boolean permiteAcesso(String acesso) {// aula 29.14 
	 * FacesContext context = FacesContext.getCurrentInstance(); 
	 * ExternalContext externalContext= context.getExternalContext(); 
	 * Pessoa pessoaUser = (Pessoa)externalContext.getSessionMap().get("usuarioLogado");// reconhecimento do usuário
	 * 
	 * 
	 * return pessoaUser.getPerfilUser().equals(acesso);// dando acesso ao usuário }
	 * 
	 * public boolean permiteAcessoAdministrador() { return
	 * permiteAcesso("ADMINISTRADOR"); }
	 */

}
