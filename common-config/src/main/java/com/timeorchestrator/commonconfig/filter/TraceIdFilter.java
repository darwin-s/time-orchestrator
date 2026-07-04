package com.timeorchestrator.commonconfig.filter;

import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter for setting a trace ID in the http headers
 */
public class TraceIdFilter extends OncePerRequestFilter {
    private static final String TRACE_HEADER_NAME = "X-Trace-Id";
    private final Tracer tracer;

    public TraceIdFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String traceId = getTraceId();
        if (traceId != null) {
            response.setHeader(TRACE_HEADER_NAME, traceId);
        }
        filterChain.doFilter(request, response);
    }

    private @Nullable String getTraceId() {
        TraceContext context = this.tracer.currentTraceContext().context();
        return context != null ? context.traceId() : null;
    }
}
