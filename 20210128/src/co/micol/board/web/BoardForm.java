package co.micol.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Command;

public class BoardForm implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) {
		//게시글 작성
		return "views/board/boardForm.jsp";
	}

}
