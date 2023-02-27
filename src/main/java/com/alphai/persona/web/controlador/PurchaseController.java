package com.alphai.persona.web.controlador;

import com.alphai.persona.dominio.Product;
import com.alphai.persona.dominio.Purchase;
import com.alphai.persona.dominio.servicio.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity<List<Purchase>> getAll(){
        System.out.println("Todos");
        //productService.getAll(),HttpStatus.OK
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId){
        return purchaseService.getByClient(clientId)
                .map( p -> ResponseEntity.ok(p))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
    }

}
