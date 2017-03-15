package org.bvvy.cms.filter;

import org.bvvy.basic.model.SystemContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class SystemContextFilter implements Filter {
    private Integer pageSize;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageSize = 15;
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Integer offset=0;
            try {
                offset = Integer.parseInt(servletRequest.getParameter("pager.offset"));
            } catch (NumberFormatException e) {
            }
            SystemContext.setPageOffset(offset);
            SystemContext.setPageSize(pageSize);
            SystemContext.setSort(servletRequest.getParameter("sort"));
            SystemContext.setOrder(servletRequest.getParameter("order"));
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            SystemContext.removeOrder();
            SystemContext.removeSort();
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
        }
    }

    @Override
    public void destroy() {

    }
}
