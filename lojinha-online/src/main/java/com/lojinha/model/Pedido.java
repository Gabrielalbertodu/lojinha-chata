package com.lojinha.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Produto> itens;
    private double valorTotal;
    private String status;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
        this.status = "PENDENTE";
    }

    public void adicionarProduto(Produto produto) {
        this.itens.add(produto);
        this.valorTotal += produto.getPreco();
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<Produto> getItens() { return itens; }
    public double getValorTotal() { return valorTotal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Pedido [ID=" + id + ", Cliente=" + cliente.getNome() + ", Total=" + valorTotal + ", Status=" + status + "]";
    }
}
