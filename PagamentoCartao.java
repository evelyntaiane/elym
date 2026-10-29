package br.com.elym.pagamento;

public class PagamentoCartao implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor) {
        System.out.printf("Pagamento de R$ %.2f processado via cartão.%n", valor);
        return valor > 0;
    }
}
