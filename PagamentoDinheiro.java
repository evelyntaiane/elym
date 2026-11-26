package br.com.elym.pagamento;

public class PagamentoDinheiro implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor) {
        if (valor <= 0) {
            return false;
        }

        System.out.printf("Pagamento de R$ %.2f confirmado em dinheiro pelo motorista.%n", valor);
        return true;
    }
}
