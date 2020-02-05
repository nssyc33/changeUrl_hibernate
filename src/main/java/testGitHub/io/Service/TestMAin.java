package testGitHub.io.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestMAin {
	
	public static void main(String[] args) throws Exception{
		
		FileInputStream inputStream = new FileInputStream("D://testCSV//TESTcsv.txt");
		FileOutputStream outputStream = new FileOutputStream("D://testCSV//TESTcsv.txt");
		
		BufferedInputStream inputBufferStream = new BufferedInputStream(new FileInputStream("D://testCSV//TESTcsv.txt"));
		BufferedOutputStream outputBufferStream = new BufferedOutputStream(new FileOutputStream("D://testCSV//TESTcsv.txt"));
		
		
		String as = null+"test";
		System.out.println(as);
		System.out.println(as.length());
	}

}
