package com.example.lab1gtics.controller;

import com.example.lab1gtics.model.Equipo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private static final List<Equipo> listaEquipos = new ArrayList<>();

    @GetMapping({"/", "/registro"})
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/equipos/registrar")
    public String registrarEquipo(
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam("codigoActivo") String codigoActivo,
            @RequestParam("fechaAdquisicion") String fechaAdquisicion,
            Model model) {

        Equipo equipo = new Equipo();

        equipo.setNombre(nombre);
        equipo.setTipo(tipo);
        equipo.setCodigoActivo(codigoActivo);
        equipo.setFechaAdquisicion(fechaAdquisicion);

        listaEquipos.add(equipo);

        model.addAttribute("equipos", listaEquipos);
        model.addAttribute("mensaje", "Equipo registrado correctamente.");

        return "listado";
    }

    @GetMapping("/equipos")
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", listaEquipos);
        return "listado";
    }

    @GetMapping("/equipos/buscar")
    public String buscarEquipo(
            @RequestParam("codigoActivo") String codigoActivo,
            Model model) {

        List<Equipo> resultado = new ArrayList<>();

        for (Equipo equipo : listaEquipos) {
            if (equipo.getCodigoActivo().equalsIgnoreCase(codigoActivo)) {
                resultado.add(equipo);
                break;
            }
        }

        model.addAttribute("equipos", resultado);
        model.addAttribute("codigoBuscado", codigoActivo);

        if (resultado.isEmpty()) {
            model.addAttribute(
                    "error",
                    "No se encontró un equipo con el código " + codigoActivo
            );
        } else {
            model.addAttribute("mensaje", "Equipo encontrado.");
        }

        return "listado";
    }

    @GetMapping("/equipos/{codigoActivo}")
    public String buscarPorRuta(
            @PathVariable("codigoActivo") String codigoActivo,
            Model model) {

        List<Equipo> resultado = new ArrayList<>();

        for (Equipo equipo : listaEquipos) {
            if (equipo.getCodigoActivo().equalsIgnoreCase(codigoActivo)) {
                resultado.add(equipo);
                break;
            }
        }

        model.addAttribute("equipos", resultado);
        model.addAttribute("codigoBuscado", codigoActivo);

        if (resultado.isEmpty()) {
            model.addAttribute(
                    "error",
                    "No se encontró un equipo con el código " + codigoActivo
            );
        } else {
            model.addAttribute("mensaje", "Equipo encontrado.");
        }

        return "listado";
    }
}
