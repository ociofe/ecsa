package main.webapp.ecsa.dao;

public class ElementNote  {

	public int generator; // MHPGNO;
	public String programDescription; //MHPGDE
	public String modificationNumber;  //MHMONR
	public String description; //MHDESC
	public String textDescription; //MHT256
	public String release; // MHRELL                       
	public String fileDetails; //MHFILD
	public String programDetails; //MHPGMD               
	public String messageDetails; //MHMSGD               
	public String fieldDetails; //MHFLDD		                 
	public String menuOptionDetails; //MHMOPD           
	public String windowDetails; //MHWNDD               
	public String status; //MHSTAT
	public String internalName; //MHPSNA      
	public String analyserName; //MHANAL
	public String testerName; //MHTEST
	public int testedOnDate; //MHTDAT
	public int promotedToLiveOnDate; //MHPDAT
	public int textKey; //MHTXKY
	public String attachmentName; // MHATNA

			          
	
	public int getGenerator() {
		return generator;
	}
	public void setGenerator(int generator) {
		this.generator = generator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProgramDescription() {
		return programDescription;
	}
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	public String getModificationNumber() {
		return modificationNumber;
	}
	public void setModificationNumber(String modificationNumber) {
		this.modificationNumber = modificationNumber;
	}
	public String getTextDescription() {
		return textDescription;
	}
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(String fileDetails) {
		this.fileDetails = fileDetails;
	}
	public String getProgramDetails() {
		return programDetails;
	}
	public void setProgramDetails(String programDetails) {
		this.programDetails = programDetails;
	}
	public String getMessageDetails() {
		return messageDetails;
	}
	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}
	public String getFieldDetails() {
		return fieldDetails;
	}
	public void setFieldDetails(String fieldDetails) {
		this.fieldDetails = fieldDetails;
	}
	public String getMenuOptionDetails() {
		return menuOptionDetails;
	}
	public void setMenuOptionDetails(String menuOptionDetails) {
		this.menuOptionDetails = menuOptionDetails;
	}
	public String getWindowDetails() {
		return windowDetails;
	}
	public void setWindowDetails(String windowDetails) {
		this.windowDetails = windowDetails;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInternalName() {
		return internalName;
	}
	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
	public String getAnalyserName() {
		return analyserName;
	}
	public void setAnalyserName(String analyserName) {
		this.analyserName = analyserName;
	}
	public String getTesterName() {
		return testerName;
	}
	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}

	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public int getTestedOnDate() {
		return testedOnDate;
	}
	public void setTestedOnDate(int testedOnDate) {
		this.testedOnDate = testedOnDate;
	}
	public int getPromotedToLiveOnDate() {
		return promotedToLiveOnDate;
	}
	public void setPromotedToLiveOnDate(int promotedToLiveOnDate) {
		this.promotedToLiveOnDate = promotedToLiveOnDate;
	}
	public int getTextKey() {
		return textKey;
	}
	public void setTextKey(int textKey) {
		this.textKey = textKey;
	}
			
}
