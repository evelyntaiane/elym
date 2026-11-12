package br.com.elym.model;

import br.com.elym.pagamento.ProcessadorPagamento;

public class Pagamento {
    private final int id;
    private final double valor;
    private boolean confirmado;

    public Pagamento(int id, double valor) {
        this.id = id;
        this.valor = valor;
    }

    public boolean processarPagamento(ProcessadorPagamento processador) {
        confirmado = processador.processar(valor);
        return confirmado;
    }

    public int getId() { return id; }
    public double getValor() { return valor; }
    public boolean isConfirmado() { return confirmado; }
}
