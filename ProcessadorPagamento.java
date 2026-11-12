package br.com.elym.pagamento;

public interface ProcessadorPagamento {
    boolean processar(double valor);
}
