package br.com.elym.pagamento;

public class PagamentoCartao implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor) {
        if (valor <= 0) {
            return false;
        }

        System.out.printf("Pagamento de R$ %.2f processado via cartão.%n", valor);
        return true;
    }
}
