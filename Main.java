package br.com.elym.app;

import br.com.elym.model.*;
import br.com.elym.pagamento.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("       ELYM | Mobilidade Urbana          ");
        System.out.println("=========================================\n");

        // 1. Criando Passageiro
        Passageiro passageiro = new Passageiro(1, "Mariana", "mariana@email.com", "83999990000", "1234");
        System.out.println("Passageiro cadastrado: " + passageiro.getNome());

        // 2. Criando Veículos usando Herança (Carro e Moto)
        Veiculo carro = new Carro("ABC1D23", "Fiat Argo", "Prata", 4);
        Veiculo moto = new Moto("XYZ9K88", "Honda CG 160", "Preta");

        // 3. Criando Motoristas
        Motorista motoristaCarro = new Motorista(2, "Carlos", "carlos@email.com", "83988880000", "abcd", "12345678900", carro);
        Motorista motoristaMoto = new Motorista(3, "Lucas", "lucas@email.com", "83977770000", "efgh", "98765432100", moto);

        System.out.println("Motorista 1: " + motoristaCarro.getNome() + " | " + motoristaCarro.getVeiculo().getDetalhes());
        System.out.println("Motorista 2: " + motoristaMoto.getNome() + " | " + motoristaMoto.getVeiculo().getDetalhes());
        System.out.println();

        // ---------------------------------------------------------
        // TESTE 1: Corrida de Moto + Pagamento via PIX
        // ---------------------------------------------------------
        System.out.println("--- TESTE 1: Corrida de Moto + Pagamento PIX ---");
        Corrida corrida1 = passageiro.solicitarCorrida(100, "Centro", "Universidade", 8.0);
        System.out.println("Data/Hora da Solicitação (LocalDateTime): " + corrida1.getDataHoraFormatada());
        System.out.printf("Corrida 1 solicitada: %s -> %s | Valor: R$ %.2f%n",
                corrida1.getOrigem(), corrida1.getDestino(), corrida1.getValorTotal());

        if (motoristaMoto.aceitarCorrida(corrida1)) {
            System.out.println("Motorista de moto (" + motoristaMoto.getNome() + ") aceitou a corrida.");
        }
        corrida1.iniciar();
        System.out.println("Status atual: " + corrida1.getStatus());

        Pagamento pagPix = new Pagamento(501, corrida1.getValorTotal());
        pagPix.processarPagamento(new PagamentoPix());
        corrida1.finalizar(pagPix);
        System.out.println("Status final: " + corrida1.getStatus());
        System.out.println("Pagamento confirmado: " + pagPix.isConfirmado() + "\n");

        // ---------------------------------------------------------
        // TESTE 2: Corrida de Carro + Pagamento via Cartão
        // ---------------------------------------------------------
        System.out.println("--- TESTE 2: Corrida de Carro + Pagamento Cartão ---");
        Corrida corrida2 = passageiro.solicitarCorrida(101, "Bairro A", "Bairro B", 12.0);
        System.out.println("Data/Hora da Solicitação: " + corrida2.getDataHoraFormatada());

        motoristaCarro.aceitarCorrida(corrida2);
        corrida2.iniciar();

        Pagamento pagCartao = new Pagamento(502, corrida2.getValorTotal());
        pagCartao.processarPagamento(new PagamentoCartao());
        corrida2.finalizar(pagCartao);
        System.out.println("Status final: " + corrida2.getStatus());
        System.out.println("Pagamento confirmado: " + pagCartao.isConfirmado() + "\n");

        // ---------------------------------------------------------
        // TESTE 3: Corrida de Carro + Pagamento em Dinheiro + Interface Cancelavel
        // ---------------------------------------------------------
        System.out.println("--- TESTE 3: Corrida de Carro + Pagamento Dinheiro ---");
        Corrida corrida3 = passageiro.solicitarCorrida(102, "Shopping", "Aeroporto", 20.0);
        System.out.println("Data/Hora da Solicitação: " + corrida3.getDataHoraFormatada());

        motoristaCarro.aceitarCorrida(corrida3);
        corrida3.iniciar();

        Pagamento pagDinheiro = new Pagamento(503, corrida3.getValorTotal());
        pagDinheiro.processarPagamento(new PagamentoDinheiro());
        corrida3.finalizar(pagDinheiro);
        System.out.println("Status final: " + corrida3.getStatus());
        System.out.println("Pagamento confirmado: " + pagDinheiro.isConfirmado() + "\n");

        // Resumo final
        System.out.println("=========================================");
        System.out.println("Total de corridas no histórico do passageiro: " + passageiro.getHistoricoCorridas().size());
        System.out.println("=========================================");
    }
}
