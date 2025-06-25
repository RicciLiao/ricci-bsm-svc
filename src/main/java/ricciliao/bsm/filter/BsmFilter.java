package ricciliao.bsm.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ricciliao.x.component.servlet.ContentCachingFilter;
import ricciliao.x.log.AuditLoggerFactory;
import ricciliao.x.log.logger.AuditLogger;

import java.io.IOException;

public class BsmFilter extends ContentCachingFilter {

    private static final AuditLogger logger = AuditLoggerFactory.getLogger(BsmFilter.class);

    @Override
    public void doFilter(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, FilterChain chain) throws ServletException, IOException {
        logger.duration().start().info("message filter received.");
        chain.doFilter(requestWrapper, responseWrapper);
        logger.duration().stop().info("message filter completed.");
    }

}




























