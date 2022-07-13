package com.korea.updownTest;
import java.io.*;
public class C01FileClass {
	public static void main(String[] args) {
		// 1. 파일 디렉토리 여부 확인
		File tmp = new File("C:\\Users\\ASUS\\Documents\\upload");
		if(tmp.isFile()) {
			System.out.println("파일입니다.");
		}
		if(tmp.isDirectory()) {
			System.out.println("디렉토리입니다.");
		}
		System.out.println("------------------------------------");
		
		// 2. 파일 상대, 절대경로 확인
		System.out.println("Path : " + tmp.getPath());
		System.out.println("Path : " + tmp.getAbsolutePath());
		System.out.println("------------------------------------");

		// 3. 디렉토리 생성
		if(!tmp.exists()) {
			//tmp 디렉토리가 존재하지 않는다면 디렉토리 생성
			tmp.mkdirs();
			System.out.println("디렉토리 생성!");
		}
		
		// 4. 파일 목록 확인
		File[] list = tmp.listFiles();
		// 필터설정을 통해 특정한 파일만 가져올 수도 있다.
		for(int i=0; i<list.length; i++) {
			if(list[i].isFile()) {
				System.out.println("File(전체경로출력) : " + list[i]);
				System.out.println("File(이름만 출력) : " + list[i].getName());
			}
		}
		
		System.out.println("------------------------------------");

		// 5. 필터처리 : 원하는 파일만 가져오기
		// FilenameFilter에서 지정한 파일만 가져올것이다. dir 혹은 name에 대한 필터를 지정할 수 있다.
		File[] list2 = tmp.listFiles(new FilenameFilter() {
									// 익명객체. 이름없는 객체
			@Override
			public boolean accept(File dir, String name) {
				
				return name.contains(".pkt");
//				return name.endsWith("t");
			}
		});
		
		for(int i=0; i<list2.length; i++) {
			if(list2[i].isFile()) {
				System.out.println("File(전체경로출력) : " + list2[i]);
				System.out.println("File(이름만 출력) : " + list2[i].getName());
			}
		}
		
		
		
	}
}
