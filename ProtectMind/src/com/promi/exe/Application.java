package com.promi.exe;

import java.util.Scanner;

import com.promi.event.EvenService;
import com.promi.member.Member;
import com.promi.member.MemberService;
import com.promi.product.ProductService;

public class Application {
	Scanner s = new Scanner(System.in);
	ProductService ps = new ProductService();
	MemberService ms = new MemberService();
	EvenService es = new EvenService();
	int menu;

	public Application() {
		while (true) {
			System.out.println("1.로그인 2.회원가입 3.종료");

			
			menu = Integer.parseInt(s.nextLine());
			if (menu == 1) {
				
				ms.doLogin();
				if (MemberService.memberInfo.getRoles() == 0) {
					run2();
				} else if (MemberService.memberInfo.getRoles() == 1) {
					System.out.println("1.일반 2. 관리자");
					int menu2 = Integer.parseInt(s.nextLine());
					if(menu2==1) {
						run2();
					}else if (menu2==2) {
						run();
					}

				}

			} else if (menu == 2) {
				ms.insertShip();
				
			} else if (menu == 3) {
				System.out.println("종료합니다.");
				break;
			}
		}
	}
			///일반//
	private void run2() {
		boolean run = true;
		while (run) {
			System.out.println("1.상품 목록 2.검색 3.정보조회(조회&구매목록&삭제) 4.구매(구매+상품) 5.배송확인 6 .게시판 7.종료(로그아웃) ");
			menu = Integer.parseInt(s.nextLine());
			switch (menu) {

			case 1:// 상품목록
				ps.listProduck();
				break;
			case 2:// 검색
				ps.listProduckName();
				break;
			case 3:// 정보조회 (일반조회 ,구매목록,계정 삭제)
				ms.detailInfo();
				break;
			case 4:// 구매//
				ps.buyProduct();
				break;
			case 5:// 배송확인
				ms.deliveryMem();
				break;

			case 6:// 게시판
				while (true) {
					System.out.println("1.자유게시판 2.리뷰게시판");
					int menu = Integer.parseInt(s.nextLine());
					while (true) {
						if (menu == 1) {
							System.out.println("==============자유 게시판==============");
							System.out.println(" 1.목록 2.읽기 3.검색 4.글쓰기 5.글삭제 6.나가기");
							int menu2 = Integer.parseInt(s.nextLine());
							if (menu2 == 1) {// 목록
								es.listEvent();
							} else if (menu2 == 2) {// 읽기
								es.readEvent();
							} else if (menu2 == 3) {// 검색
								es.searchEvent();
							} else if (menu2 == 4) {// 글쓰기
								es.writeBoard();
							} else if (menu2 == 5) {// 글삭제
								es.delete();

							} else if (menu2 == 6) {
								break;

							}
							// 이벤트게시판
						} else if (menu == 2) {
							System.out.println("==============2리뷰 게시판==============");
							System.out.println(" 1.목록 2.읽기 3.검색 4.글쓰기 5.글삭제 6.나가기");
							int menu2 = Integer.parseInt(s.nextLine());
							if (menu2 == 1) {// 목록
								es.listEventR();
							} else if (menu2 == 2) {// 읽기
								es.readEventR();
							} else if (menu2 == 3) {// 검색
								es.searchEventR();
							} else if (menu2 == 4) {// 글쓰기
								es.writeBoard();
							} else if (menu2 == 5) {// 글삭제
								es.deleteR();

							} else if (menu2 == 6) {
								break;

							}
						
						}
					}
					break;
				}
				break;
			case 7:
				System.out.println("종료합니다.");
				run = false;
				MemberService.memberInfo = null;
				break;
			}// end of switch

		} // end of while

	}

//	1제품관리 -- 1.제품등록 2.제품삭제3.상품목록 4.상품검색 -
//	2.회원관리-- 1.가입 2.회원정보조회 3.회원삭제
	private void run() {
		boolean run = true;
		while (run) {
			int menu2 = 0;
			System.out.println("1.상품 관리 2.회원관리 3.계정변경 4.배송관리 5.게시판 6.종료");
			menu = Integer.parseInt(s.nextLine());
			switch (menu) {
			case 1:
				while (menu2 != 99) {
					System.out.println("====================================");
					System.out.println("상품관리 메뉴로 들어갑니다.");
					System.out.println("====================================");
					System.out.println("1.상품등록 2.상품목록 3.상품검색 4.상품삭제 99.나가기");
					menu2 = Integer.parseInt(s.nextLine());
					if (menu2 == 1) {
						ps.insertProduct();// 상품등록
					} else if (menu2 == 2) {
						ps.listProduck();
					} else if (menu2 == 3) {
						ps.listProduckName();
					} else if (menu2 == 4) {
						ps.deleteProduck();// 상품삭제
					}
				}
				break;
			case 2:
				while (menu2 != 99) {
					System.out.println("====================================");
					System.out.println("회원 관리 메뉴로 들어갑니다.");
					System.out.println("====================================");
					System.out.println("1.회원가입 2.회원정보조회 3.회원삭제 99.나가기");
					menu2 = Integer.parseInt(s.nextLine());
					if (menu2 == 1) {
						ms.insertShip();// 가입
					} else if (menu2 == 2) {
						ms.memberInfo();// 조회
					} else if (menu2 == 3) {
						ms.deletememberM();// 삭제

					}
				}
				break;
			case 3:
				ms.doLogin();
				break;
			case 4:
				ms.delivery();
				break;
			case 5:
				while (true) {
					System.out.println("==============게시판==============");
					System.out.println("1.목록 2.읽기 3.검색 4.글쓰기 5.글삭제 6.종료");
					menu = Integer.parseInt(s.nextLine());
					if (menu == 1) {
						es.listEvent();
					} else if (menu == 2) {
						es.readEvent();
					} else if (menu == 3) {
						es.searchEvent();
					} else if (menu == 4) {
						es.writeBoard();
					} else if (menu == 5) {
						es.delete();

					} else if (menu == 6) {
						break;

					}

				}
				break;
			case 6:
				
				System.out.println("관리자메뉴를 종료합니다.");
				run = false;
				break;
			case 7:
				break;

			}
		}
	}
}
