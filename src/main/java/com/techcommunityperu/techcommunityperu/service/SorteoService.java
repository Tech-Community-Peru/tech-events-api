package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.CrearSorteoDTO;
import com.techcommunityperu.techcommunityperu.dto.GanadorDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Sorteo;

import java.util.List;

public interface SorteoService {
    String crearSorteo(CrearSorteoDTO crearSorteoDTO);
    String realizarSorteo(Integer sorteoId);
}