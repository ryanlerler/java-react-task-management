package com.example.backendapplication.security.jwt;

import com.example.backendapplication.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.example.backendapplication.security.utils.SecurityConstants.*;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenManager jwtTokenManager;

	private final UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

		final String requestURI = req.getRequestURI();

		if (requestURI.contains(LOGIN_REQUEST_URI) || requestURI.contains(REGISTRATION_REQUEST_URI)) {
			chain.doFilter(req, res);
			return;
		}

		final String header = req.getHeader(HEADER_STRING);
		String username = null;
		String authToken = null;
		if (Objects.nonNull(header) && header.startsWith(TOKEN_PREFIX)) {

			authToken = header.replace(TOKEN_PREFIX, StringUtils.EMPTY);
			try {
				username = jwtTokenManager.getUsernameFromToken(authToken);
			}
			catch (Exception e) {
				log.error("Authentication Exception : {}", e.getMessage());
			}
		}

		final SecurityContext securityContext = SecurityContextHolder.getContext();

		if (Objects.nonNull(username) && Objects.isNull(securityContext.getAuthentication())) {

			final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtTokenManager.validateToken(authToken, userDetails.getUsername())) {

				final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				log.info("Authentication successful. Logged in username : {} ", username);
				securityContext.setAuthentication(authentication);
			}
		}

		chain.doFilter(req, res);
	}
}
