package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.model.entity.Cronograma;
import com.techcommunityperu.techcommunityperu.service.CronogramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cronograma")
@RequiredArgsConstructor
public class CronogramaController {

    private final CronogramaService cronogramaService;

    @PostMapping("/crear")
    public ResponseEntity<Cronograma> crearCronograma(@RequestBody Cronograma cronograma) {
        Cronograma nuevoCronograma = cronogramaService.save(cronograma);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCronograma);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Cronograma> obtenerCronogramaId(@PathVariable Integer id) {
        Cronograma cronograma = cronogramaService.findById(id);
        return ResponseEntity.ok(cronograma);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Cronograma> actualizarCronogramaId(@PathVariable Integer id, @RequestBody Cronograma cronogramaActualizado) {
        Cronograma cronograma = cronogramaService.update(id, cronogramaActualizado);
        return ResponseEntity.ok(cronograma);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCronogramaId(@PathVariable Integer id) {
        cronogramaService.delete(id);
        return ResponseEntity.ok("El cronograma con ID " + id + " ha sido eliminado exitosamente.");
    }

}
