package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.DAO.DaoGeneric;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;

@ViewScoped// a instancia sera mantida enquanto o usuário estiver na mesma view/página
@ManagedBean(name = "lancamentoBean")//Faz com que a classe seja gerenciada pelo JSF, permitindo que ela seja referenciada nas páginas JSF pelo nome lancamentoBean.
public class LancamentoBean {//aula 29.16
	
	private Lancamento lancamento = new Lancamento();//representa o lançamento que está sendo gerenciado.
	private DaoGeneric<Lancamento> daoGeneric = new DaoGeneric<Lancamento>();// possui métodos para salvar, atualizar, deletar e buscar entidades no banco de dados.
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();//usada para armazenar e exibir múltiplos lançamentos na interface.
	
	
	public String salvar() {
		
		FacesContext context = FacesContext.getCurrentInstance(); 
		ExternalContext externalContext= context.getExternalContext(); 
		Pessoa pessoaUser = (Pessoa)externalContext.getSessionMap().get("usuarioLogado");
		lancamento.setUsuario(pessoaUser);
		daoGeneric.salvar(lancamento);
		return "lancamento";
	}
	
	public String novo() {
		lancamento = new Lancamento(); // Limpar o formulário para um novo lançamento
		return "";
	}
	
	public String remover() {
		daoGeneric.delete(lancamento);
        lancamento = new Lancamento(); // Limpar o formulário
        return "lancamento"; // Nome da página para redirecionar
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public DaoGeneric<Lancamento> getDaoGeneric() {
		return daoGeneric;
	}
	public void setDaoGeneric(DaoGeneric<Lancamento> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	
	
	

}
