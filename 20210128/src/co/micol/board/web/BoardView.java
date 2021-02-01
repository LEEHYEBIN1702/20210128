package co.micol.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Command;
import co.micol.board.dao.BoardDao;
import co.micol.board.vo.BoardVo;

public class BoardView implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) {
		//글 상세보기 (하려면 디비 다녀와야함)
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setbId(Integer.parseInt(request.getParameter("bId")));
		
		vo = dao.select(vo);
		request.setAttribute("vo", vo);
		
		return "views/board/boardView.jsp";
	}

}
