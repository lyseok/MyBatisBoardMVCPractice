package board.service;

import java.util.List;

import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private static IBoardService instance;
	private IBoardDao dao;
	
	private BoardServiceImpl(IBoardDao dao) {
		this.dao = dao;
	}
	
	public static IBoardService getInstance(IBoardDao dao) {
		if(instance == null) {
			instance = new BoardServiceImpl(dao);
		}
		return instance;
	}
	
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		if(setCountIncrement(boardNo) == 0) {
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> searchBoard(String boardTitle) {
		return dao.searchBoard(boardTitle);
	}

	@Override
	public List<BoardVO> printAllBoard() {
		return dao.printAllBoard();
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
