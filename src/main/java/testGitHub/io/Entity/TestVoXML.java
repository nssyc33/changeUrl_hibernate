package testGitHub.io.Entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "tests")
public class TestVoXML {

	private String status;
	private List<TestVo> testList;
	
	public TestVoXML(){
		
	}
	
	public TestVoXML(String status, List<TestVo> testList){
		this.status = status;
		this.testList = testList;
	}
	
	@XmlElement
	public void setStatus(String status){
		this.status = status;
	}
	
	@XmlElement(name = "test")
	public void setTestList(List<TestVo> testList){
		this.testList = testList;
	}
	
	public String getStstus(){
		return this.status;
	}
	
	public List<TestVo> getTestList(){
		return this.testList;
	}
}
