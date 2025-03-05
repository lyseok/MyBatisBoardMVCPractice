package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.util.MyBatisUtil;
import board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	public static IBoardDao instance;
	
	private BoardDaoImpl() {}
	
	public static IBoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.insert("board.insertBoard", boardVo);
				
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO bVo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			
			bVo = session.selectOne("board.getBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return bVo;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.update("board.updateBoard", boardVo);
			
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.delete("board.deleteBoard", boardNo);
			
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(String boardTitle) {
		SqlSession session = null;
		List<BoardVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("board.searchBoard", boardTitle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public List<BoardVO> printAllBoard() {
		SqlSession session = null;
		List<BoardVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("board.printAllBoard");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.update("board.setCountIncrement", boardNo);
			
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

}
