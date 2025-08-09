package linuwux.xyz.desafio_java_backend.controller;

import linuwux.xyz.desafio_java_backend.domain.model.Estatistica;
import linuwux.xyz.desafio_java_backend.domain.model.Transacao;
import linuwux.xyz.desafio_java_backend.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class TransacaoController {
    TransacaoService transacaoService = new TransacaoService();
    @PostMapping("/transacao")
    public ResponseEntity receberTransacoes(@RequestBody Transacao transacaoParaCadastrar) {
        /*
            2 - A transação NÃO DEVE acontecer no futuro
            3 - A transação DEVE ter acontecido a qualquer momento no passado
            4 - A transação NÃO DEVE ter valor negativo
            5 - A transação DEVE ter valor igual ou maior que 0 (zero)

        * */
        try {
            if (
                    transacaoParaCadastrar.getDataHora().isAfter(OffsetDateTime.now()) ||
                            transacaoParaCadastrar.getValor() < 0
            ) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

            } else {
                transacaoService.adicionarTransacao(transacaoParaCadastrar);
                return ResponseEntity.status(HttpStatus.CREATED).build();

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @GetMapping("/todasTransacoes")
//    public ResponseEntity<String> verTransacoes() {
//        return ResponseEntity.ok(transacaoService.toString());
//    }

//    @GetMapping("/testeTransacoes")
//    public ResponseEntity<List<Transacao>> verultminTransacoes() {
//        return ResponseEntity.ok(transacaoService.getListaUltimoMinuto());
//    }


    @DeleteMapping("/transacao")
    public ResponseEntity limparTransacoes() {
        transacaoService.limparTodasTransacoes();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<Estatistica> enviarEstatisticas() {
        List<Transacao> ultimoMinuto = transacaoService.getListaUltimoMinuto();
        int count = ultimoMinuto.size();
        if(count == 0) {
            return ResponseEntity.ok(new Estatistica(0, 0, 0, 0, 0));
        }
        double sum = ultimoMinuto.stream().mapToDouble(Transacao::getValor).sum();
        double avg = sum/count;
        double min = ultimoMinuto.stream().mapToDouble(Transacao::getValor).min().orElse(0);
        double max = ultimoMinuto.stream().mapToDouble(Transacao::getValor).max().orElse(0);
        return ResponseEntity.ok(new Estatistica(count, sum, avg, min, max));

    }
}
