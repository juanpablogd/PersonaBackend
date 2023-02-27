package com.alphai.persona.persistencia;

import java.util.List;
import java.util.Optional;

import com.alphai.persona.persistencia.crud.ProductoCrudRepositorio;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alphai.persona.dominio.Product;
import com.alphai.persona.dominio.repositorio.ProductRepository;
import com.alphai.persona.persistencia.entidad.Producto;
import com.alphai.persona.persistencia.mapper.ProductMapper;

@Repository
public class ProductoRepositorio implements ProductRepository {

	@Autowired
	private ProductoCrudRepositorio productoCrudRepositorio;
	@Autowired
	private ProductMapper mapper;
	
    @Override
    public List<Product> getAll() {
        List<Producto> productos = productoCrudRepositorio.findAll();
        return mapper.toProducts(productos);
    }
	
	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		List<Producto> productos = productoCrudRepositorio.findByIdCategoriaOrderByNombreAsc(categoryId);
		return Optional.of(mapper.toProducts(productos));
	}
	
	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		Optional<List<Producto>> productos  = productoCrudRepositorio.findByCantidadStockLessThanAndEstado(quantity, true);
		return productos.map(prods -> mapper.toProducts(prods));
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		return productoCrudRepositorio.findById(productId).map(producto -> mapper.toProduct(producto));
	}
	
	@Override
    public Product save(Product product) {
    	Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepositorio.save(producto));
    }
	

	public List<Producto> getByCategoria(int idCategoria){
		return productoCrudRepositorio.findByIdCategoriaOrderByNombreAsc(idCategoria);
	}
	
	public Optional<List<Producto>> getEscasos(int cantidad){
		return productoCrudRepositorio.findByCantidadStockLessThanAndEstado(cantidad, true);
	}
	
	public Optional<Producto> getProducto(int idProducto) {
        return productoCrudRepositorio.findById(idProducto);
    }

    public Producto save(Producto producto) {
        return productoCrudRepositorio.save(producto);
    }

    public void delete(int idProducto) {
    	productoCrudRepositorio.deleteById(idProducto);
    }

}
