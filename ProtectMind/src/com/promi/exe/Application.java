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
	Application() {
		run();
	}
	int menu;
	private void run() {
		while (true) {
			System.out.println("1.제품등록 2.제품삭제3.상품목록4.상품검색 5.가입6.로그인체크7.회원정보조회"
					+ "8.배송(관리자) 9.배송(고객)+10.게시판+11구매");
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
			case 7 :
				ms.memberInfo();
				break;
			case 8 :
				ms.delivery();
			break;
			case 9 :
				ms.deliveryMem();
			case 10 : 
				while(true) {
				System.out.println("==============게시판==============");
				System.out.println("1.목록 2.읽기 3.검색 4.글쓰기 5.글삭제 6.나가기");
				 menu = Integer.parseInt(s.nextLine());
				 if(menu==1) {
					 es.listEvent();
				 }else if(menu==2) {
					 es.readEvent();
				 }else if(menu==3) {
					es.searchEvent();
				 }else if(menu==4) {
					 es.writeBoard();
				 }else if(menu==5) {
					es.delete();
					 
				 }else if(menu==6) {
						break;
						 
					 }
				}
			case 11 :
				ps.buyProduct();
				
				
			}
		}
	}
}
