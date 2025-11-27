package com.example.exemplo.Controller.novocontroler;

import com.example.exemplo.ModelNovo.*;
import com.example.exemplo.ModelNovo.Repository.*;
import com.example.exemplo.view.PedidoForm;
import com.example.exemplo.view.ItemPedidoForm;
import com.example.exemplo.view.PedidoView;
import com.example.exemplo.view.ItemPedidoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoView> criar(@RequestBody PedidoForm form) {
        Optional<Cliente> optCliente = clienteRepository.findById(form.getIdCliente());
        Optional<Funcionario> optFuncionario = funcionarioRepository.findById(form.getIdFuncionario());
        if (optCliente.isEmpty() || optFuncionario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (form.getItens() == null || form.getItens().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(optCliente.get());
        pedido.setFuncionario(optFuncionario.get());
        pedido.setStatusPedido("ABERTO");
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setValorTotal(0.0);
        pedidoRepository.save(pedido);

        List<ItemPedidoView> itensView = new ArrayList<>();
        double total = 0.0;

        for (ItemPedidoForm itemForm : form.getItens()) {
            String sku = itemForm.getSku() != null ? itemForm.getSku() : itemForm.getSkuProduto();
            if (sku == null || sku.isEmpty() || itemForm.getQuantidade() == null || itemForm.getQuantidade() <= 0) {
                return ResponseEntity.badRequest().build();
            }
            Optional<Produto> optProduto = produtoRepository.findById(sku);
            if (optProduto.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Produto produto = optProduto.get();
            if (produto.getPrecoProduto() == null || produto.getPrecoProduto() < 0) {
                return ResponseEntity.badRequest().build();
            }

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemForm.getQuantidade());
            item.setPrecoUnitario(produto.getPrecoProduto());
            item.setSubtotal(item.getQuantidade() * item.getPrecoUnitario());
            itemPedidoRepository.save(item);

            total += item.getSubtotal();
            itensView.add(new ItemPedidoView(produto.getSku(), item.getQuantidade(), item.getPrecoUnitario(), item.getSubtotal()));
        }

        pedido.setValorTotal(total);
        pedidoRepository.save(pedido);

        PedidoView view = new PedidoView(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getFuncionario().getId(),
                pedido.getStatusPedido(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                itensView
        );

        return ResponseEntity.created(URI.create("/pedidos/" + pedido.getId())).body(view);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoView> read(@PathVariable Integer id) {
        Optional<Pedido> opt = pedidoRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = opt.get();
        List<ItemPedido> itens = itemPedidoRepository.findByPedido_Id(pedido.getId());
        List<ItemPedidoView> itensView = new ArrayList<>();
        for (ItemPedido ip : itens) {
            itensView.add(new ItemPedidoView(ip.getProduto().getSku(), ip.getQuantidade(), ip.getPrecoUnitario(), ip.getSubtotal()));
        }
        PedidoView view = new PedidoView(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getFuncionario().getId(),
                pedido.getStatusPedido(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                itensView
        );
        return ResponseEntity.ok(view);
    }
}