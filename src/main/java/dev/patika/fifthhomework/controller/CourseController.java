package dev.patika.fifthhomework.controller;

import dev.patika.fifthhomework.dto.CourseDTO;
import dev.patika.fifthhomework.mapper.CourseMapper;
import dev.patika.fifthhomework.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController implements BaseController<CourseDTO> {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper=courseMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() {
        List<CourseDTO> courseDTOList=new ArrayList<>();
        courseService.findAll().forEach(c->courseDTOList.add(courseMapper.toCourseDTO(c)));
        return ResponseEntity.ok(courseDTOList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(courseMapper.toCourseDTO(courseService.findById(id)));
    }

    @GetMapping("/course_code/{code}")
    public ResponseEntity<CourseDTO> findByCode(@PathVariable String code){
        return ResponseEntity.ok(courseMapper.toCourseDTO(courseService.findByCode(code)));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<CourseDTO> save(@RequestBody CourseDTO body) {
        return ResponseEntity.ok(courseMapper.toCourseDTO(courseService.save(courseMapper.toCourse(body))));
    }

    @Override
    @PutMapping("")
    public ResponseEntity<CourseDTO> update(@RequestBody CourseDTO body) {
        return ResponseEntity.ok(courseMapper.toCourseDTO(courseService.update(courseMapper.toCourse(body))));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(courseMapper.toCourseDTO(courseService.deleteById(id)));
    }

    @DeleteMapping("/name/{code}")
    public ResponseEntity<CourseDTO> deleteByCode(@PathVariable String code){
        return ResponseEntity.ok(courseMapper.toCourseDTO(courseService.deleteByCode(code)));
    }
}
