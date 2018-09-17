package org.atomsk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/board/*") //보드로 시작하는 모든 경로에 적용 필터는 막는 역할
public class SampleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init sample filter");



    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        System.out.println("do sample filter");

        HttpServletResponse resq =(HttpServletResponse)servletResponse;

        String pageStr = servletRequest.getParameter("page");

        try{
            if (pageStr == null){throw new Exception("NULL");}

            int page = Integer.parseInt(pageStr);

//            if (page <=1){
//                throw new Exception("Page Num"); //강제로 발생시키기
//            }

        }catch(NumberFormatException e) {
            resq.sendRedirect("/error");
            return;
        }catch (Exception ee){

        }


        filterChain.doFilter(servletRequest,servletResponse);





    }
}
