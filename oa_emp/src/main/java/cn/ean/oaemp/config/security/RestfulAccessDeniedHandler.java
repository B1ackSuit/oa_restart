package cn.ean.oaemp.config.security;

import cn.ean.oaemp.model.bo.ResponseBO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @FileName RestfulAccessDeniedHandler
 * @Author ean
 * @Date 2023/1/4 16:45
 **/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param httpServletResponse              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter out = httpServletResponse.getWriter();
        ResponseBO bo = ResponseBO.error("权限不足，请联系管理员！");
        bo.setCode(403);
        out.write(new ObjectMapper().writeValueAsString(bo));
        out.flush();
        out.close();

    }
}
