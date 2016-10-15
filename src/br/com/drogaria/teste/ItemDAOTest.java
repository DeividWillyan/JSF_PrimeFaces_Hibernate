package br.com.drogaria.teste;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;

public class ItemDAOTest {

	@Test
	@Ignore
	public void salvar() {

		Produto p = new Produto();
		Venda v = new Venda();
		ProdutoDAO pdao = new ProdutoDAO();
		VendaDAO vdao = new VendaDAO();

		v = vdao.buscarPorCodigo(1L);
		p = pdao.buscarPorCodigo(2L);

		Item i = new Item();
		i.setProduto(p);
		i.setQuantidade(10);
		i.setValor(new BigDecimal("100.00"));
		i.setVenda(v);

		ItemDAO idao = new ItemDAO();
		idao.salvar(i);

	}

	@Test
	@Ignore
	public void listar() {
		ItemDAO dao = new ItemDAO();
		for (Item i : dao.listar()) System.out.println(i);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		Item i = new Item();
		ItemDAO dao = new ItemDAO();
		i = dao.buscarPorCodigo(1L);
		System.out.println(i);
	}
	
}
