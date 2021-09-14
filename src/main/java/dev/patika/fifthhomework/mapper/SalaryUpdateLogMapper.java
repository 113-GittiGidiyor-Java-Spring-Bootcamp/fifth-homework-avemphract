package dev.patika.fifthhomework.mapper;

import dev.patika.fifthhomework.dto.SalaryUpdateLogDTO;
import dev.patika.fifthhomework.model.SalaryUpdateLog;
import dev.patika.fifthhomework.service.InstructorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SalaryUpdateLogMapper {
    @Autowired
    protected InstructorService instructorService;

    @Mappings({
            @Mapping(target = "instructor", expression = "java(instructorService.findById(salaryUpdateLogDTO.getInstructorId()))")
    })
    public abstract SalaryUpdateLog toSalaryUpdateLog(SalaryUpdateLogDTO salaryUpdateLogDTO);
    
    @Mappings({
            @Mapping(target = "instructorId",expression = "java(salaryUpdateLog.getId())")
    })
    public abstract SalaryUpdateLogDTO toSalaryUpdateLogDTO(SalaryUpdateLog salaryUpdateLog);

    public abstract List<SalaryUpdateLogDTO> salaryUpdateLogDTOList(List<SalaryUpdateLog> salaryUpdateLogList);

    public abstract List<SalaryUpdateLog> salaryUpdateLogList(List<SalaryUpdateLogDTO> salaryUpdateLogDTOList);
}
