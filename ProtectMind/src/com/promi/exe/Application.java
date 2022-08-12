package com.promi.exe;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;

import com.promi.event.EvenService;
import com.promi.member.Member;
import com.promi.member.MemberDAO;
import com.promi.member.MemberService;
import com.promi.product.ProductService;

public class Application {
	
	Scanner s = new Scanner(System.in);
	ProductService ps = new ProductService();
	MemberService ms = new MemberService();
	EvenService es = new EvenService();
	LocalTime now = LocalTime.now();
	int menu;

//구독 구문 //
	public void check01() {
			
		
		try {
		int min = now.getMinute();
		if (MemberService.memberInfo.gettest_sub() < min) {
			System.out.println("구독이 만료되었습니다.");
			System.out.println("1. 구독연장 2.그만두기");
			int c = Integer.parseInt(s.nextLine());
			if (c == 1) {

				System.out.println("1. 일주일 2. 한달(30일) ");
				int num = Integer.parseInt(s.nextLine());
				if (num == 1 || num == 2) {
					if (MemberService.memberInfo.getSubDay() != null) {

						ps.subCheck(num);
					} else if (MemberService.memberInfo.getSubDay() == null) {

						ps.sub(num);

					}

				}
				System.out.println("구독완료");
			} else if (c == 2) {
				System.out.println("이용해주셔서 감사합니다.");
				MemberDAO.getInstance().subEnd(MemberService.memberInfo);
			}
		}
		} catch (Exception e) {
			System.out.println("바른값을 넣어주세요");
		}
	}

	public Application() {
		while (true) {
			try {
			System.out.println("1.로그인 2.회원가입 3.종료 4.선물주기");
			
			menu = Integer.parseInt(s.nextLine());
			if (menu == 1) {
				ms.doLogin();

				if (MemberService.memberInfo.getRoles() == 0) {
					check01();

					run2();
				} else if (MemberService.memberInfo.getRoles() == 1) {
					System.out.println("1.일반 2. 관리자");
					int menu2 = Integer.parseInt(s.nextLine());
					if (menu2 == 1) {
						run2();
					} else if (menu2 == 2) {
						run();
					}

				}

			} else if (menu == 2) {
				ms.insertShip();

			} else if (menu == 3) {
				System.out.println("종료합니다.");
				break;
			}else if (menu ==4 ) {
				int pre = (int)((Math.random()*20)+1);
				System.out.println(pre+"번!");
				System.out.println("======당첨자는=====");
				switch(pre) {
				case 1:
					System.out.println("    안희원씨");
					break;
				case 2:
					System.out.println("    김아현씨");
					break;
				case 3:
					System.out.println("    정다슬씨");
					break;
				case 4:
					System.out.println("    백나현씨");
					break;
				case 5:
					System.out.println("    정민선씨");
					break;
				case 6:
					System.out.println("    도소람씨");
					break;
				case 7:
					System.out.println("    손정빈씨");
					break;
				case 8:
					System.out.println("    백진희씨");
					break;
				case 9:
					System.out.println("    노은경씨");
					break;
				case 10:
					System.out.println("    김민지씨");
					break;
				case 11:
					System.out.println("    이준의씨");
					break;
				case 12:
					System.out.println("    진정욱씨");
					break;
				case 13:
					System.out.println("    김두영씨");
					break;
				case 14:
					System.out.println("    황용주씨");
					break;
				case 15:
					System.out.println("    전성하씨");
					break;
				case 16:
					System.out.println("    김준형씨");	
					break;
				case 17:
					System.out.println("    이주훈씨");
					break;
				case 18:
					System.out.println("    남순탁씨");
					break;
				case 19:
					System.out.println("    이현성씨");
					break;
				
				}
			
			}else if(menu == 99){
				System.out.println("=====================================");
				System.out.println("=====================================");
				System.out.println("=====================================");
				System.out.println("=====================================");
				t();
				System.out.println("=====================================");
				System.out.println("=====================================");
				System.out.println("=====================================");
				System.out.println("=====================================");
				
				
			}
			} catch (Exception e) {
				System.out.println("오타입니다.");
		}
		}
	}

	public void t () {
		String str="다음주 한주 잘보내시고 다담주에 뵈요";
		String[] strArray = str.split("");
		try {
			for (int i = 0; i < strArray.length; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.print(strArray[i]);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/// 일반//
	private void run2() {
		boolean run = true;
		while (run) {
			try {
			System.out.println("1.상품 목록 2.검색 3.정보조회(조회&구매목록&삭제) 4.구매 5.구독 6.배송확인 7.게시판 8.로그아웃 ");
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
			case 5:// 구ㅡ독
				System.out.println("====구독 페이지 입니다.====");
				System.out.println("1. 일주일 2. 한달(30일) 3.나가기 ");
				int num = Integer.parseInt(s.nextLine());
				if (num == 1 || num == 2) {
					if (MemberService.memberInfo.getSubDay() != null) {

						ps.subCheck(num);
					} else if (MemberService.memberInfo.getSubDay() == null) {

						ps.sub(num);

					}
					break;
				} else if (num == 3) {
					System.out.println("메뉴로 돌아갑니다.");
				}
			case 6://배송조회
				ms.deliveryMem();
				break;
				

			case 7:// 게시판
				while (true) {
					System.out.println("1.자유게시판 2.리뷰게시판 3.나가기");
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

						} else if (menu == 3) {
							System.out.println("메인 메뉴로 돌아갑니다.");
							break;
						}
					}
					break;
				}
				break;
			case 8:
				System.out.println("로그아웃합니다.");
				run = false;
				MemberService.memberInfo = null;
				break;
			}// end of switch

			} catch (Exception e) {
				System.out.println("바른값을 넣어주세요");
			}
		} // end of while
	}

//	1제품관리 -- 1.제품등록 2.제품삭제3.상품목록 4.상품검색 -
//	2.회원관리-- 1.가입 2.회원정보조회 3.회원삭제
	private void run() {
		boolean run = true;
		while (run) {
			try {
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
			} catch (Exception e) {
				System.out.println("바른값을 넣어주세요");
		}
	}
	}
}
