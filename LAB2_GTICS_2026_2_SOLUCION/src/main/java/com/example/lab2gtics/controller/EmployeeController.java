package com.example.lab2gtics.controller;

import com.example.lab2gtics.entity.Employee;
import com.example.lab2gtics.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository) { this.employeeRepository = employeeRepository; }

    @GetMapping({"/", "/employees"})
    public String listarEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees/list";
    }

    @GetMapping("/employees/ver")
    public String verEmployee(@RequestParam("id") Integer id, Model model) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            model.addAttribute("employee", employeeOptional.get());
            return "employees/detail";
        }
        return "redirect:/employees";
    }

    @GetMapping("/employees/buscar")
    public String buscarEmployees(
            @RequestParam(name = "texto", required = false) String texto,
            @RequestParam(name = "puesto", required = false) String puesto,
            @RequestParam(name = "salarioMin", required = false) BigDecimal salarioMin,
            Model model) {

        List<Employee> employees = employeeRepository.findAll();
        List<Employee> resultado = new ArrayList<>();
        String textoFiltro = texto == null ? "" : texto.trim().toLowerCase();
        String puestoFiltro = puesto == null ? "" : puesto.trim().toLowerCase();

        for (Employee employee : employees) {
            boolean coincideTexto = true;
            boolean coincidePuesto = true;
            boolean coincideSalario = true;

            if (!textoFiltro.isEmpty()) {
                String firstName = employee.getFirstName() == null ? "" : employee.getFirstName().toLowerCase();
                String lastName = employee.getLastName() == null ? "" : employee.getLastName().toLowerCase();
                String email = employee.getEmail() == null ? "" : employee.getEmail().toLowerCase();
                coincideTexto = firstName.contains(textoFiltro) || lastName.contains(textoFiltro) || email.contains(textoFiltro);
            }
            if (!puestoFiltro.isEmpty()) {
                String jobId = employee.getJobId() == null ? "" : employee.getJobId().toLowerCase();
                coincidePuesto = jobId.equals(puestoFiltro);
            }
            if (salarioMin != null) {
                coincideSalario = employee.getSalary() != null && employee.getSalary().compareTo(salarioMin) >= 0;
            }
            if (coincideTexto && coincidePuesto && coincideSalario) resultado.add(employee);
        }

        model.addAttribute("employees", resultado);
        model.addAttribute("texto", texto);
        model.addAttribute("puesto", puesto);
        model.addAttribute("salarioMin", salarioMin);
        return "employees/list";
    }

    @GetMapping("/employees/nuevo")
    public String nuevoEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new";
    }

    @PostMapping("/employees/guardar")
    public String guardarEmployee(Employee employee) {
        if (employee.getEmployeeId() == null) {
            Integer nuevoId = 1;
            List<Employee> employees = employeeRepository.findAll();
            for (Employee e : employees) {
                if (e.getEmployeeId() != null && e.getEmployeeId() >= nuevoId) nuevoId = e.getEmployeeId() + 1;
            }
            employee.setEmployeeId(nuevoId);
        }
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/editar")
    public String editarEmployee(@RequestParam("id") Integer id, Model model) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            model.addAttribute("employee", employeeOptional.get());
            return "employees/edit";
        }
        return "redirect:/employees";
    }
}
