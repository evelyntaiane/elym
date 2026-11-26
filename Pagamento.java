package br.com.elym.model;

import br.com.elym.pagamento.ProcessadorPagamento;

public class Pagamento {
    private final int id;
    private final double valor;
    private boolean confirmado;

    public Pagamento(int id, double valor) {
        if (id <= 0 || valor <= 0) {
            throw new IllegalArgumentException("Dados de pagamento inválidos.");
        }

        this.id = id;
        this.valor = valor;
        this.confirmado = false;
    }

    public boolean processarPagamento(ProcessadorPagamento processador) {
        if (processador == null) {
            throw new IllegalArgumentException("O processador não pode ser nulo.");
        }

        confirmado = processador.processar(valor);
        return confirmado;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public boolean isConfirmado() {
        return confirmado;
    }
}
