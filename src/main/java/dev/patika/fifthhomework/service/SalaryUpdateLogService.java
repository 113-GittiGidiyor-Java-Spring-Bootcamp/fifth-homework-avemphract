package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.model.SalaryUpdateLog;
import dev.patika.fifthhomework.repository.SalaryUpdateInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryUpdateLogService {
    SalaryUpdateInfoRepository salaryUpdateInfoRepository;

    @Autowired
    public SalaryUpdateLogService(SalaryUpdateInfoRepository salaryUpdateInfoRepository) {
        this.salaryUpdateInfoRepository = salaryUpdateInfoRepository;
    }

    @Transactional
    public List<SalaryUpdateLog> findAll() {
        List<SalaryUpdateLog> list=new ArrayList<>();
        salaryUpdateInfoRepository.findAll().forEach(s->list.add(s));
        return list;
    }

    public List<SalaryUpdateLog> findByInstructorId(int instructorId){
        List<SalaryUpdateLog> list = new ArrayList<>();
        salaryUpdateInfoRepository.findByInstructorId(instructorId).forEach(s->list.add(s));
        return list;
    }

    public SalaryUpdateLog findById(int id) {
        Optional<SalaryUpdateLog> optionalSalaryUpdateLog = salaryUpdateInfoRepository.findById(id);
        return optionalSalaryUpdateLog.get();
    }

    public SalaryUpdateLog deleteById(int id) {
        Optional<SalaryUpdateLog> optional=salaryUpdateInfoRepository.findById(id);
        if (!optional.isPresent())
            throw new AbsentEntityException(SalaryUpdateLog.class,0);
        salaryUpdateInfoRepository.deleteById(id);
        return optional.get();
    }

}
