package jp.irof.ac;

import hudson.util.PluginServletFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageRequestFilter extends PluginServletFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getPathInfo().matches(".*/images/jenkins.png")) {
            ((HttpServletResponse) response).sendRedirect("/plugin/filter-sample/hudmi.png");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
