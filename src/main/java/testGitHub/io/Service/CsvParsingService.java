package testGitHub.io.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CsvParsingService {

	public void ioParsing(){
		List<List<String>> resultList = new ArrayList<List<String>>();
		long start = System.currentTimeMillis();
        try(BufferedReader br = Files.newBufferedReader(Paths.get("D://TESTcsv.txt"))){
            Charset.forName("UTF-8");
            String line = "";
            System.out.println("값 : "+br.read());
            int count = 0 ;
            while((line = br.readLine()) != null){
                //CSV 1행을 저장하는 리스트
                List<String> tempList = new ArrayList<String>();
                String array[] = line.split(",");
                //배열에서 리스트 반환
                tempList = Arrays.asList(array);
//                System.out.println(tmpList);
                resultList.add(tempList);
                count = count + 1;
            }
            System.out.println("갯수: "+count);
        }catch(Exception e){
        	System.out.println("에러 : "+e.getMessage());
        	throw new RuntimeException();
        }finally {
        	long end = System.currentTimeMillis();
			System.out.println( "IO 실행 시간 : " + ( end - start )/1000.0 );
		}
	}
	
	public void nioParsing(){
		File inFile = new File("D://TESTcsv.txt");
		long start = System.currentTimeMillis();
		// Open File Channel
		List<List<String>> resultList = new ArrayList<List<String>>();
		try(FileInputStream	inStream = new FileInputStream(inFile);
			FileChannel inChannel = inStream.getChannel()){
			
			ByteBuffer bBuffer = ByteBuffer.allocate((int)inChannel.size());
			inChannel.read(bBuffer);
			String[] strBuffer = new String(((ByteBuffer)bBuffer.flip()).array()).split("\r\n");
			int count = 0;
			List<String> tempList = new ArrayList<String>();
			for( int i = 0; i < strBuffer.length; i++ ){
				tempList = Arrays.asList(strBuffer[i]);
				resultList.add(tempList);
				count = count + 1;
			}
			System.out.println("갯수: "+count);
		}
		catch(IOException e){
			throw new RuntimeException();
		}finally{
			long end = System.currentTimeMillis();
			System.out.println( "NIO 실행 시간 : " + ( end - start )/1000.0 );
		}
	}
	
	public void fileTransfer() throws IOException {
		String inPosition = "D://testCSV//TESTcsv.txt";
		String outPosition = "D://testCSV//TESTcsv_normal.txt";
		
		try(FileInputStream inputStream = new FileInputStream(inPosition);
			FileOutputStream outputStream = new FileOutputStream(outPosition);){
			long startTime = System.currentTimeMillis();
			int data=0;
			while ((data= inputStream.read()) != -1)
			{
				outputStream.write(data);
			}
			long stopTime = System.currentTimeMillis();
			System.out.println("normal file copy : "+(stopTime - startTime)/1000);//99
		} catch (Exception e) {
			System.out.println("오류");
			e.printStackTrace();
		}
	}
	
	public void fileTransfer_useBuffer() throws IOException {
		String inPosition = "D://testCSV//TESTcsv.txt";
		String outPosition = "D://testCSV//TESTcsv_useBuffer.txt";
		
		try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inPosition));
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outPosition));){
			long startTime = System.currentTimeMillis();
			int data=0;
			while ((data= inputStream.read()) != -1)
			{
				outputStream.write(data);
			}
			long stopTime = System.currentTimeMillis();
			System.out.println("useBuffer file copy : "+(stopTime - startTime)/1000);//99
		} catch (Exception e) {
			System.out.println("오류");
			e.printStackTrace();
		}
	}
	
	public void fileTransferNio() throws IOException {
		String inPosition = "D://testCSV//TESTcsv.txt";
		String outPosition = "D://testCSV//TESTcsv_nio.txt";
		
		try(FileInputStream inputStream = new FileInputStream(inPosition);
			FileOutputStream outputStream = new FileOutputStream(outPosition);) {
			long startTime = System.currentTimeMillis();
			FileChannel fis = inputStream.getChannel();
			FileChannel fos = outputStream.getChannel();
			fis.transferTo(0, (int)fis.size(), fos);
			long stopTime = System.currentTimeMillis();
			System.out.println("nio file copy : "+(stopTime - startTime)/1000);//
			
			
		} catch (Exception e) {
			System.out.println("오류");
			e.printStackTrace();
		}
	}
}
