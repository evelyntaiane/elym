package br.com.elym.pagamento;

public class PagamentoPix implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor) {
        System.out.printf("Pagamento de R$ %.2f processado via Pix.%n", valor);
        return valor > 0;
    }
}
