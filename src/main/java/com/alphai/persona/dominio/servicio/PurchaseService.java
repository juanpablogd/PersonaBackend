package com.alphai.persona.dominio.servicio;

import com.alphai.persona.dominio.Product;
import com.alphai.persona.dominio.Purchase;
import com.alphai.persona.dominio.repositorio.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }
    public Optional<List<Purchase>> getByClient(String clientId){
        return  purchaseRepository.getByClient(clientId);
    }
    public  Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }
}
