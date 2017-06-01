package src.model;

import java.time.LocalDate;

public class DriversLicense {
			
	private String type;
	
	private Integer number;
	
	private LocalDate issueDate;
	
	private LocalDate expiryDate;
	
	//photo?
	
	public DriversLicense(String licenseType, Integer licenseNumber){
		this.type = licenseType;
		this.number = licenseNumber;
		this.issueDate = LocalDate.now();
		this.expiryDate = LocalDate.now();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		//Check date is today or earlier
		this.issueDate = issueDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		//check date is today or later
		this.expiryDate = expiryDate;
	}

	public Integer getNumber() {
		return number;
	}

}
