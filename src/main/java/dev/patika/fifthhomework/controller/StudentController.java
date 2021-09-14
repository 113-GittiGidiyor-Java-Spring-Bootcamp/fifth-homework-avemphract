package dev.patika.fourthhomeworkavemphract.controller;

import dev.patika.fourthhomeworkavemphract.dto.StudentDTO;
import dev.patika.fourthhomeworkavemphract.mapper.StudentMapper;
import dev.patika.fourthhomeworkavemphract.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController implements BaseController<StudentDTO> {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> studentDTOList=new ArrayList<>();
        studentService.findAll().forEach(s->studentDTOList.add(studentMapper.studentToStudentDTO(s)));
        return ResponseEntity.ok(studentDTOList);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(studentMapper.studentToStudentDTO(studentService.findById(id)));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO body) {
        return ResponseEntity.ok(studentMapper.studentToStudentDTO(studentService.save(body)));
    }

    @Override
    @PutMapping("")
    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO body) {
        return ResponseEntity.ok(studentMapper.studentToStudentDTO(studentService.update(body)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDTO> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(studentMapper.studentToStudentDTO(studentService.deleteById(id)));
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<StudentDTO> deleteByName(@PathVariable String name){
        return ResponseEntity.ok(studentMapper.studentToStudentDTO(studentService.deleteByName(name)));
    }

    @GetMapping("/group_by_address")
    public ResponseEntity<?> groupByAddress(){
        return ResponseEntity.ok(studentService.groupByAddress());
    }

    @GetMapping("/group_by_gender")
    public ResponseEntity<?> groupByGender(){
        return ResponseEntity.ok(studentService.groupByGender());
    }
}
