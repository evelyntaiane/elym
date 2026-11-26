package br.com.elym.model;

public class Carro extends Veiculo {
    private int quantidadePortas;

    public Carro(String placa, String modelo, String cor, int quantidadePortas) {
        super(placa, modelo, cor);

        if (quantidadePortas <= 0) {
            throw new IllegalArgumentException("A quantidade de portas deve ser maior que zero.");
        }

        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        if (quantidadePortas <= 0) {
            throw new IllegalArgumentException("A quantidade de portas deve ser maior que zero.");
        }

        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public String getDetalhes() {
        return super.getDetalhes() + ", " + quantidadePortas + " portas";
    }
}
