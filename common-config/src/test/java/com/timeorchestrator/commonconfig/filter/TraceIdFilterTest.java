package com.timeorchestrator.commonconfig.filter;

import io.micrometer.tracing.CurrentTraceContext;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Trace ID Filter tests
 */
@ExtendWith(MockitoExtension.class)
class TraceIdFilterTest {
    private static final String HEADER_NAME = "X-Trace-Id";
    private static final String TEST_TRACE = "trace123";

    @Mock
    private Tracer tracer;

    @Mock
    private TraceContext traceContext;

    @Mock
    private CurrentTraceContext currentTraceContext;

    @Mock
    private FilterChain filterChain;

    private TraceIdFilter traceIdFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        when(tracer.currentTraceContext()).thenReturn(currentTraceContext);

        traceIdFilter = new TraceIdFilter(tracer);
    }

    @Test
    void traceIdHeaderPresentWhenTracingContextIsNotNull() throws Exception {
        when(currentTraceContext.context()).thenReturn(traceContext);
        when(traceContext.traceId()).thenReturn(TEST_TRACE);

        traceIdFilter.doFilterInternal(request, response, filterChain);

        assertThat(response.getHeader(HEADER_NAME), equalTo(TEST_TRACE));
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void traceIdHeaderNotPresentWhenTracingContextIsNull() throws Exception {
        when(currentTraceContext.context()).thenReturn(null);

        traceIdFilter.doFilterInternal(request, response, filterChain);

        assertFalse(response.containsHeader(HEADER_NAME));
        verify(filterChain).doFilter(request, response);
    }
}
