package dev.patika.fifthhomework.controller;

import dev.patika.fifthhomework.dto.SalaryUpdateLogDTO;
import dev.patika.fifthhomework.mapper.SalaryUpdateLogMapper;
import dev.patika.fifthhomework.service.SalaryUpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/salary_update_logs")
public class SalaryUpdateLogController {
    private final SalaryUpdateLogService service;
    private final SalaryUpdateLogMapper mapper;

    @Autowired
    public SalaryUpdateLogController(SalaryUpdateLogService service, SalaryUpdateLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<SalaryUpdateLogDTO>> findAll(){
        List<SalaryUpdateLogDTO> salaryUpdateLogDTOList=new ArrayList<>();
        service.findAll().forEach(s->salaryUpdateLogDTOList.add(mapper.toSalaryUpdateLogDTO(s)));
        return ResponseEntity.ok(salaryUpdateLogDTOList);
    }

    @GetMapping("/instructor_id/{id}")
    public ResponseEntity<List<SalaryUpdateLogDTO>> findByInstructorId(@PathVariable int id){
        //List<SalaryUpdateLogDTO> salaryUpdateLogDTOList=new ArrayList<>();
        //service.findAll().forEach(s->salaryUpdateLogDTOList.add(mapper.toSalaryUpdateLogDTO(s)));
        return ResponseEntity.ok(mapper.salaryUpdateLogDTOList(service.findByInstructorId(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryUpdateLogDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(mapper.toSalaryUpdateLogDTO(service.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SalaryUpdateLogDTO> deleteById(@PathVariable int id){
        return ResponseEntity.ok(mapper.toSalaryUpdateLogDTO(service.deleteById(id)));
    }

}
