package main.webapp.ecsa.dao;

public class FileModel {
	
	public String fileName;
	
	public String filePath;

	public FileModel(String name, String path){
		this.fileName=name;
		this.filePath=path;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	

}
