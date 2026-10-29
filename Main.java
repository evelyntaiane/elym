package br.com.elym.app;

import br.com.elym.model.*;
import br.com.elym.pagamento.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ELYM | Mobilidade Urbana ===");

        Passageiro passageiro = new Passageiro(1, "Mariana", "mariana@email.com", "83999990000", "1234");
        Veiculo veiculo = new Veiculo("ABC1D23", "Fiat Argo", "Prata");
        Motorista motorista = new Motorista(2, "Carlos", "carlos@email.com", "83988880000", "abcd", "12345678900", veiculo);

        System.out.println("Login do passageiro: " + (passageiro.login("mariana@email.com", "1234") ? "sucesso" : "falhou"));
        System.out.println("Veículo: " + motorista.getVeiculo().getDetalhes());

        Corrida corrida = passageiro.solicitarCorrida(100, "Centro", "Universidade", 8.0);
        System.out.printf("Corrida solicitada: %s -> %s | Valor estimado: R$ %.2f%n",
                corrida.getOrigem(), corrida.getDestino(), corrida.getValorTotal());

        if (motorista.aceitarCorrida(corrida)) {
            System.out.println("Motorista aceitou a corrida.");
        } else {
            System.out.println("Motorista não conseguiu aceitar a corrida.");
            return;
        }

        corrida.iniciar();
        System.out.println("Status: " + corrida.getStatus());

        Pagamento pagamento = new Pagamento(500, corrida.getValorTotal());
        ProcessadorPagamento processador = new PagamentoPix(); // Pode trocar por PagamentoCartao ou PagamentoDinheiro.
        if (pagamento.processarPagamento(processador)) {
            corrida.finalizar(pagamento);
            System.out.println("Status final: " + corrida.getStatus());
            System.out.println("Pagamento confirmado: " + pagamento.isConfirmado());
            System.out.println("Corridas no histórico do passageiro: " + passageiro.getHistoricoCorridas().size());
        }
    }
}
