package dev.patika.fifthhomework.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component
@SessionScope
public class SalaryUpdateRequestScope {
    private String sessionId;
    private String clientUrl;
}
