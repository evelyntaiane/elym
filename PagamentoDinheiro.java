package br.com.elym.pagamento;

public class PagamentoDinheiro implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor) {
        System.out.printf("Pagamento de R$ %.2f confirmado em dinheiro pelo motorista.%n", valor);
        return valor > 0;
    }
}
