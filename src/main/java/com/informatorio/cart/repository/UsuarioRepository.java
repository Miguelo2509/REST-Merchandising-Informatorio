package com.informatorio.cart.repository;

import com.informatorio.cart.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //List<Usuario> findByFechaDeCreacionAfter(LocalDate dateTime);

    //List<Usuario> findByFechaDeCreacionBetween(LocalDateTime desde, LocalDateTime hasta);

    //List<Usuario> findByNombreContainingAndApellidoContaining(String nombre, String apellido);

    List<Usuario> findByNombreStartingWith(String nombre);

    List<Usuario> findByCiudadStartingWith(String ciudad);

    List<Usuario> findByFechaDeCreacionAfter(LocalDate fechaDeCreacion);
}
