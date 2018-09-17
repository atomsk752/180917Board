package org.atomsk.dao;

import lombok.extern.log4j.Log4j;
import org.atomsk.dao.QueryExecutor;
import org.atomsk.domain.BoardVO;
import org.atomsk.domain.PageDTO;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class BoardDAO {

    private static final String INSERT ="insert into TBL_BOARD (BNO, TITLE, CONTENT, WRITER) \n" +
            "VALUES (SEQ_BOARD.nextval,?,?,?)";

    private static final String LIST = "select *\n" +
            "from (select\n" +
            "             /*+INDEX_DESC(tbl_board pk_board) */\n" +
            "          ROWNUM rn, bno, title, CONTENT, WRITER, REGDATE, UPDATEDATE, viewcnt \n" +
            "      from TBL_BOARD\n" +
            "      where BNO > 0\n" +
            "        AND ROWNUM <= (? * ?))\n" +
            "where rn > ( ? -1) * ?";

    private static final String UPDATE_HIT = "update tbl_board set viewcnt = viewcnt +1 where bno = ?";

    private static final String READ ="select * from tbl_board where bno = ?";

    public BoardVO getBoard(Integer bno, boolean updateable)throws Exception{
        BoardVO vo = new BoardVO();

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                if(updateable){
                    stmt = con.prepareStatement(UPDATE_HIT);
                    stmt.setInt(1, bno);
                    int updateCnt = stmt.executeUpdate();
                    log.info("updateCnt: "+updateCnt);
                    stmt.close();
                }
                stmt = con.prepareStatement(READ);
                stmt.setInt(1,bno);
                rs = stmt.executeQuery();
                while(rs.next()){
                    vo.setTitle(rs.getString("title"));
                    vo.setContent(rs.getString("content"));
                    vo.setViewcnt(rs.getInt("viewcnt"));
                }

            }
        }.executeAll();


        return vo;
    }


    public List<BoardVO> getList(PageDTO pageDTO)throws Exception{
        List<BoardVO> list = new ArrayList<>();

        log.debug(LIST);

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(LIST);
                int i = 1;
                stmt.setInt(i++, pageDTO.getPage());
                stmt.setInt(i++, pageDTO.getSize());
                stmt.setInt(i++, pageDTO.getPage());
                stmt.setInt(i++, pageDTO.getSize());
                rs = stmt.executeQuery();

                //bno, title, CONTENT, WRITER, REGDATE, UPDATEDATE
                while(rs.next()){
                    BoardVO vo = new BoardVO();
                    int idx = 2;
                    vo.setBno(rs.getInt(idx++));
                    vo.setTitle(rs.getString(idx++));
                    vo.setContent(rs.getString(idx++));
                    vo.setWriter(rs.getString(idx++));
                    vo.setRegdate(rs.getDate(idx++));
                    vo.setUpdatedate(rs.getDate(idx++));
                    vo.setViewcnt(rs.getInt(idx++));
                    list.add(vo);
                }
            }
        }.executeAll();
        return list;
    }

    public void create(BoardVO vo)throws Exception{

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(INSERT);
                int i = 1;
                stmt.setString(i++, vo.getTitle());
                stmt.setString(i++, vo.getContent());
                stmt.setString(i++, vo.getWriter());
                stmt.executeUpdate();
            }
        }.executeAll();

    }
}