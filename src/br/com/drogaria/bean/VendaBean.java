package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private List<Item> listaItens;
	private Venda vendaCadastro;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public List<Item> getListaItens() {
		if (listaItens == null) {
			listaItens = new ArrayList<Item>();
		}
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaProdutos = dao.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar LISTAR os Produtos: " + ex.getMessage());
		}
	}

	public void adicionar(Produto produto) {
		int posicaoEncontrada = -1;
		for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			Item temp = listaItens.get(pos);

			if (temp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}

		Item item = new Item();
		item.setProduto(produto);

		if (posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValor(produto.getPreco());
			listaItens.add(item);
		} else {
			Item temp = listaItens.get(posicaoEncontrada);
			item.setQuantidade(temp.getQuantidade() + 1);
			item.setValor(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicaoEncontrada, item);
		}

		vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco()));

	}

	public void remover(Item item) {
		int posicaoEncontrada = -1;
		for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			Item temp = listaItens.get(pos);

			if (temp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
			if (posicaoEncontrada > -1) {
				listaItens.remove(posicaoEncontrada);
				vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor()));
			}
		}
	}

	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());

		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario f = new Funcionario();
		f = fdao.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());
		vendaCadastro.setFuncionario(f);

	}

	public void salvar() {
		try {

			VendaDAO vdao = new VendaDAO();
			Long codigoVenda = vdao.salvar(vendaCadastro);
			Venda vendaFK = vdao.buscarPorCodigo(codigoVenda);

			for (Item item : listaItens) {
				item.setVenda(vendaFK);

				ItemDAO idao = new ItemDAO();
				idao.salvar(item);
			}

			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));

			listaItens = new ArrayList<Item>();

			FacesUtil.addMsgInfo("Venda salva com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar salvar a venda: " + ex.getMessage());
		}

	}

}
