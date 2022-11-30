package com.cross.Danmat.board.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Board {
	private int board_idx; // 게시물 번호
	private String notice; // 공지 등록 여부 (공지사항, 게시판 2개가 합쳐지면서 추가)
	private String userId; // 유저 id
	private String title; // 게시글 제목
	private String content; // 게시글 목록
	private Date createDate; // 게시글 생성일자
	private Date updateDate; // 게시글 수정일자
	private Date deleteDate; // 게시글 삭제일자
	private String delete_yn; // 삭제 여부
	private long readCount; // 조회수
	private long replyCount; // 댓글 수
	
	
	public Board() {
		super();
	}
	
	public Board(int board_idx, String notice, String userId, String title, String content, Date createDate, Long readCount, long replyCount) {
		super();
		this.board_idx = board_idx;
		this.notice = notice;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.readCount = readCount;
		this.replyCount = replyCount;
	}
	
	public Board(int board_idx, String notice, String title, String content, Date updateDate, Long readCount) {
		super();
		this.board_idx = board_idx;
		this.notice = notice;
		this.title = title;
		this.content = content;
		this.updateDate = updateDate;
		this.readCount = readCount;
	}
}