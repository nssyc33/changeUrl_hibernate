package testGitHub.io.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_url")
@SequenceGenerator(name="URL_SEQ_GENERATOR", sequenceName = "URL_SEQ", initialValue=1, allocationSize=1)
public class UrlData {

	@Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ORI_URL")
    private String oriUrl;


    @Column(name = "SUB_URL")
    private String subUrl;


    @Column(name = "SUB_KEY")
    private String subKey;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
