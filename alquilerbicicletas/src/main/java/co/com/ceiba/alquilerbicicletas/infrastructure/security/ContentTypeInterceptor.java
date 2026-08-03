package co.com.ceiba.alquilerbicicletas.infrastructure.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import org.springframework.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ContentTypeInterceptor implements HandlerInterceptor{
    
    private static final String ERROR_SOLO_SE_PERMITE_APPLICATION_JSON = "{\"error\": \"Unsupported Media Type. Solo se permite application/json\"}";
    private static final String APPLICATION_JSON = "application/json";

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String metodo = request.getMethod();
        String contentType = request.getContentType();
        if ("POST".equals(metodo) || "PUT".equals(metodo)) {
            if(contentType == null || !APPLICATION_JSON.equals(contentType)){
                response.setStatus( HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                response.setContentType(APPLICATION_JSON);
                response.getWriter().write( ERROR_SOLO_SE_PERMITE_APPLICATION_JSON);
                return false;
                }
            }
        return true; 
    }
}
