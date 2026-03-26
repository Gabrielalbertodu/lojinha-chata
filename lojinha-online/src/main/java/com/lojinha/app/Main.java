package com.lojinha.app;

import com.lojinha.model.Cliente;
import com.lojinha.model.Pedido;
import com.lojinha.model.Produto;
import com.lojinha.service.LojaService;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("SIMULAÇÃO");
        LojaService loja = new LojaService();

        // 1. Identificação de cliente
        Cliente cliente = loja.getClientes().get(0); // João Silva
        System.out.println("Cliente identificado: " + cliente.getNome());

        // 2. Listagem de produtos
        System.out.println("\nProdutos disponíveis:");
        for (Produto p : loja.getProdutos()) {
            System.out.println("- " + p.getNome() + ": R$ " + p.getPreco());
        }

        // 3. Seleção de produtos e Criação de pedido
        System.out.println("\nSelecionando produtos para o pedido");
        List<Produto> itensSelecionados = new ArrayList<>();
        itensSelecionados.add(loja.getProdutos().get(0)); // Smartphone
        itensSelecionados.add(loja.getProdutos().get(2)); // Fone de Ouvido

        Pedido pedido = loja.criarPedido(cliente, itensSelecionados);
        System.out.println("Pedido criado: " + pedido);

        // 4. Processamento de pagamento (via Singleton)
        System.out.println("\nIniciando processamento de pagamento");
        loja.processarPedido(pedido);

        System.out.println("\nStatus final do pedido: " + pedido.getStatus());
        System.out.println("FIM DA SIMULAÇÃO ");
    }
}
