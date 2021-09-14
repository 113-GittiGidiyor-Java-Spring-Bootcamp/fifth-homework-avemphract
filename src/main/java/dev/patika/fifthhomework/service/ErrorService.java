package dev.patika.fourthhomeworkavemphract.service;

import dev.patika.fourthhomeworkavemphract.exception.AbsentEntityException;
import dev.patika.fourthhomeworkavemphract.exception.ErrorEntity;
import dev.patika.fourthhomeworkavemphract.repository.ErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ErrorService{
    private final ErrorRepository errorRepository;

    /**
     * Get all ErrorEntities from database
     * @return List of ErrorEntities
     */
    @Transactional(readOnly = true)
    public List<ErrorEntity> findAll(){
        List<ErrorEntity> list=new ArrayList<>();
        errorRepository.findAll().forEach(n->list.add(n));
        return list;
    }

    /**
     * Get ErrorEntity by entity id from database
     * @param id course id
     * @return ErrorEntity
     */
    @Transactional(readOnly = true)
    public ErrorEntity findById(int id){
        Optional<ErrorEntity> optionalErrorEntity=errorRepository.findById(id);
        if (optionalErrorEntity.isPresent()){
            return optionalErrorEntity.get();
        }
        throw new AbsentEntityException(ErrorEntity.class, id);
    }

    /**
     * Save ErrorEntity and return
     * @param errorEntity
     * @return ErrorEntity
     */
    @Transactional(readOnly = false)
    public ErrorEntity save(ErrorEntity errorEntity){
        return errorRepository.save(errorEntity);
    }

    /**
     * Get List of ErrorEntity by http status code
     * @param errorCode
     * @return List of ErrorEntity
     */
    @Transactional(readOnly = true)
    public List<ErrorEntity> findByErrorCode(int errorCode){
        return errorRepository.findByErrorCode(errorCode);
    }

    /**
     * Get List of ErrorEntity between dates
     * If beginDate is null, it will set 0000-01-01
     * If endDate is null, it will set 2050-01-01
     * @param beginDate (Optional) Errors recorded later than date
     * @param endDate (Optional) Errors recorded before than date
     * @return
     */
    @Transactional(readOnly = true)
    public List<ErrorEntity> findByDate(String beginDate, String endDate){
        return errorRepository.findByDateBetween(
                beginDate!=null?LocalDate.parse(beginDate).atStartOfDay().toInstant(ZoneOffset.UTC):LocalDate.of(0,1,1).atStartOfDay().toInstant(ZoneOffset.UTC),
                endDate!=null?LocalDate.parse(endDate).atStartOfDay().toInstant(ZoneOffset.UTC):LocalDate.of(2050,1,1).atStartOfDay().toInstant(ZoneOffset.UTC));
    }

}
