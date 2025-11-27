package com.example.exemplo.Controller.novocontroler;

import com.example.exemplo.ModelNovo.*;
import com.example.exemplo.ModelNovo.Repository.*;
import com.example.exemplo.view.NotaFiscalForm;
import com.example.exemplo.view.NotaFiscalView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/notas-fiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<NotaFiscalView> gerar(@RequestBody NotaFiscalForm form) {
        Optional<Pedido> optPedido = pedidoRepository.findById(form.getIdPedido());
        if (optPedido.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Pedido pedido = optPedido.get();

        Optional<NotaFiscal> existente = notaFiscalRepository.findByPedido_Id(pedido.getId());
        if (existente.isPresent()) {
            return ResponseEntity.status(409).build();
        }

        NotaFiscal nf = new NotaFiscal();
        nf.setPedido(pedido);
        nf.setNumeroNota("NF-" + System.currentTimeMillis());
        nf.setDataEmissao(LocalDateTime.now());
        nf.setValorImpostos((pedido.getValorTotal() != null ? pedido.getValorTotal() : 0.0) * 0.10);
        nf.setChaveAcesso(UUID.randomUUID().toString());
        notaFiscalRepository.save(nf);

        NotaFiscalView view = new NotaFiscalView(
                nf.getId(),
                pedido.getId(),
                nf.getNumeroNota(),
                nf.getDataEmissao(),
                nf.getValorImpostos(),
                nf.getChaveAcesso()
        );
        return ResponseEntity.created(URI.create("/notas-fiscais/" + nf.getId())).body(view);
    }
}