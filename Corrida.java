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

    public Corrida(int id, Passageiro passageiro, String origem,
                   String destino, double distanciaKm) {

        if (id <= 0 || passageiro == null || distanciaKm <= 0
                || origem == null || origem.trim().isEmpty()
                || destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("Dados da corrida inválidos.");
        }

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
        if (distancia <= 0) {
            throw new IllegalArgumentException("A distância deve ser maior que zero.");
        }

        return 5.00 + (distancia * 2.50);
    }

    public void atribuirMotorista(Motorista motorista) {
        if (motorista == null) {
            throw new IllegalArgumentException("Motorista inválido.");
        }

        if (status != StatusCorrida.SOLICITADA) {
            throw new IllegalStateException("Corrida não está disponível para aceite.");
        }

        if (!motorista.isDisponivel()) {
            throw new IllegalStateException("Motorista indisponível.");
        }

        this.motorista = motorista;
        motorista.setDisponivel(false);
        status = StatusCorrida.ACEITA;
    }

    public void iniciar() {
        if (status != StatusCorrida.ACEITA) {
            throw new IllegalStateException("A corrida precisa ser aceita antes de iniciar.");
        }

        status = StatusCorrida.EM_ANDAMENTO;
    }

    public void finalizar(Pagamento pagamento) {
        if (status != StatusCorrida.EM_ANDAMENTO) {
            throw new IllegalStateException("A corrida não está em andamento.");
        }

        if (pagamento == null || !pagamento.isConfirmado()) {
            throw new IllegalStateException("O pagamento precisa ser confirmado.");
        }

        this.pagamento = pagamento;
        status = StatusCorrida.FINALIZADA;

        if (motorista != null) {
            motorista.setDisponivel(true);
        }

        passageiro.adicionarCorrida(this);
    }

    @Override
    public boolean cancelar() {
        if (status == StatusCorrida.EM_ANDAMENTO
                || status == StatusCorrida.FINALIZADA
                || status == StatusCorrida.CANCELADA) {
            return false;
        }

        status = StatusCorrida.CANCELADA;

        if (motorista != null) {
            motorista.setDisponivel(true);
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public StatusCorrida getStatus() {
        return status;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public LocalDateTime getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public String getDataHoraFormatada() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return dataHoraSolicitacao.format(formatter);
    }
}
