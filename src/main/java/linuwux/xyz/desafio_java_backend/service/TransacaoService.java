package linuwux.xyz.desafio_java_backend.service;

import linuwux.xyz.desafio_java_backend.domain.model.Transacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
    private List<Transacao> listaTransacoes;
    public TransacaoService () {
        listaTransacoes = new ArrayList<>();
    }
    public void adicionarTransacao(Transacao transacao) {
        listaTransacoes.add(transacao);
    }

    @Override
    public String toString() {

        return String.join("", listaTransacoes.toString());
    }
}
