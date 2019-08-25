package testGitHub.io.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_url")
public class UrlData {

	@Id
    @Column(name = "IDS")
    @GeneratedValue
    private int ids;


    @Column(name = "ORI_URL")
    private String oriUrl;


    @Column(name = "SUB_URL")
    private String subUrl;


    @Column(name = "SUB_KEY")
    private String subKey;


	public int getIds() {
		return ids;
	}


	public void setIds(int ids) {
		this.ids = ids;
	}


	public String getOriUrl() {
		return oriUrl;
	}


	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}


	public String getSubUrl() {
		return subUrl;
	}


	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}


	public String getSubKey() {
		return subKey;
	}


	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}
}
