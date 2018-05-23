package com.cursomc.uml;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.uml.domain.Categoria;
import com.cursomc.uml.domain.Cidade;
import com.cursomc.uml.domain.Cliente;
import com.cursomc.uml.domain.Endereco;
import com.cursomc.uml.domain.Estado;
import com.cursomc.uml.domain.Produto;
import com.cursomc.uml.domain.enums.TipoCliente;
import com.cursomc.uml.repositories.CategoriaRepository;
import com.cursomc.uml.repositories.CidadeRepository;
import com.cursomc.uml.repositories.EstadoRepository;
import com.cursomc.uml.repositories.ProdutoRepository;
import com.cursomc.uml.repositories.ClienteRepository;
import com.cursomc.uml.repositories.EnderecoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 4000.00);
		Produto p2 = new Produto (null,"Impressora", 1000.00);
		Produto p3 = new Produto (null, "Mouse", 120.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Lucia Alves de Azevedo", "mrlucia@gmail.com", "123.456.789-00", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("12312312123","12312312313"));
		
		Endereco e1 = new Endereco(null, "Rua 10", "42", "Conj. Fernando Collor", "Taicoca", "49160-000",cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Nikita", "11", "Conj. Shangrila", "Pq 10", "683443453", cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
