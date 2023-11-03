package com.example.springmvc_thuc_tap.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter("/static/uploads/**")
@Component
public class FileAccessFilter extends OncePerRequestFilter {
    //muon truy cap duoc thi http://localhost:3030/static/uploads/hello.txt?pass=123
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if (requestURI.contains("/static/uploads/")){
            if (request.getParameterMap().containsKey("pass") && "123".equals(request.getParameter("pass"))) {
                // Cho phép truy cập vào file tĩnh
                filterChain.doFilter(request, response);
            } else {
                // Trả về lỗi hoặc chuyển hướng tùy ý
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // Hoặc có thể chuyển hướng đến một trang lỗi
                // response.sendRedirect("/error-page");
            }
        } else{
            filterChain.doFilter(request, response);
        }

    }
}
