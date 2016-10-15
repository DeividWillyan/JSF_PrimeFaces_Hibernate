package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String autenticar() {
		try {

			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarioLogado = dao.autenticar(funcionarioLogado.getCpf(), funcionarioLogado.getSenha());

			if (funcionarioLogado != null) {
				FacesUtil.addMsgInfo("Funcionario autentificado com sucesso");
				return "/pages/principal.xhtml?faces-redirect=true";
			} else {
				FacesUtil.addMsgErro("CPF ou Senha Incorretos.");
				return null;
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar autentificar um Funcionario: " + ex.getMessage());
			return null;
		}
	}

	public String sair() {
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}

}
