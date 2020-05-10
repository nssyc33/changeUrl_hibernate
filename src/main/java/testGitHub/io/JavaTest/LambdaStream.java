package testGitHub.io.JavaTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LambdaStream {

	
	public static void main(String[] args) throws IOException {
//		LambdaInter a  =  (String aa ) -> "test";
//		System.out.println(a.testString("s"));
		List<Apple> asList = Arrays.asList(new Apple(),new Apple(),new Apple());
		
		prettyPrintApple(asList, new PrintImpl());
		prettyPrintApple(asList, new PrintSubImpl());
		
		System.out.println(processFile());
		
		
	}
	
	public static void prettyPrintApple(List<Apple> inventory, PrintInter p){
		for(Apple apple : inventory){
			String output = p.printsApple(apple);
			System.out.println(output);
		}
	}
	
	public static String processFile() throws IOException{
		try(BufferedReader br = new BufferedReader(new FileReader("D:\\TEST.txt"))){
			return br.readLine();
		}
	}
}