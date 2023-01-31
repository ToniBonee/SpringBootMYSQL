
package com.bone.API.service;

import com.bone.API.modelo.Producto;
import com.bone.API.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    private ProductRepository repository;

    public List<Producto>listarProductos(){
        return repository.findAll();
    }
    
    public void guardarProductos(Producto producto){
        repository.save(producto);
    }
    
    public Producto obtenerProductoPorId(Integer id){
        return repository.findById(id).get();
    }
    
    public void eliminarProductos(Integer id) {
        repository.deleteById(id);
    }
}
