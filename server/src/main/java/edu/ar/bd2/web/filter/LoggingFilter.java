package edu.ar.bd2.web.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class LoggingFilter extends OncePerRequestFilter {

	private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(MediaType.valueOf("text/*"),
			MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.valueOf("application/*+json"), MediaType.valueOf("application/*+xml"),
			MediaType.MULTIPART_FORM_DATA);

	Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ContentCachingRequestWrapper wrapRequest = wrapRequest(request);
		ContentCachingResponseWrapper wrapResponse = wrapResponse(response);

		try {
			beforeRequest(wrapRequest);
			filterChain.doFilter(wrapRequest, wrapResponse);
		} finally {
			afterRequest(wrapRequest, wrapResponse);
			wrapResponse.copyBodyToResponse();
		}
	}

	protected void beforeRequest(ContentCachingRequestWrapper request) {
		if (log.isInfoEnabled()) {
			logRequestHeader(request, "|>");
		}
	}

	protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
		if (log.isInfoEnabled()) {
			logRequestBody(request, "|>");
			logResponse(response, "|<");
		}
	}

	private void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
		String queryString = request.getQueryString();
		if (queryString == null) {
			log.info("{} REQUEST {} {}", prefix, request.getMethod(), request.getRequestURI());
		} else {
			log.info("{} REQUEST {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
		}
	}

	private void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
		byte[] content = request.getContentAsByteArray();
		if (content.length > 0) {
			logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix);
		}
	}

	private void logResponse(ContentCachingResponseWrapper response, String prefix) {
		int status = response.getStatus();
		log.info("{} RESPONSE {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
		byte[] content = response.getContentAsByteArray();
		if (content.length > 0) {
			logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix);
		}
	}

	private void logContent(byte[] content, String contentType, String contentEncoding, String prefix) {
		MediaType mediaType = MediaType.valueOf(contentType);
		boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
		if (visible) {
			String contentString = StringUtils.toEncodedString(content, Charset.forName(contentEncoding));
			log.info("{} {}", prefix, contentString);
		} else {
			log.info("{} [{} bytes content]", prefix, content.length);
		}
	}

	private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
		if (request instanceof ContentCachingRequestWrapper) {
			return (ContentCachingRequestWrapper) request;
		} else {
			return new ContentCachingRequestWrapper(request);
		}
	}

	private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper) {
			return (ContentCachingResponseWrapper) response;
		} else {
			return new ContentCachingResponseWrapper(response);
		}
	}

}
