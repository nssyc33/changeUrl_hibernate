package testGitHub.io.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import testGitHub.io.Entity.TestVo;

@Service
public class XmlService {

	
	public List<TestVo> getDataList(){
	
		List resultList = new ArrayList<>();
		TestVo tv1 = new TestVo();
		tv1.setInputFirst("first 1");
		tv1.setInputSecond("second 1");
		
		TestVo tv2 = new TestVo();
		tv2.setInputFirst("first 2");
		tv2.setInputSecond("second 2");
		
		TestVo tv3 = new TestVo();
		tv3.setInputFirst("first 3");
		tv3.setInputSecond("second 3");
		resultList.add(tv1);
		resultList.add(tv2);
		resultList.add(tv3);
		
		return resultList;
	}
}
