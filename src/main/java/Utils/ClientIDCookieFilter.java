package Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter("/*")
public class ClientIDCookieFilter implements Filter {
    private final String name = "web_lab3_client_id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long clientId = 0L;
        boolean flag = false;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Cookie[] userCookies = httpServletRequest.getCookies();

        if (userCookies != null && userCookies.length > 0 ) {
            for (int i = 0; i < userCookies.length; i++) {
                if (userCookies[i].getName().equals(name)) {
                    Cookie cookie = userCookies[i];
                    clientId = Long.parseLong(cookie.getValue());
                    flag=true;
                    break;
                }
            }
        }
        if(!flag){
            clientId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            Cookie cookie = new Cookie(name,Long.toString(clientId));
            cookie.setMaxAge(31536000);
            httpServletResponse.addCookie(cookie);
        }
        request.setAttribute("clientID", clientId);
    }

    @Override
    public void destroy() {

    }
}
