package dev.patika.fifthhomework.controller;


import dev.patika.fifthhomework.dto.InstructorDTO;
import dev.patika.fifthhomework.mapper.InstructorMapper;
import dev.patika.fifthhomework.service.InstructorService;
import dev.patika.fifthhomework.utils.SalaryUpdateRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController implements BaseController<InstructorDTO> {
    private final InstructorService instructorService;
    private final InstructorMapper instructorMapper;
    private final SalaryUpdateRequestInfo salaryUpdateRequestInfo;

    @Autowired
    public InstructorController(InstructorService instructorService, InstructorMapper instructorMapper, SalaryUpdateRequestInfo salaryUpdateRequestInfo) {
        this.instructorService = instructorService;
        this.instructorMapper = instructorMapper;
        this.salaryUpdateRequestInfo=salaryUpdateRequestInfo;

    }

    @Override
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> findAll() {
        List<InstructorDTO> instructorDTOList=new ArrayList<>();
        instructorService.findAll().forEach(i->instructorDTOList.add(instructorMapper.toInstructorDTO(i)));
        return ResponseEntity.ok(instructorDTOList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(instructorMapper.toInstructorDTO(instructorService.findById(id)));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<InstructorDTO> save(@RequestBody InstructorDTO body) {
        return ResponseEntity.ok(instructorMapper.toInstructorDTO(instructorService.save(instructorMapper.toInstructor(body))));
    }

    @Override
    @PutMapping("")
    public ResponseEntity<InstructorDTO> update(@RequestBody InstructorDTO body) {
        return ResponseEntity.ok(instructorMapper.toInstructorDTO(instructorService.update(instructorMapper.toInstructor(body))));
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<InstructorDTO> updateSalary(@PathVariable int id, @RequestBody double percent){
        return ResponseEntity.ok(instructorMapper.toInstructorDTO(instructorService.updateSalary(id,percent)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<InstructorDTO> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(instructorMapper.toInstructorDTO(instructorService.deleteById(id)));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<InstructorDTO> deleteByName(@PathVariable String name){
        return ResponseEntity.ok(instructorMapper.toInstructorDTO(instructorService.deleteByName(name)));
    }

    @GetMapping("/get_highest_salary")
    public ResponseEntity<List<InstructorDTO>> getHighestSalaryInstructor(){
        List<InstructorDTO> list=new ArrayList<>();
        instructorService.getHighestSalaryInstructor().forEach(i->list.add(instructorMapper.toInstructorDTO(i)));
        return ResponseEntity.ok(list);
    }
}