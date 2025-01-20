package com.example.demo.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// 어플리케이션 실행시 폴더가 자동으로 생성됨

@Component
public class FileRunner implements CommandLineRunner  {

	// 파일경로는 application.properties 파일에서 수정하세요!
	@Value("${filepath}")
	String filepath;
	
	@Override
	public void run(String... args) throws Exception {

		File file = new File(filepath);
		
		if(file.exists()) {
//			System.out.println(file + " 폴더가 존재합니다.");
		} else {
			file.mkdir();
			System.out.println(file + " 폴더가 생성되었습니다.");
		}

	}

}
