package com.example.clinicaveterinaria.controller;

import com.example.clinicaveterinaria.entity.Mascota;
import com.example.clinicaveterinaria.repository.MascotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaRepository mascotaRepository;

    public MascotaController(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("listaMascotas", mascotaRepository.findAll());
        return "mascotas/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "mascotas/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(Mascota mascota) {
        mascotaRepository.save(mascota);
        return "redirect:/mascotas";
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
        }
        return "redirect:/mascotas";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Long id, Model model) {
        Optional<Mascota> mascotaOpt = mascotaRepository.findById(id);
        if (mascotaOpt.isEmpty()) {
            return "redirect:/mascotas";
        }
        model.addAttribute("mascota", mascotaOpt.get());
        return "mascotas/editar";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("criterio") String criterio,
                         @RequestParam("valor") String valor,
                         Model model) {
        List<Mascota> lista;

        switch (criterio) {
            case "nombre" -> lista = mascotaRepository.findByNombreContainingIgnoreCase(valor);
            case "especie" -> lista = mascotaRepository.findByEspecieContainingIgnoreCase(valor);
            case "estado" -> {
                Boolean estado = valor.equalsIgnoreCase("activo") || valor.equals("1") || valor.equalsIgnoreCase("true");
                lista = mascotaRepository.findByEstado(estado);
            }
            default -> lista = mascotaRepository.findAll();
        }

        model.addAttribute("listaMascotas", lista);
        return "mascotas/lista";
    }

    // TODO P5: crear el POST de actualización usando la consulta personalizada del repository.

    @GetMapping("/reporte")
    public String reporte(Model model) {
        // TODO P6: obtener MAX, MIN, AVG y COUNT desde consultas personalizadas del repository.
        return "mascotas/reporte";
    }
}
