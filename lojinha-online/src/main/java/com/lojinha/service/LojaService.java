package com.lojinha.service;

import com.lojinha.model.Cliente;
import com.lojinha.model.Pedido;
import com.lojinha.model.Produto;
import com.lojinha.singleton.SistemaPagamentoExterno;
import java.util.ArrayList;
import java.util.List;

public class LojaService {
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Pedido> pedidos;
    private int proximoPedidoId;

    public LojaService() {
        this.clientes = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.proximoPedidoId = 1;
        inicializarDados();
    }

    private void inicializarDados() {
        // Clientes estáticos
        clientes.add(new Cliente(1, "João Silva", "joao@email.com"));
        clientes.add(new Cliente(2, "Maria Oliveira", "maria@email.com"));
        clientes.add(new Cliente(3, "Carlos Santos", "carlos@email.com"));

        // Produtos estáticos
        produtos.add(new Produto(101, "Smartphone", 1500.00));
        produtos.add(new Produto(102, "Notebook", 3500.00));
        produtos.add(new Produto(103, "Fone de Ouvido", 200.00));
        produtos.add(new Produto(104, "Mouse Sem Fio", 80.00));
    }

    public List<Cliente> getClientes() { return clientes; }
    public List<Produto> getProdutos() { return produtos; }

    public Pedido criarPedido(Cliente cliente, List<Produto> itens) {
        Pedido pedido = new Pedido(proximoPedidoId++, cliente);
        for (Produto p : itens) {
            pedido.adicionarProduto(p);
        }
        pedidos.add(pedido);
        System.out.println("[LOJA] Pedido #" + pedido.getId() + " criado para o cliente " + cliente.getNome());
        return pedido;
    }

    public void processarPedido(Pedido pedido) {
        SistemaPagamentoExterno gateway = SistemaPagamentoExterno.getInstancia();
        boolean pago = gateway.processarPagamento(pedido);
        
        if (pago) {
            pedido.setStatus("PAGO");
            System.out.println("[LOJA] Pedido #" + pedido.getId() + " finalizado com sucesso!");
        } else {
            pedido.setStatus("FALHA_PAGAMENTO");
            System.out.println("[LOJA] Pedido #" + pedido.getId() + " falhou devido ao pagamento.");
        }
    }
}
