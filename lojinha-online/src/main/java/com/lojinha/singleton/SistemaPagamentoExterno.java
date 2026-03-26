package com.lojinha.singleton;

import com.lojinha.model.Pedido;
import java.util.Random;

/**
 * Justificativa do uso do Singleton:
 * O padrão Singleton é aplicado aqui para garantir que exista apenas uma única instância 
 * responsável pela comunicação com o serviço externo de pagamento durante toda a execução do sistema.
 * Isso é fundamental para:
 * 1. Centralizar o controle de conexões com a API externa, evitando sobrecarga de instâncias.
 * 2. Manter um estado consistente de logs ou sessões de comunicação com o provedor de pagamento.
 * 3. Garantir que configurações globais (como chaves de API ou endpoints) sejam carregadas uma única vez.
 */
public class SistemaPagamentoExterno {
    private static SistemaPagamentoExterno instancia;
    private Random random;

    private SistemaPagamentoExterno() {
        this.random = new Random();
        System.out.println("[SISTEMA PAGAMENTO] Conexão com gateway externo inicializada.");
    }

    public static synchronized SistemaPagamentoExterno getInstancia() {
        if (instancia == null) {
            instancia = new SistemaPagamentoExterno();
        }
        return instancia;
    }

    public boolean processarPagamento(Pedido pedido) {
        System.out.println("[SISTEMA PAGAMENTO] Processando pagamento do Pedido #" + pedido.getId() + " no valor de R$ " + pedido.getValorTotal());
        
        // Simulação de comunicação externa
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simula 80% de chance de sucesso
        boolean sucesso = random.nextDouble() < 0.8;
        
        if (sucesso) {
            System.out.println("[SISTEMA PAGAMENTO] Pagamento APROVADO para o Pedido #" + pedido.getId());
        } else {
            System.out.println("[SISTEMA PAGAMENTO] Pagamento REJEITADO para o Pedido #" + pedido.getId());
        }
        
        return sucesso;
    }
}
