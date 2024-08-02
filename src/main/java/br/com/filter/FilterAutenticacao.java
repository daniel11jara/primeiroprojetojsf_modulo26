package br.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

@WebFilter(urlPatterns = {"/*"})//aula 29.11 - anotação vai interceptar todas as páginas
public class FilterAutenticacao implements Filter {
	
	@Override
	public void destroy(){
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)//doFilter vai ser execurado em todas as execuções 
			throws IOException, ServletException {//aqui é feita toda a autenticação
		
		HttpServletRequest req  = (HttpServletRequest) request;
		//HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		//String usuarioLogado = (String) session.getAttribute("usuarioLogado");
		String url = req.getServletPath();
		
		if(!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null ) {//|| usuarioLogado != null && usuarioLogado.trim().isEmpty()
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
			dispatcher.forward(request, response);
			return;
		}else {
			chain.doFilter(request, response);//executa as ações do request e do response
		}
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException { //init - vai ser executado quando sobe o servidor
		JPAUtil.getEntityManager();//levantado a conexão com o banco de dados
	}

}
