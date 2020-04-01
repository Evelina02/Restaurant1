package by.restaurant.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter
public class CharsetFilter implements Filter{

	private String encoding;
    
	private static final String UTF8_ENCODING = "UTF8";
	private static final String CHARACTER_ENCODING = "characterEncoding";


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		encoding = filterConfig.getInitParameter(CHARACTER_ENCODING);
		if(encoding == null) {
			encoding = UTF8_ENCODING;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {}
}
