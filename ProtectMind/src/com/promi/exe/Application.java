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
		System.out.println("1.로그인 2.회원가입 3.종료");

		System.out.println("1.일반 2. 관리자");
		menu = Integer.parseInt(s.nextLine());
		if (menu == 1) {
			ms.doLogin();
			run2();
		} else if (menu == 2) {
			run();
		}
	}

	private void run2() {

		while (true) {
			System.out.println("1.상품 목록 2.검색 3.정보조회(조회&구매목록&삭제) 4.구매(구매+상품) 5.배송확인 6 .게시판 7.종료(로그아웃) ");
			menu = Integer.parseInt(s.nextLine());
			switch (menu) {

			case 1://상품목록
				ps.listProduck();
				break;
			case 2://검색
				ps.listProduckName();
				break;
			case 3://정보조회 (일반조회 ,구매목록,계정 삭제)
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
					System.out.println("==============게시판==============");
					System.out.println("1.목록 2.읽기 3.검색 4.글쓰기 5.글삭제 6.나가기");
					menu = Integer.parseInt(s.nextLine());
					if (menu == 1) {//목록
						es.listEvent();
					} else if (menu == 2) {//읽기
						es.readEvent();
					} else if (menu == 3) {//검색
						es.searchEvent();
					} else if (menu == 4) {//글쓰기
						es.writeBoard();
					} else if (menu == 5) {//글삭제
						es.delete();

					} else if (menu == 6) {
						break;

					}

				}
				break;
			case 7 :
				System.out.println("종료합니다.");
			}//end of switch

		}//end of while

	}

	private void run() {
		while (true) {
			System.out.println("1.제품등록 2.제품삭제3.상품목록4.상품검색 5.가입 6.로그인 7.회원정보조회" + "8.배송(관리자) 9.사람 삭제10.게시판+11구매");
			menu = Integer.parseInt(s.nextLine());
			switch (menu) {
			case 1:
				ps.insertProduct();
				break;
			case 2:
				ps.deleteProduck();
				break;
			case 3:
				ps.listProduck();
				break;
			case 4:
				ps.listProduckName();
				break;
			case 5:
				ms.insertShip();
				break;
			case 6:
				ms.doLogin();
				break;
			case 7:
				ms.memberInfo();
				break;
			case 8:
				ms.delivery();
				break;
			case 9:
				//삭제
				ms.deletememberM();
				break;
			case 10:
				while (true) {
					System.out.println("==============게시판==============");
					System.out.println("1.목록 2.읽기 3.검색 4.글쓰기 5.글삭제 6.나가기");
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
			case 11:
				ps.buyProduct();
				break;

			}
		}
	}
}
