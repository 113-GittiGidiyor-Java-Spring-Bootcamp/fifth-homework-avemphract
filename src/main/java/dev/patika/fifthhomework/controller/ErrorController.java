package dev.patika.fourthhomeworkavemphract.controller;

import dev.patika.fourthhomeworkavemphract.exception.ErrorEntity;
import dev.patika.fourthhomeworkavemphract.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/errors")
public class ErrorController {
    private final ErrorService errorService;

    @Autowired
    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping()
    public ResponseEntity<List<ErrorEntity>> findAll() {
        return ResponseEntity.ok(errorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrorEntity> findById(@PathVariable int id) {
        return ResponseEntity.ok(errorService.findById(id));
    }

    @GetMapping("/code/{errorCode}")
    public ResponseEntity<List<ErrorEntity>> findByErrorCode(@PathVariable int errorCode) {
        return ResponseEntity.ok(errorService.findByErrorCode(errorCode));
    }

    @RequestMapping("/code/errorDate")
    public ResponseEntity<List<ErrorEntity>> findByDate(@RequestParam(value = "begin",required = false) String beginDate, @RequestParam(value = "end",required = false) String endDate) {
        System.out.println(beginDate+" / "+endDate);
        return ResponseEntity.ok(errorService.findByDate(beginDate,endDate));
    }

}
