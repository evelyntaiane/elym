package br.com.elym.app;

import br.com.elym.model.*;
import br.com.elym.pagamento.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("       ELYM | Mobilidade Urbana          ");
        System.out.println("=========================================\n");

        Passageiro passageiro = new Passageiro(
                1, "Mariana", "mariana@email.com", "83999990000", "1234"
        );

        System.out.println("Passageiro cadastrado: " + passageiro.getNome());

        Veiculo carro = new Carro("ABC1D23", "Fiat Argo", "Prata", 4);
        Veiculo moto = new Moto("XYZ9K88", "Honda CG 160", "Preta", 160);

        Motorista motoristaCarro = new Motorista(
                2, "Carlos", "carlos@email.com", "83988880000",
                "abcd", "12345678900", carro
        );

        Motorista motoristaMoto = new Motorista(
                3, "Lucas", "lucas@email.com", "83977770000",
                "efgh", "98765432100", moto
        );

        System.out.println("Motorista 1: " + motoristaCarro.getNome()
                + " | " + motoristaCarro.getVeiculo().getDetalhes());

        System.out.println("Motorista 2: " + motoristaMoto.getNome()
                + " | " + motoristaMoto.getVeiculo().getDetalhes());

        System.out.println();

        // TESTE 1: Moto e pagamento via PIX

        System.out.println("--- TESTE 1: Corrida de Moto + Pagamento PIX ---");

        Corrida corrida1 = passageiro.solicitarCorrida(
                100, "Centro", "Universidade", 8.0
        );

        System.out.println("Data/Hora: " + corrida1.getDataHoraFormatada());
        System.out.printf("Valor da corrida: R$ %.2f%n", corrida1.getValorTotal());

        if (motoristaMoto.aceitarCorrida(corrida1)) {
            corrida1.iniciar();

            Pagamento pagPix = new Pagamento(501, corrida1.getValorTotal());
            pagPix.processarPagamento(new PagamentoPix());

            corrida1.finalizar(pagPix);

            System.out.println("Status final: " + corrida1.getStatus());
            System.out.println("Pagamento confirmado: " + pagPix.isConfirmado());
        }

        System.out.println();

        // TESTE 2: Carro e pagamento via cartão

        System.out.println("--- TESTE 2: Corrida de Carro + Pagamento Cartão ---");

        Corrida corrida2 = passageiro.solicitarCorrida(
                101, "Bairro A", "Bairro B", 12.0
        );

        System.out.println("Data/Hora: " + corrida2.getDataHoraFormatada());
        System.out.printf("Valor da corrida: R$ %.2f%n", corrida2.getValorTotal());

        if (motoristaCarro.aceitarCorrida(corrida2)) {
            corrida2.iniciar();

            Pagamento pagCartao = new Pagamento(502, corrida2.getValorTotal());
            pagCartao.processarPagamento(new PagamentoCartao());

            corrida2.finalizar(pagCartao);

            System.out.println("Status final: " + corrida2.getStatus());
            System.out.println("Pagamento confirmado: " + pagCartao.isConfirmado());
        }

        System.out.println();

        // TESTE 3: Carro e pagamento em dinheiro

        System.out.println("--- TESTE 3: Corrida de Carro + Pagamento Dinheiro ---");

        Corrida corrida3 = passageiro.solicitarCorrida(
                102, "Shopping", "Aeroporto", 20.0
        );

        System.out.println("Data/Hora: " + corrida3.getDataHoraFormatada());
        System.out.printf("Valor da corrida: R$ %.2f%n", corrida3.getValorTotal());

        if (motoristaCarro.aceitarCorrida(corrida3)) {
            corrida3.iniciar();

            Pagamento pagDinheiro = new Pagamento(503, corrida3.getValorTotal());
            pagDinheiro.processarPagamento(new PagamentoDinheiro());

            corrida3.finalizar(pagDinheiro);

            System.out.println("Status final: " + corrida3.getStatus());
            System.out.println("Pagamento confirmado: " + pagDinheiro.isConfirmado());
        }

        // Resumo final

        System.out.println("\n=========================================");
        System.out.println("Total de corridas no histórico: "
                + passageiro.getHistoricoCorridas().size());
        System.out.println("=========================================");
    }
}
