package com.domain.authCommon.filter;

import com.domain.authCommon.appUser.service.AppUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final AppUserService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String username = request.getHeader("X-Username");
        System.out.println(username);

//       if (username != null) {
//           UserDetails userDetails = service.loadUserByUsername(username);
//           if (userDetails != null) {
//               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//               SecurityContextHolder.getContext().setAuthentication(authentication);
//           }
//       }
        System.out.println("doFilterInternal AuthFilter");

       filterChain.doFilter(request, response);
    }
}
