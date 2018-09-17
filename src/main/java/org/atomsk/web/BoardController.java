package org.atomsk.web;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.atomsk.dao.BoardDAO;
import org.atomsk.domain.PageDTO;
import org.atomsk.domain.PageMaker;
import org.atomsk.web.util.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


@WebServlet(urlPatterns = "/board/*")
@Log4j
public class BoardController extends AbstractController {

    private BoardDAO dao = new BoardDAO();

//    private static Logger logger = Logger.getLogger("board");

    public String writeGET(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        System.out.println("writeGET.......................");
        return "write";
    }

    public String listGET(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        System.out.println("listGET.......................");

        log.trace("Trace..............");
        log.debug("debug.............");
        log.info("info...................");
        log.warn("warn...................");
        log.error("error...................");
        log.fatal("fatal.................");

        PageDTO dto = PageDTO.of()
                .setPage(Converter.getInt(req.getParameter("page"),1))
                .setSize(Converter.getInt(req.getParameter("size"),10));

        int total = 320;
        PageMaker pageMaker = new PageMaker(total, dto);

        req.setAttribute("pageMaker", pageMaker);
        req.setAttribute("list", dao.getList(dto));

        return "list";
    }

    public String readGET(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        System.out.println("readGET.......................");

        String bnoStr = req.getParameter("bno");
        int bno = Converter.getInt(bnoStr,-1);
        boolean updateable = false;

        if(bno == -1){ throw new Exception("Invalid data"); }

        Cookie[] cookies = req.getCookies();
        Cookie viewCookie = null;
        for(Cookie ck:cookies){
            if(ck.getName().equals("views")){
                viewCookie = ck;
                break;
            }
        }
        //쿠키가 없다면
        if(viewCookie == null){
            Cookie newCookie = new Cookie("views", bnoStr+"%");
            newCookie.setMaxAge(60*60*24);
            viewCookie = newCookie;
            updateable = true;
        }else {
            String cookieValue = viewCookie.getValue();
            System.out.println("cookieValue: "+cookieValue);
            updateable = cookieValue.contains(bnoStr+'%') == false;

            if(updateable){
                cookieValue += bnoStr +"%";
                viewCookie.setValue(cookieValue);
            }
            System.out.println("cookieValue after: "+cookieValue);
            System.out.println("updateable: " + updateable);
        }
        System.out.println("-------------board con--------------");
        System.out.println(viewCookie);

        resp.addCookie(viewCookie);

        req.setAttribute("board", dao.getBoard(bno, updateable));

        return "read";
    }

    public String getBasic() {
        return "/board/";
    }
}


//@WebServlet(urlPatterns = "/board/*")
//public class BoardController extends HttpServlet {
//
//
//
//
//    public String  writeGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        System.out.println("writeGET.........");
//        return "write";
//    }
//        public String  listGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//            System.out.println("listGET.........");
//            return "list";
//        }
//    public String readGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        System.out.println("readGET.........");
//        return "read";
//    }
//
//        public String getBasic(){
//            return "/board/";
//        }
//
//    }
//
//
