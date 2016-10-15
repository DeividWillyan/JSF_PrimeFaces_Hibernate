package br.com.drogaria.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Fabricante f1 = new Fabricante();
		Fabricante f2 = new Fabricante();
		f1.setDescricao("Fabricante 3");
		f2.setDescricao("Fabricante 4");

		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(f1);
		dao.salvar(f2);

	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO dao = new FabricanteDAO();

		List<Fabricante> f = dao.listar();

		for (Fabricante fabricante : f) {
			System.out.println(fabricante);
		}
	}

	@Test
	//@Ignore
	public void buscarPorCodigo() {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f = dao.buscarPorCodigo(2L);
		System.out.println(f);

	}

	@Test
	@Ignore
	public void excluir() {

		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f = dao.buscarPorCodigo(2L);
		dao.excluir(f);

	}

	@Test
	@Ignore
	public void excluirPorCodigo() {

		FabricanteDAO dao = new FabricanteDAO();
		dao.excluir(2L);

	}

	@Test
	@Ignore
	public void editar() {

		Fabricante f = new Fabricante();
		f.setCodigo(6L);
		f.setDescricao("Fabricante 6");

		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(f);

	}

}
