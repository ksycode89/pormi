package com.promi.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.promi.member.MemberDAO;
import com.promi.member.MemberService;
import com.promi.subscript.SubscriptDAO;

public class ProductService {
	Scanner s = new Scanner(System.in);

//	제품 등록

	public void insertProduct() {

		Product product = new Product();

		System.out.println("제품명");
		String name = s.nextLine();
		System.out.println("제품 가격");
		int price = Integer.parseInt(s.nextLine());
		System.out.println("제품 종류");
		String kind = s.nextLine();
//		System.out.println("평점");
//		int garde= Integer.parseInt(s.nextLine());
		System.out.println("제품 설명");
		String explain = s.nextLine();

		product.setProductName(name);
		product.setProductPrice(price);
		product.setProductKind(kind);
		product.setProductExplain(explain);

		int result = ProductDAO.getInstance().insertProduct(product);
		if (result == 1) {
			System.out.println("O");
		} else {
			System.out.println("X");
		}
	}

//제품삭제
	public void deleteProduck() {
		System.out.println("삭제할 제품명");
		String name = s.nextLine();

		int result = ProductDAO.getInstance().deleteProduck(name);

		if (result == 1) {
			System.out.println("O");
		} else {
			System.out.println("X");
		}

	}

//상품목록
	public void listProduck() {
		int num = 0;

		List<Product> list = ProductDAO.getInstance().listProduck();
		System.out.println("==========================");
		System.out.println("상품목록을 출력합니다.");
		System.out.println("==========================");
		for (Product pro : list) {
			System.out.println(num + ". 제품명 : " + pro.getProductName() + " | 제품 가격 : " + pro.getProductPrice()
					+ "| 제품 종류 : " + pro.getProductKind() + " |");
			num++;
		}
		System.out.println("==========================");

	}

	// 상품이름검색//
	public void listProduckName() {
		int num = 0;
		System.out.println("검색할 상품");
		String name = s.nextLine();
		List<Product> list = ProductDAO.getInstance().listProduckName(name);
		System.out.println("================================================================================");
		System.out.println("[검색 결과]");
		System.out.println("================================================================================");

		for (Product pro : list) {
//			System.out.println("["+num+"]");
//			System.out.println("제품명 : "+pro.getProductName()+" | 제품 가격 : "+pro.getProductPrice()+"| 제품 종류 : "+pro.getProductKind()+ " |");		
			System.out.println(num + "." + pro);
			num++;
			System.out.println("==============================================================================");
		}

	}

//	총 판매목록(누적),일반구매
	public void buyProduct() {
		System.out.println("구입할 상품의 명과 수량을 입력해주세요.");
		System.out.print("상품명 :");
		String name = s.nextLine();
		System.out.print("갯수 :");
		int num = Integer.parseInt(s.nextLine());
		int sum = 0;
		Product pro = ProductDAO.getInstance().buyList(name);
		if (pro.getProductName().equals(name)) {
			System.out.println("구매하는 물품은 " + pro.getProductName() + "이며" + " 수량은 " + num + "입니다.");
			sum = pro.getProductPrice() * num;
			System.out.println("총 결제 금액은 " + sum + "원 입니다.");
			System.out.println("결제를 시작합니다.");
			System.out.println("비밀번호를 입력해주세요");
			String pw = s.nextLine();
			if (MemberService.memberInfo.getConsumerPw().equals(pw)) {
				
				int result = MemberDAO.getInstance().updateDeliveryOrder(MemberService.memberInfo.getConsumerId());
				if (result == 1) {
					System.out.println("주문완료");
					// product에 총액 누적합산
					ProductDAO.getInstance().TotalMemPrice(sum, MemberService.memberInfo.getConsumerId());
					// 상품 제품 누적 부분
					SubscriptDAO.getInstance().updateInsence(pro.getProductName(), num, sum,
							MemberService.memberInfo.getConsumerId());
					// 배달 0-1로 번경 구문
					// product 에 판매수 누적
					ProductDAO.getInstance().TotalProNum(num, name);
					//product에 판매 액누적 
					ProductDAO.getInstance().TotalProMoney(sum,name);
				} else {
					System.out.println("주문 실패");
				}

//			if("인센스스틱".equals(pro.getProductKind())) {
//				SubscriptDAO.getInstance().updateInsence(MemberService.memberInfo.getConsumerId());
//			}else if ("차".equals(pro.getProductKind())) {
//				SubscriptDAO.getInstance().updateInsence(MemberService.memberInfo.getConsumerId());
//			}else if ("필로우미스트".equals(pro.getProductKind())) {
//				SubscriptDAO.getInstance().updateInsence(MemberService.memberInfo.getConsumerId());
//			}

			}else {System.out.println("잘못된 비밀 번호입니다.");}

		} else {
			System.out.println("올바른 상품명을 입력해 주세요.");
		}

	}

}

//	(재고체크 ),
//	회원조회(아이디, 구독정보,구매정보)
//	리뷰(확인,삭제) 	
