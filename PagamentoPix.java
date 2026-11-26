package br.com.elym.pagamento;

public class PagamentoPix implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor) {
        if (valor <= 0) {
            return false;
        }

        System.out.printf("Pagamento de R$ %.2f processado via Pix.%n", valor);
        return true;
    }
}
