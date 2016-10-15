package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;

public class VendaDAOTest {

	@Test
	@Ignore
	public void salvar() {

		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f = dao.buscarPorCodigo(2L);

		Venda v = new Venda();
		v.setFuncionario(f);
		v.setHorario(new Date());
		v.setValor(new BigDecimal("666.99"));

		VendaDAO vdao = new VendaDAO();
		vdao.salvar(v);

	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO dao = new VendaDAO();
		for (Venda v : dao.listar()) {
			System.out.println(v);
		}
	}

	@Test
	@Ignore
	// caso seja buscado um codigo não existente, o retorno será null, o
	// hibernate/jpa não trata isto como um erro.
	public void buscarPorCodigo() {
		VendaDAO dao = new VendaDAO();
		Venda v = dao.buscarPorCodigo(2L);
		System.out.println(v);
	}

	@Test
	@Ignore
	public void remove() {
		VendaDAO dao = new VendaDAO();
		dao.excluir(2L);
	}

	@Test
	@Ignore
	public void editar() {

		Funcionario f = new FuncionarioDAO().buscarPorCodigo(2L);
		Venda v = new Venda();

		v.setCodigo(3L);
		v.setFuncionario(f);
		v.setHorario(new Date());
		v.setValor(new BigDecimal("29.90"));

		VendaDAO dao = new VendaDAO();
		dao.editar(v);

	}

}
