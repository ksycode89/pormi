package com.promi.exe;

import java.util.Scanner;

import com.promi.member.Member;
import com.promi.member.MemberService;
import com.promi.product.ProductService;

public class Application {
	Scanner s = new Scanner(System.in);
	ProductService ps = new ProductService();
	MemberService ms = new MemberService();
	Application() {
		run();
	}

	private void run() {
		while (true) {
			System.out.println("1.제품등록 2.제품삭제3.상품목록4.상품검색 5.가입6.로그인체크7.회원정보조회"
					+ "8배송(관리자) 9배송(고객)");
			int menu = Integer.parseInt(s.nextLine());
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
			}
		}
	}
}
