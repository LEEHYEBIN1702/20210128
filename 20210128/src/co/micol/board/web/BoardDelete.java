package co.micol.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Command;
import co.micol.board.dao.BoardDao;
import co.micol.board.vo.BoardVo;

public class BoardDelete implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) {
		// 글 삭제하기
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setbId(Integer.parseInt(request.getParameter("bId")));
		
		int n = dao.delete(vo);
		
	    String viewPage = "boardList.do";
		
		if(n == 0) 
			viewPage = "views/board/boardDeleteFail.jsp";
		
		return viewPage;
	}

}