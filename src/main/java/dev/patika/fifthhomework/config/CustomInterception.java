package dev.patika.fifthhomework.config;

import dev.patika.fifthhomework.utils.SalaryUpdateRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomInterception implements AsyncHandlerInterceptor {

    @Autowired
    SalaryUpdateRequestInfo salaryUpdateRequestInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        salaryUpdateRequestInfo.setClientUrl(request.getRemoteAddr());
        salaryUpdateRequestInfo.setSessionId(request.getSession().getId());
        salaryUpdateRequestInfo.setRequestURI(request.getRequestURL().toString());
        return true;
    }
}
