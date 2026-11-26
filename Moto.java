package br.com.elym.model;

public class Moto extends Veiculo {
    private int cilindradas;

    public Moto(String placa, String modelo, String cor, int cilindradas) {
        super(placa, modelo, cor);

        if (cilindradas <= 0) {
            throw new IllegalArgumentException("As cilindradas devem ser maiores que zero.");
        }

        this.cilindradas = cilindradas;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        if (cilindradas <= 0) {
            throw new IllegalArgumentException("As cilindradas devem ser maiores que zero.");
        }

        this.cilindradas = cilindradas;
    }

    @Override
    public String getDetalhes() {
        return super.getDetalhes() + ", " + cilindradas + " cilindradas";
    }
}
