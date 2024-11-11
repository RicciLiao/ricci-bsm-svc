package ricciliao.bsm.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.Serial;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

public class HttpServletWrapperFilter implements Filter {

    private final ExcludePathPatterns excludePathPatterns = new ExcludePathPatterns();

    private ExcludePathPatterns getExcludePathPatterns() {
        return excludePathPatterns;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // for swagger start--
        excludePathPatterns.add("/swagger-ui/.*");
        excludePathPatterns.add("/v3/api-docs/.*");
        // for swagger end--
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ContentCachingRequestWrapper httpServletRequestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper httpServletResponseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        // Set default is UTF-8
        if (StringUtils.isBlank(httpServletRequest.getCharacterEncoding())) {
            httpServletRequestWrapper.setCharacterEncoding("UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
        }

        try {
            if (!excludePathPatterns.matches(httpServletRequest.getRequestURI())) {
                Thread.sleep(Duration.ofSeconds(6L));
            }

            chain.doFilter(httpServletRequestWrapper, httpServletResponseWrapper);
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        } finally {
            /**
             *@Description: Copy the complete cached body content to the response.
             */
            httpServletResponseWrapper.copyBodyToResponse();
        }
    }

    @Override
    public void destroy() {
        // destroy
    }


    static class ExcludePathPatterns extends ArrayList<String> {

        @Serial
        private static final long serialVersionUID = 8274608574677831929L;

        public boolean matches(String url) {
            Iterator<String> iterator = iterator();
            if (StringUtils.isNotBlank(url)) {
                while (iterator.hasNext()) {
                    String excludePathPattern = iterator.next();
                    if (url.matches(excludePathPattern)) {

                        return true;
                    }
                }
            }

            return false;
        }
    }
}




























