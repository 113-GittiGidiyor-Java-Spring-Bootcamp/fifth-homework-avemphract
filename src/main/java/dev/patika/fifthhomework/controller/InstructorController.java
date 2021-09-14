package dev.patika.fourthhomeworkavemphract.controller;

import dev.patika.fourthhomeworkavemphract.dto.InstructorDTO;
import dev.patika.fourthhomeworkavemphract.mapper.InstructorMapper;
import dev.patika.fourthhomeworkavemphract.service.InstructorService;
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

    @Autowired
    public InstructorController(InstructorService instructorService, InstructorMapper instructorMapper) {
        this.instructorService = instructorService;
        this.instructorMapper = instructorMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> findAll() {
        List<InstructorDTO> instructorDTOList=new ArrayList<>();
        instructorService.findAll().forEach(i->instructorDTOList.add(instructorMapper.instructorToInstructorDTO(i)));
        return ResponseEntity.ok(instructorDTOList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(instructorMapper.instructorToInstructorDTO(instructorService.findById(id)));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<InstructorDTO> save(@RequestBody InstructorDTO body) {
        return ResponseEntity.ok(instructorMapper.instructorToInstructorDTO(instructorService.save(body)));
    }

    @Override
    @PutMapping("")
    public ResponseEntity<InstructorDTO> update(@RequestBody InstructorDTO body) {
        return ResponseEntity.ok(instructorMapper.instructorToInstructorDTO(instructorService.update(body)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<InstructorDTO> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(instructorMapper.instructorToInstructorDTO(instructorService.deleteById(id)));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<InstructorDTO> deleteByName(@PathVariable String name){
        return ResponseEntity.ok(instructorMapper.instructorToInstructorDTO(instructorService.deleteByName(name)));
    }

    @GetMapping("/get_highest_salary")
    public ResponseEntity<List<InstructorDTO>> getHighestSalaryInstructor(){
        List<InstructorDTO> list=new ArrayList<>();
        instructorService.getHighestSalaryInstructor().forEach(i->list.add(instructorMapper.instructorToInstructorDTO(i)));
        return ResponseEntity.ok(list);
    }
}