package com.alphai.persona.persistencia;

import com.alphai.persona.dominio.Purchase;
import com.alphai.persona.dominio.repositorio.PurchaseRepository;
import com.alphai.persona.persistencia.crud.CompraCrudRepositorio;
import com.alphai.persona.persistencia.entidad.Compra;
import com.alphai.persona.persistencia.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepositorio implements PurchaseRepository {
    @Autowired
    private CompraCrudRepositorio compraCrudRepositorio;
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        List<Compra> ls_compra = compraCrudRepositorio.findAll();
        return mapper.toPurchases(ls_compra);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepositorio.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra miCompra = mapper.toCompra(purchase);
        miCompra.getProductos().forEach(producto -> producto.setCompra(miCompra));
        return mapper.toPurchase(compraCrudRepositorio.save(miCompra));
    }
}
