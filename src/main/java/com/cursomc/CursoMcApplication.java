package com.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.Estado;
import com.cursomc.domain.ItemPedido;
import com.cursomc.domain.Pagamento;
import com.cursomc.domain.PagamentoComBoleto;
import com.cursomc.domain.PagamentoComCartao;
import com.cursomc.domain.Pedido;
import com.cursomc.domain.Produto;
import com.cursomc.domain.enums.EstadoPagamento;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.CidadeRepository;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.EnderecoRepository;
import com.cursomc.repositories.EstadoRepository;
import com.cursomc.repositories.ItemPedidoRepository;
import com.cursomc.repositories.PagamentoRepository;
import com.cursomc.repositories.PedidoRepository;
import com.cursomc.repositories.ProdutoRepository;

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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
		
	
	//Principal
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Games");
		Categoria cat4 = new Categoria(null, "Cinema");
		Categoria cat5 = new Categoria(null, "Livros");
		Categoria cat6 = new Categoria(null, "Hardware");
		Categoria cat7 = new Categoria(null, "Armazenamento");
		Categoria cat8 = new Categoria(null, "Smartphones");
		Categoria cat9 = new Categoria(null, "Perifericos");
		Categoria cat10 = new Categoria(null, "Eletrônicos");
		Categoria cat11 = new Categoria(null, "Automação");
		Categoria cat12 = new Categoria(null, "Segurança");
		Categoria cat13 = new Categoria(null, "Som & Imagem");
		Categoria cat14 = new Categoria(null, "Ferramentas");
		Categoria cat15 = new Categoria(null, "Instrumentos Musicais");
		Categoria cat16 = new Categoria(null, "Redes");
		
		Produto p1 = new Produto(null,"Computador", 4000.00);
		Produto p2 = new Produto (null,"Impressora", 1000.00);
		Produto p3 = new Produto (null, "Mouse", 120.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,
												 cat9,cat10,cat11,cat12,cat13,cat14,cat15,cat16));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pd1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido pd2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pmt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pd1, 6);
		pd1.setPagamento(pmt1);
		
		Pagamento pmt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pd2, sdf.parse("20/10/2017 00:00"), null);
		pd2.setPagamento(pmt2);
		
		cli1.getPedidos().addAll(Arrays.asList(pd1, pd2));
		
		pedidoRepository.saveAll(Arrays.asList(pd1, pd2));
		pagamentoRepository.saveAll(Arrays.asList(pmt1,pmt2));
		
		ItemPedido ip1 = new ItemPedido(pd1, p1, 0.0, 2, 4000.00);
		ItemPedido ip2 = new ItemPedido(pd1, p3, 0.0, 1, 120.00);
		ItemPedido ip3 = new ItemPedido(pd1, p2, 100.0, 1, 1000.00);
		
		pd1.getItens().addAll(Arrays.asList(ip1,ip2));
		pd2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
	}
}
