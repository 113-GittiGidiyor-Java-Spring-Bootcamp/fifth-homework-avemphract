package dev.patika.fourthhomeworkavemphract.controller;

import dev.patika.fourthhomeworkavemphract.dto.CourseDTO;
import dev.patika.fourthhomeworkavemphract.mapper.CourseMapper;
import dev.patika.fourthhomeworkavemphract.model.Course;
import dev.patika.fourthhomeworkavemphract.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
        courseService.findAll().forEach(c->courseDTOList.add(courseMapper.courseToCourseDTO(c)));
        return ResponseEntity.ok(courseDTOList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(courseMapper.courseToCourseDTO(courseService.findById(id)));
    }

    @GetMapping("/course_code/{code}")
    public ResponseEntity<CourseDTO> findByCode(@PathVariable String code){
        return ResponseEntity.ok(courseMapper.courseToCourseDTO(courseService.findByCode(code)));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<CourseDTO> save(@RequestBody CourseDTO body) {
        return ResponseEntity.ok(courseMapper.courseToCourseDTO(courseService.save(body)));
    }

    @Override
    @PutMapping("")
    public ResponseEntity<CourseDTO> update(@RequestBody CourseDTO body) {
        return ResponseEntity.ok(courseMapper.courseToCourseDTO(courseService.update(body)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(courseMapper.courseToCourseDTO(courseService.deleteById(id)));
    }

    @DeleteMapping("/name/{code}")
    public ResponseEntity<CourseDTO> deleteByCode(@PathVariable String code){
        return ResponseEntity.ok(courseMapper.courseToCourseDTO(courseService.deleteByCode(code)));
    }
}
