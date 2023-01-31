package com.bone.API.controller;

import com.bone.API.modelo.Producto;
import com.bone.API.service.ProductoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/producto")
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id) {
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/producto")
    public void nuevoProducto(@RequestBody Producto producto) {
        productoService.guardarProductos(producto);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto producto, @PathVariable Integer id) {
        try {
            Producto productoExistente = productoService.obtenerProductoPorId(id);
            productoExistente.setName(producto.getName());
            productoExistente.setPrice(producto.getPrice());
            productoService.guardarProductos(productoExistente);
            return new ResponseEntity<Producto>(producto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/producto/{id}")
    public void borrarProducto (@PathVariable Integer id){
        productoService.eliminarProductos(id);
    }
}
