package br.com.elym.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Corrida implements Cancelavel {
    private final int id;
    private final Passageiro passageiro;
    private Motorista motorista;
    private final String origem;
    private final String destino;
    private final double distanciaKm;
    private final double valorTotal;
    private StatusCorrida status;
    private Pagamento pagamento;
    private final LocalDateTime dataHoraSolicitacao;

    public Corrida(int id, Passageiro passageiro, String origem, String destino, double distanciaKm) {
        if (distanciaKm <= 0) throw new IllegalArgumentException("A distância deve ser maior que zero.");
        this.id = id;
        this.passageiro = passageiro;
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.valorTotal = calcularValor(distanciaKm);
        this.status = StatusCorrida.SOLICITADA;
        this.dataHoraSolicitacao = LocalDateTime.now();
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
        if (motorista != null) {
            motorista.setDisponivel(true);
        }
        passageiro.adicionarCorrida(this);
    }

    @Override
    public boolean cancelar() {
        if (status == StatusCorrida.EM_ANDAMENTO || status == StatusCorrida.FINALIZADA) {
            return false;
        }
        status = StatusCorrida.CANCELADA;
        if (motorista != null) {
            motorista.setDisponivel(true);
        }
        return true;
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
    public LocalDateTime getDataHoraSolicitacao() { return dataHoraSolicitacao; }

    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHoraSolicitacao.format(formatter);
    }
}
