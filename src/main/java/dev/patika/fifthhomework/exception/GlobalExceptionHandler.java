package dev.patika.fourthhomeworkavemphract.exception;

import dev.patika.fourthhomeworkavemphract.model.BaseEntity;
import dev.patika.fourthhomeworkavemphract.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;

    @ExceptionHandler({AbsentEntityException.class})
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorEntity> handleException(AbsentEntityException exception) {
        ErrorEntity respond = prepareErrorRespond(NOT_FOUND, null, exception.getMessage());
        return new ResponseEntity<>(respond, NOT_FOUND);
    }

    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorEntity> handleException(CourseIsAlreadyExistException exception) {
        ErrorEntity respond = prepareErrorRespond(BAD_REQUEST, exception.getCourse(), exception.getMessage());
        return new ResponseEntity<>(respond, BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorEntity> handleException(InstructorIsAlreadyExistException exception) {
        ErrorEntity respond = prepareErrorRespond(BAD_REQUEST, exception.getInstructor(), exception.getMessage());
        return new ResponseEntity<>(respond, BAD_REQUEST);
    }

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorEntity> handleException(StudentAgeNotValidException exception) {
        ErrorEntity respond = prepareErrorRespond(BAD_REQUEST, exception.getStudent(), exception.getMessage());
        return new ResponseEntity<>(respond, BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorEntity> handleException(StudentNumberForOneCourseExceededException exception) {
        ErrorEntity respond = prepareErrorRespond(BAD_REQUEST, exception.getCourse(), exception.getMessage());
        return new ResponseEntity<>(respond, BAD_REQUEST);
    }

    private <T extends BaseEntity> ErrorEntity prepareErrorRespond(HttpStatus status, BaseEntity object, String string) {
        ErrorEntity errorObject = new ErrorEntity();
        errorObject.setErrorMessage(string);
        errorObject.setErrorCode(status.value());
        if (object != null) {
            errorObject.setErroredEntity(object.toString());
        }
        errorService.save(errorObject);
        return errorObject;
    }
}
