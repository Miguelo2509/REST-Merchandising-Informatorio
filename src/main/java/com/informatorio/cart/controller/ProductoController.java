package com.informatorio.cart.controller;

import com.informatorio.cart.domain.Producto;
import com.informatorio.cart.domain.Usuario;
import com.informatorio.cart.exception.CarritoException;
import com.informatorio.cart.repository.ProductoRepository;
import com.informatorio.cart.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto) {
        return new ResponseEntity<>(productoRepository.save(producto), HttpStatus.CREATED);
    }
    //throws CarritoException



    //@GetMapping
    //public ResponseEntity<?> buscarProducto(@RequestParam("comienzaCon") String comienzaCon) throws CarritoException{
    //    return new ResponseEntity<>(productoRepository.buscarPorNombreQueComienza(comienzaCon), HttpStatus.CREATED);
    //}

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(productoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public Producto getProductoPorId(@PathVariable("id") Long id) {
        return productoRepository.findById(id).get();
    }

    @DeleteMapping(value = "{id}")
    public String deleteProductoPorId(@PathVariable("id") Long id) { productoRepository.deleteById(id); return null; }

    @PutMapping(value = "{id}")
    public Producto modificarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(id).get();
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setContenido(producto.getContenido());
        productoExistente.setPublicado(producto.getPublicado());
        productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
        return productoRepository.save(productoExistente);
    }

}
