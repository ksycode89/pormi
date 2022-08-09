package com.promi.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		System.out.println("==========================");
		System.out.println("[" + name + "]정보");
		System.out.println("==========================");

		for (Product pro : list) {
//			System.out.println(num+". 제품명 : "+pro.getProductName()+" | 제품 가격 : "+pro.getProductPrice()+"| 제품 종류 : "+pro.getProductKind()+ " |");		
			System.out.println(pro);
			num++;
		}
		System.out.println("==========================");

	}

}
//	총 판매목록(누적),
//	(재고체크 ),
//	회원조회(아이디, 구독정보,구매정보)
//	리뷰(확인,삭제) 	
