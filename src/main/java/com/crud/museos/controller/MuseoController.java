package com.crud.museos.controller;

import com.crud.museos.model.Museo;
import com.crud.museos.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/museos")
public class MuseoController {

    @Autowired
    private FirebaseService firebaseService;

    // Crear nuevo museo
    @PostMapping
    public ResponseEntity<String> agregarMuseo(@RequestBody Museo museo) {
        String id = firebaseService.guardarMuseo(museo);
        return ResponseEntity.ok(id);
    }

    // Obtener todos los museos
    @GetMapping
    public ResponseEntity<List<Museo>> obtenerMuseos() {
        List<Museo> museos = firebaseService.obtenerMuseos();
        return ResponseEntity.ok(museos);
    }

    // Obtener museo por id
    @GetMapping("/{id}")
    public ResponseEntity<Museo> obtenerMuseo(@PathVariable String id) {
        Museo museo = firebaseService.obtenerMuseoPorId(id);
        if (museo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(museo);
    }

    // Actualizar museo
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarMuseo(@PathVariable String id, @RequestBody Museo museo) {
        museo.setId(id);
        firebaseService.guardarMuseo(museo);
        return ResponseEntity.ok("Museo actualizado");
    }

    // Eliminar museo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMuseo(@PathVariable String id) {
        firebaseService.eliminarMuseo(id);
        return ResponseEntity.ok("Museo eliminado");
    }
}
