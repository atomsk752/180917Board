import lombok.ToString;
import org.atomsk.dao.BoardDAO;
import org.atomsk.domain.BoardVO;
import org.atomsk.domain.PageDTO;
import org.atomsk.domain.PageMaker;
import org.junit.Test;

import java.util.TreeMap;

import static org.junit.Assert.assertNotNull;

public class BoardDAOTests {


    private BoardDAO boardDAO = new BoardDAO();




    @Test
    public void testPageMaker(){
        PageDTO dto = PageDTO.of().setPage(7).setSize(10);
        int total = 123;

        PageMaker pageMaker = new PageMaker(total,dto);

        System.out.println(pageMaker);

    }


    @Test

    public void testList() throws Exception{
        boardDAO.getList(PageDTO.of().setPage(2).setSize(100))
                .forEach(vo-> System.out.println(vo));

    }

    @Test
    public void testInsert() throws Exception{
        BoardVO vo = new BoardVO();
        vo.setTitle("경----★코드로 배우는 웹스프링 자바분야 1위★---축");
        vo.setContent("어예");
        vo.setWriter("예스24사장");
        boardDAO.create(vo);
    }

    @Test
    public void test1(){

        assertNotNull(boardDAO);
        System.out.println("test1...............");

        PageDTO pageDTO= PageDTO.of().setSize(20).setPage(5);

    }

}
