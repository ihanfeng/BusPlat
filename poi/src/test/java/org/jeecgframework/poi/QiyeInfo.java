package org.jeecgframework.poi;

import org.jeecgframework.poi.excel.annotation.Excel;
public class QiyeInfo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8773282778259076036L;

	@Excel(name = "企业全称")
    private String fullName;
	@Excel(name = "企业法人")
    private String legal;

    private String shortName;

    private String province;

    private String address;

    private String tel;
    
    private String fax;

    private String email;
    
    private String desc;
    @Excel(name = "企业LOGO",type=2)
    private String logo;

    private String species;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
    

    


    
    
}