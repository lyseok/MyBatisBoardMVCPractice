package board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.dao.BoardDaoImpl;
import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;


public class BoardController {
	IBoardService service;
	Scanner sc;
	
	public BoardController() {
		service = BoardServiceImpl.getInstance(BoardDaoImpl.getInstance());
		sc = new Scanner(System.in);		
	}
	
	public static void main(String[] args) {
		new BoardController().start();
	}
	
	private void start() {
		while(true) {
			switch(display()){
				case 1:	insertBoard();break;
				case 2:	viewBoard(); break;
				case 3:	updateBoard(); break;
				case 4:	deleteBoard(); break;
				case 5:	serachBoard(); break;
				case 6:	printAllBoard(); break;
				case 0: System.out.println("\n프로그램 종료"); return;
				default:
					System.out.println("작업번호를 잘못 입력했습니다");
					System.out.println("다시 입력해주세요");
			}
		}
	}

	// 메뉴를 출력하고 작업 번호를 반환하는 메서드
	private int display() {
		System.out.println();
		System.out.println("1. 새글 작성");
		System.out.println("2. 게시글 보기");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
		System.out.println("5. 게시글 검색");
		System.out.println("6. 전체 목록 출력");
		System.out.println("0. 작업 끝");
		System.out.println("===========================");
		System.out.print("작업번호 >> ");
		return sc.nextInt();
	}
	
	// 새글을 작성하는 메서드
	private void insertBoard() {
		sc.nextLine();
		BoardVO boardVo = new BoardVO();
		
		System.out.println("게시글 작성");
		System.out.print("제목 : ");
		boardVo.setBoard_title(sc.nextLine());
		System.out.print("작성자 : ");
		boardVo.setBoard_writer(sc.nextLine());
		System.out.print("게시글 내용 : ");
		boardVo.setBoard_content(sc.nextLine());
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("게시글 작성 완료");
		} else {
			System.out.println("게시글 작성 실패");
		}
	}
	
	private void viewBoard() {
		System.out.println("읽을 게시글 번호를 입력하세여");
		System.out.print(">> ");
		int no = sc.nextInt();
		
		BoardVO boardVo = service.getBoard(no);
		
		if(boardVo == null) {
			System.out.println(no + "번 게시글은 존재하지 않습니다");
		} else {
			System.out.println();
			System.out.println("게시글 번호 : "	+ boardVo.getBoard_no());
			System.out.println("-----------------------------------");
			System.out.println("제 목 : " 	+ boardVo.getBoard_title());
			System.out.println("작성자 : " 	+ boardVo.getBoard_writer());
			System.out.println("조회수 : " 	+ boardVo.getBoard_cnt());
			System.out.println("내 용 : " 	+ boardVo.getBoard_content());
			System.out.println("작성일 : " 	+ boardVo.getBoard_Date());
			System.out.println();
		}
	}
	
	private void updateBoard() {
		BoardVO boardVo = new BoardVO();
		
		System.out.println("수정할 게시글 번호를 입력하세여");
		System.out.print(">> ");
		boardVo.setBoard_no(sc.nextInt());
		
		sc.nextLine();
		System.out.println("게시글 수정");
		System.out.print("제목 : ");
		boardVo.setBoard_title(sc.nextLine());
		System.out.print("게시글 내용 : ");
		boardVo.setBoard_content(sc.nextLine());
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("게시글 수정 완료");
		} else {
			System.out.println("게시글 수정 실패");
		}
	}
	
	private void serachBoard() {
		sc.nextLine();
		System.out.println("검색할 게시글 제목을 입력하세요");
		System.out.print(" >> ");
		String title = sc.nextLine();
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = service.searchBoard(title);
		
		printBoardList(list);
		
	}
	
	private void deleteBoard() {
		System.out.println();
		System.out.println("게시글 삭제하기");
		System.out.println("--------------------------");
		System.out.print("삭제할 게시글 번호 >> ");
		int no = sc.nextInt();
		
		int cnt = service.deleteBoard(no);
		
		if(cnt > 0) {
			System.out.println(no + "번 게시글이 삭제되었습니다");
		} else {
			System.out.println(no + "번 글이 없거나 삭제 실패했습니다");
		}
		
	}
	
	private void printAllBoard(){
		List<BoardVO> list = service.printAllBoard();
		printBoardList(list);
	}
	
	// 게시글 목록을 매개변수로 받아서 출력하는 메서드
	private void printBoardList(List<BoardVO> list) {
		
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("NO\t제목\t작성자\t조회수");
		System.out.println("-------------------------------------");
		
		if(list == null || list.size() == 0) {
			System.out.println("게시글이 하나도 없습니다.");
		} else {
			for(BoardVO bVo : list) {
				System.out.printf("%d\t%s\t%s\t%d\n", 
						bVo.getBoard_no(),
						bVo.getBoard_title(),
						bVo.getBoard_writer(),
						bVo.getBoard_cnt() );
			}
		}
		System.out.println("-------------------------------------");
		
	}

	
}

