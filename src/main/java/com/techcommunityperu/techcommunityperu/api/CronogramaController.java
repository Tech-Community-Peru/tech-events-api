package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.CronogramaDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Cronograma;
import com.techcommunityperu.techcommunityperu.service.CronogramaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cronograma")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
public class CronogramaController {

    private final CronogramaService cronogramaService;
    private final ModelMapper modelMapper;

    @PostMapping("/crear")
    public ResponseEntity<CronogramaDTO> crearCronograma(@RequestBody CronogramaDTO cronogramaDTO) {
        Cronograma cronograma = modelMapper.map(cronogramaDTO, Cronograma.class);
        Cronograma nuevoCronograma = cronogramaService.save(cronograma);
        CronogramaDTO responseDTO = modelMapper.map(nuevoCronograma, CronogramaDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<CronogramaDTO> obtenerCronogramaId(@PathVariable Integer id) {
        Cronograma cronograma = cronogramaService.findById(id);
        CronogramaDTO responseDTO = modelMapper.map(cronograma, CronogramaDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CronogramaDTO> actualizarCronogramaId(@PathVariable Integer id, @RequestBody CronogramaDTO cronogramaDTO) {
        Cronograma cronogramaActualizado = modelMapper.map(cronogramaDTO, Cronograma.class);
        Cronograma cronograma = cronogramaService.update(id, cronogramaActualizado);
        CronogramaDTO responseDTO = modelMapper.map(cronograma, CronogramaDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCronogramaId(@PathVariable Integer id) {
        cronogramaService.delete(id);
        return ResponseEntity.ok("El cronograma con ID " + id + " ha sido eliminado exitosamente.");
    }
}
