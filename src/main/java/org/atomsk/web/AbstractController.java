package org.atomsk.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public abstract class AbstractController extends HttpServlet {

    public abstract String getBasic();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("service..........");

        String path = req.getRequestURI().substring(getBasic().length());
        String way = req.getMethod();

        System.out.println(path+": " + way);

        String methodName = path+way; //writeGET, listGET, writePOST

        Class clz = this.getClass();

        try {
            Method method = clz.getDeclaredMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);

            Object result = method.invoke(this, req, resp);

            String returnURL = (String)result;

            System.out.println("RETURN: " + returnURL);

            if(returnURL.startsWith("redirect")){
                resp.sendRedirect(returnURL.substring(9));
                return;
            }
            req.getRequestDispatcher("/WEB-INF/"+returnURL+".jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
//public abstract class AbstractController extends HttpServlet {
//
//    public abstract String getBasic();
//    @Override //get post 상관없이 받기
//
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        System.out.println("Service.....................");
//
//        String path = req.getRequestURI().substring(getBasic().length());//
////        String path = req.getRequestURI(); //무슨 경로로 들어오는지
//        String way = req.getMethod(); // get인지 post인지
//
//        String methodName = path+way; //writeGET, listGet, write POST
//
//        Class clz = this.getClass();
//
//        try {
//            Method method = clz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class); //클래스에 선언된 메소드 찾기 이름이 같은 메소드가 많아서 파라미터도 불러옴
//
//            Object result = method.invoke(this, req, resp);//메소드 실행
//
//            String returnURL = (String)result; //오브젝트 받은 것을 스트링으로 보기
//
//            System.out.println("RETURN: " + returnURL);
//
//            if(returnURL.startsWith("redirect")){
//                resp.sendRedirect(returnURL.substring(9));//redirect로 시작하면 9자 잘라냄
//                return;
//            }
//            req.getRequestDispatcher("/WEB-INF/"+returnURL+".jsp").forward(req,resp);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println(path+":"+way);
//
//    }
//}
