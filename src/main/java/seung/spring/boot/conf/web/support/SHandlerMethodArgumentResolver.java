package seung.spring.boot.conf.web.support;

import java.net.URI;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;

@Slf4j
public class SHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		log.debug("run");
		return methodParameter.getParameterType().equals(SRequest.class);
	}// end of supportsParameter
	
	@Override
	public Object resolveArgument(
			MethodParameter methodParameter
			, ModelAndViewContainer modelAndViewContainer
			, NativeWebRequest nativeWebRequest
			, WebDataBinderFactory webDataBinderFactory
			) throws Exception {
		
		log.debug("run");
		
		SRequest sRequest = SRequest.builder()
				.build()
				;
		
		Object element = null;
		String key = null;
//		String[] value = null;
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
		
		// network
		key = "remote_addr";
		String remote_addr = httpServletRequest.getHeader("X-Forwarded-For");
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("Proxy-Client-IP");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("HTTP_CLIENT_IP");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getRemoteAddr();
		}
		sRequest.putNetwork(key, remote_addr);
		key = "request_uri";
		sRequest.putNetwork(key, httpServletRequest.getRequestURI());
		key = "referer";
		sRequest.putNetwork(key, httpServletRequest.getHeader("referer") == null ? "" : new URI(httpServletRequest.getHeader("referer")).getPath());
		
		// parameters
		if(methodParameter.getParameterType().equals(SRequest.class)) {
			
			// header
			Enumeration<?> enumerations = httpServletRequest.getHeaderNames();
			while(enumerations.hasMoreElements()) {
				
				element = enumerations.nextElement();
				key = element == null ? "" : (String) element;
				
				sRequest.putHeader(key, httpServletRequest.getHeader(key));
				
			}// end of header
			
		}
		
		return sRequest;
		
	}// end of resolveArgument
	
}
