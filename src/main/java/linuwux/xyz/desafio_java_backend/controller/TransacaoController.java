package linuwux.xyz.desafio_java_backend.controller;

import linuwux.xyz.desafio_java_backend.domain.model.Transacao;
import linuwux.xyz.desafio_java_backend.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

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

    @GetMapping("todasTransacoes")
    public ResponseEntity<String> verTransacoes() {

        return ResponseEntity.ok(transacaoService.toString());
    }
}
