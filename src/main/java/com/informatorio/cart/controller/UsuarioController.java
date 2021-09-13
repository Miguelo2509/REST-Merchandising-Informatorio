package com.informatorio.cart.controller;

import com.informatorio.cart.domain.Usuario;
import com.informatorio.cart.repository.UsuarioRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id).get();
    }

    @DeleteMapping(value = "{id}")
    public String deleteUsuarioPorId(@PathVariable("id") Long id) { usuarioRepository.deleteById(id); return null; }

    //Falta cambiar el pasword que no tiene GETTER
    //@RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @PutMapping(value = "{id}")
    public Usuario modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setCiudad(usuario.getCiudad());
        usuarioExistente.setProvincia(usuario.getCiudad());
        usuarioExistente.setPais(usuario.getPais());
        return usuarioRepository.save(usuarioExistente);
    }

    //@GetMapping
    //public ResponseEntity<?> buscarUsuario(@RequestParam("ciudad") String ciudad) {
    //    return new ResponseEntity<>(usuarioRepository.buscarPorCiudad(ciudad), HttpStatus.CREATED);
    //}

    @GetMapping(value = "nombre/{nombre}")
    public List<Usuario> buscarPorNombre(@PathVariable("nombre") String nombre){
        return usuarioRepository.findByNombreStartingWith(nombre);
    }

    @GetMapping(value = "ciudad/{ciudad}")
    public List<Usuario> buscarPorCiudad(@PathVariable("ciudad") String ciudad){
        return usuarioRepository.findByCiudadStartingWith(ciudad);
    }

    @GetMapping(value = "fechaDeCreacion/{fechaDeCreacion}")
    public List<Usuario> buscarPorFechaDeCreacion(@PathVariable("fechaDeCreacion") LocalDate fechaDeCreacion){
        return usuarioRepository.findByFechaDeCreacionAfter(fechaDeCreacion);
    }

    /*
    @GetMapping(value = "fechaDeCreacion/{fechaDeCreacion}")
    public ResponseEntity<?> buscarUsuariosCreadosDespuesDeFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion) {
      List<Usuario> usuario = usuarioRepository.findByFechaDeCreacionAfter(fechaDeCreacion);
      return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    */




    /*
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosUsuarios(
            @RequestParam(name = "fechaDeCreacion", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido", required = false) String apellido {
        if (fechaDeCreacion != null) {
            return new ResponseEntity<>(usuarioRepository.findByFechaDeCreacionAfter(fechaDeCreacion.atStartOfDay()), HttpStatus.OK);
        } else if (Objects.nonNull(nombre) && Objects.nonNull(apellido)) {
            return new ResponseEntity<>(usuarioRepository.findByNombreContainingAndApellidoContaining(nombre, apellido), HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }
    */

}
