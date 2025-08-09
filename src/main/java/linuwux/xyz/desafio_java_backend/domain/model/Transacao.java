package linuwux.xyz.desafio_java_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class Transacao {
    @JsonProperty("valor")
    private final double valor;
    @JsonProperty("dataHora")
    private final OffsetDateTime dataHora;

    public Transacao(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Valor: %s, Data: %s".formatted(valor, dataHora);
    }
}
