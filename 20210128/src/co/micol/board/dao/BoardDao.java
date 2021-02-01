package co.micol.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.board.common.DAO;
import co.micol.board.vo.BoardVo;

public class BoardDao extends DAO {
	private PreparedStatement psmt;
	private ResultSet rs;

	public ArrayList<BoardVo> selectList() {
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		BoardVo vo;
		String sql = "SELECT * FROM BOARD ORDER BY BID DESC";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setbId(rs.getInt("bid"));
				vo.setbName(rs.getString("bname"));
				vo.setbDate(rs.getDate("bdate"));
				vo.setbTitle(rs.getString("btitle"));
				vo.setbContent(rs.getString("bcontent"));
				vo.setbHit(rs.getInt("bhit"));
				vo.setbGroup(rs.getInt("bgroup"));
				vo.setbStep(rs.getInt("bstep"));
				vo.setbIndent(rs.getInt("bindent"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return list;
	}

	public BoardVo select(BoardVo vo) {
		String sql = "SELECT * FROM BOARD WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			rs = psmt.executeQuery();
			if (rs.next()) { // rs가 존재하면 값을 담아라.
				vo.setbId(rs.getInt("bid"));
				vo.setbName(rs.getString("bname"));
				vo.setbDate(rs.getDate("bdate"));
				vo.setbTitle(rs.getString("btitle"));
				vo.setbContent(rs.getString("bcontent"));
				vo.setbHit(rs.getInt("bhit"));
				vo.setbGroup(rs.getInt("bgroup"));
				vo.setbStep(rs.getInt("bstep"));
				vo.setbIndent(rs.getInt("bindent"));
				hitCount(vo.getbId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	public int insert(BoardVo vo) {
		int n = 0;
		String sql = "INSERT INTO BOARD VALUES(BIDSEQ.NEXTVAL, ?, ?, ?, ?, 0,0,0,0)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getbName());
			psmt.setString(2, vo.getbTitle());
			psmt.setString(3, vo.getbContent());
			psmt.setDate(4, vo.getbDate());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	public int delete(BoardVo vo) {
		int n = 0;
		String sql = "DELETE FROM BOARD WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			close();
		}
		return n;
	}

	public int update(BoardVo vo) {
		int n = 0;
		String sql = "UPDATE BOARD SET BCONTENT = ? WHERE BID =?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getbContent());
			psmt.setInt(2, vo.getbId());
			n = psmt.executeUpdate();
			if (rs.next()) {
				vo.setbContent(rs.getString("bcontent"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	private void hitCount(int hit) { //조회수 증가 메소드
		String sql = "UPDATE BOARD SET BHIT = BHIT+1 WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, hit);
			psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
