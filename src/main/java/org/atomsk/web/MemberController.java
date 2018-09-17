package org.atomsk.web;

import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@WebServlet("/member/*")
@Log4j
public class MemberController extends AbstractController {

    public String loginGET(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        log.info("Login Post");

        Cookie loginCookie = new Cookie("login", URLEncoder.encode("LeeEunsang","UTF-8"));
        loginCookie.setMaxAge(60*60*24); //자동로그인 제한시간
        loginCookie.setPath("/"); //어떤 경로에서 작동할지 설정
        resp.addCookie(loginCookie);

        return "/board/list";
    }
    @Override
    public String getBasic() {
        return "/member/";
    }
}
