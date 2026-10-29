package br.com.elym.model;

public class Corrida {
    private final int id;
    private final Passageiro passageiro;
    private Motorista motorista;
    private final String origem;
    private final String destino;
    private final double distanciaKm;
    private final double valorTotal;
    private StatusCorrida status;
    private Pagamento pagamento;

    public Corrida(int id, Passageiro passageiro, String origem, String destino, double distanciaKm) {
        if (distanciaKm <= 0) throw new IllegalArgumentException("A distância deve ser maior que zero.");
        this.id = id;
        this.passageiro = passageiro;
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.valorTotal = calcularValor(distanciaKm);
        this.status = StatusCorrida.SOLICITADA;
    }

    public double calcularValor(double distancia) {
        return 5.00 + (distancia * 2.50);
    }

    public void atribuirMotorista(Motorista motorista) {
        if (!motorista.isDisponivel()) throw new IllegalStateException("Motorista indisponível.");
        if (status != StatusCorrida.SOLICITADA) throw new IllegalStateException("Corrida não está disponível para aceite.");
        this.motorista = motorista;
        motorista.setDisponivel(false);
        status = StatusCorrida.ACEITA;
    }

    public void iniciar() {
        if (status != StatusCorrida.ACEITA) throw new IllegalStateException("A corrida precisa ser aceita antes de iniciar.");
        status = StatusCorrida.EM_ANDAMENTO;
    }

    public void finalizar(Pagamento pagamento) {
        if (status != StatusCorrida.EM_ANDAMENTO) throw new IllegalStateException("A corrida não está em andamento.");
        this.pagamento = pagamento;
        status = StatusCorrida.FINALIZADA;
        motorista.setDisponivel(true);
        passageiro.adicionarCorrida(this);
    }

    public int getId() { return id; }
    public Passageiro getPassageiro() { return passageiro; }
    public Motorista getMotorista() { return motorista; }
    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public double getDistanciaKm() { return distanciaKm; }
    public double getValorTotal() { return valorTotal; }
    public StatusCorrida getStatus() { return status; }
    public Pagamento getPagamento() { return pagamento; }
}
