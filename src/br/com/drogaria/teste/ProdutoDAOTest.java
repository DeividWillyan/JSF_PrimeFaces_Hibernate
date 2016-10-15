package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante f = fdao.buscarPorCodigo(1L);
		System.out.println(f);

		Produto p = new Produto();
		p.setDescricao("Produto A");
		p.setPreco(new BigDecimal("19.99"));
		p.setQuantidade(10);
		p.setFabricante(f);

		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(p);
	}

	@Test
	@Ignore
	public void listar() {
		ProdutoDAO dao = new ProdutoDAO();

		for (Produto pro : dao.listar()) {
			System.out.println(pro);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = new Produto();

		p = dao.buscarPorCodigo(2L);

		System.out.println(p);
	}

	@Test
	@Ignore
	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(3L);
	}

	@Test
	@Ignore
	public void editar() {
		Produto p = new Produto();
		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante f = fdao.buscarPorCodigo(6L);

		p.setCodigo(2L);
		p.setDescricao("Produto B");
		p.setPreco(new BigDecimal("100.00"));
		p.setQuantidade(3);
		p.setFabricante(f);

		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
		Produto po = dao.buscarPorCodigo(2L);
		System.out.println(po);
		
	}

}
