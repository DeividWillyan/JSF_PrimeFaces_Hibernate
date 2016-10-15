package br.com.drogaria.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Funcionario f = new Funcionario();
		f.setNome("Deivid Willyan");
		f.setCpf("097.580.929-60");
		f.setSenha("1q2w3e");
		f.setFuncao("Programador");

		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(f);
	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> f = dao.listar();

		for (Funcionario fun : f) {
			System.out.println(fun);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f = dao.buscarPorCodigo(1L);

		System.out.println(f.getNome() + " " + f.getFuncao());
	}

	@Test
	@Ignore
	public void delete() {
		FuncionarioDAO dao = new FuncionarioDAO();
		// dao.excluir(1L);
	}

	@Test
	@Ignore
	public void editar() {
		Funcionario f = new Funcionario();
		f.setCodigo(2L);
		f.setNome("Deivid Willyan");
		f.setSenha("1q2w3e");
		f.setCpf("097.580.929-60");
		f.setFuncao("Analista Programador Java");

		FuncionarioDAO dao = new FuncionarioDAO();
		dao.editar(f);

	}

	@Test
	@Ignore
	public void autenticar() {

		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f = dao.autenticar("097.580.929-60", "1q2w3e");
		System.out.println("Funcionario: " + f);

	}

}
