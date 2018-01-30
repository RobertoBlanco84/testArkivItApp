package com.arkivit.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class MetadataToExcelGUI {

	 private String excelFileName; //= "standard.xls";
	 private long fileSize;
	 private String filePath;
	 private String targetexcelFilepath;  // generated excel file location
	 private String sourceFolderPath; // source Directory
	 private int totalFileCount = 0, tCount= 0; // variable to store total file count
	 private int fileCount = 0; // variable to store required files count
	 private ArrayList<String> aList = new ArrayList<String>();
	 private ArrayList<String> filePathList = new ArrayList<String>();
	 private ArrayList<Long> sizeList = new ArrayList<Long>();
	 private ArrayList<File> fList;// = new ArrayList<File>();
	 private int folderCounter = 0;
	 private int filec = 0;
	 File myFile;
	 File myFile2;
	 File destinationFile;
	 private InputStreamReaderDecoder decoder = new InputStreamReaderDecoder();
	 //private static ArrayList<String> fileExtention = new ArrayList<String>();
	
	
	
	public MetadataToExcelGUI()
	{
		
	}
	
	public MetadataToExcelGUI(String excelFileName)
	{   
		
	}
	
	public void testMeth() {
		fList = new ArrayList<File>();
		//this.excelFileName = excelFileName + ".xls";
		listOfFilesAndDirectory(sourceFolderPath);
		testFunc();
	}
	
	
	public void listOfFilesAndDirectory(String folderPathName/*, ArrayList<File> fList*/)
	{
		File folder = new File(folderPathName);
		File[] listOfFilesInDirectory = folder.listFiles();

		for(File file : listOfFilesInDirectory)
		{
			
			if(file.isFile())
			{
				filec++;
				fList.add(file);
				System.out.println("Nr " + filec + " : " + file.getName());
			}
			else if(file.isDirectory())
			{
				listOfFilesAndDirectory(file.getAbsolutePath());
			}
		}

		System.out.println(filec);

	}

	public void testFunc() 
	{

		fList.sort(new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				String s1 = o1.getName().toLowerCase();
				String s2 = o2.getName().toLowerCase();
				final int s1Dot = s1.lastIndexOf('.');
				final int s2Dot = s2.lastIndexOf('.');
				// 
				if ((s1Dot == -1) == (s2Dot == -1)) { // both or neither
					s1 = s1.substring(s1Dot + 1);
					s2 = s2.substring(s2Dot + 1);
					return s1.compareTo(s2);
				} else if (s1Dot == -1) { // only s2 has an extension, so s1 goes first
					return -1;
				} else { // only s1 has an extension, so s1 goes second
					return 1;
				}

			}});

		try {

			if(!fList.isEmpty())
			{
				for (int numberOfFilesInFolder = 0; numberOfFilesInFolder < fList.size(); numberOfFilesInFolder++) {

					decoder.fileEncoder(fList.get(numberOfFilesInFolder).getParentFile().getAbsolutePath(), fList.get(numberOfFilesInFolder).getName());  

					String files = fList.get(numberOfFilesInFolder).getName();

					String fPath;//, testPath;
					fileSize = fList.get(numberOfFilesInFolder).length();


					fPath = fList.get(numberOfFilesInFolder).getParentFile().getAbsolutePath();
					fPath = fPath.replace(sourceFolderPath, "");

					aList.add(files);
					sizeList.add(fileSize);
					filePathList.add(fPath);
					decoder.getUtfList().add(decoder.getUtfString());
					//fileCount++;

					System.out.println("File size: " + fileSize);

				}
			}
			else
			{
				System.out.println("The list is empty");
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		createExcelFile();

	}

	public void createExcelFile() {

		File file = new File(targetexcelFilepath +"/"+ excelFileName);

		try {
			System.out.println("createExcelFile");
			WorkbookSettings wbSettings = new WorkbookSettings();
			WritableWorkbook workbook = Workbook.createWorkbook(file,
					wbSettings);
			workbook.createSheet("File Names", 0);
			System.out.println("Excel file is created in path -- "
					+ targetexcelFilepath);
			WritableSheet excelSheet = (WritableSheet) workbook.getSheet(0);
			if (!aList.isEmpty()) {
				for (int rowNumber = 0; rowNumber < aList.size(); rowNumber++) {

					/*CellView cell = workbook.getSheet(0).getColumnView(rowNumber);
		    	cell.setSize(14000);
		    	workbook.getSheet(0).setColumnView(0, cell);*/
						
					String tempString = aList.get(rowNumber);
					
					if(tempString.contains("å") || tempString.contains("ä") || tempString.contains("ö")
							|| tempString.contains("Å") || tempString.contains("Ä") || tempString.contains("Ö") 
							|| tempString.contains("Ü") || tempString.contains("ü"))
					{
						tempString = replaceIllegalChars(tempString);
					}
					
					
					//System.out.println(aList.get(rowNumber));

					String sizeInString = Objects.toString(sizeList.get(rowNumber), null); 
					String fileExtention = FilenameUtils.getExtension(aList.get(rowNumber));
					// FilenameUtils.get

					Label utfLabelName = new Label(4,0, "Teckenupps�ttning");
					Label utfLabel = new Label(4, rowNumber+1, decoder.getUtfList().get(rowNumber));

					Label filePathLabelName = new Label(6, 0, "FilePath(path,url)");

					Label filePathLabel = new Label(6, rowNumber+1, filePathList.get(rowNumber));

					Label label2 = new Label(0, 0, "Filename");

					Label fileTypeLabelName = new Label(1,0,"FileType");
					Label fileTypeLabel = new Label(1, rowNumber+1, fileExtention);
					Label fileSizeLabelName = new Label(2, 0, "File Size (in Bytes)");
					Label fileSizeLabel = new Label(2, rowNumber+1, sizeInString);
					Label label = new Label(0, rowNumber+1, tempString);


					excelSheet.setColumnView(0, getLargestString(aList));
					excelSheet.setColumnView(6, getLargestString(filePathList));
					excelSheet.addCell(filePathLabelName);
					excelSheet.addCell(filePathLabel);
					excelSheet.addCell(label2);
					excelSheet.addCell(fileTypeLabelName);
					excelSheet.addCell(fileTypeLabel);
					excelSheet.addCell(fileSizeLabelName);
					excelSheet.addCell(label);
					excelSheet.addCell(fileSizeLabel);
					excelSheet.addCell(utfLabelName);
					excelSheet.addCell(utfLabel);
				}
			} else {
				System.out.println("No matching files found");
			}
			workbook.write();
			workbook.close();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private String replaceIllegalChars(String string) {
		String newString = StringUtils.replaceEach (string, 
				new String[] {"å", "ä", "ö", "ü","Å", "Ä", "Ö", "Ü", " "}, 
				new String[] {"aa", "ae", "oe", "ue","AA", "AE", "OE", "UE", "_"});
		return newString;
	}

	private int getLargestString(ArrayList<String> stringList) {

		int largestString = stringList.get(0).length();
		int index = 0;

		for(int i = 0; i < stringList.size(); i++)
		{
			if(stringList.get(i).length() > largestString)
			{
				largestString = stringList.get(i).length();
				index = i;
			}
		}

		return largestString;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTargetexcelFilepath() {
		return targetexcelFilepath;
	}

	public void setTargetexcelFilepath(String targetexcelFilepath) {
		this.targetexcelFilepath = targetexcelFilepath;
	}

	public String getSourceFolderPath() {
		return sourceFolderPath;
	}

	public void setSourceFolderPath(String sourceFolderPath) {
		this.sourceFolderPath = sourceFolderPath;
	}

	public int getTotalFileCount() {
		return totalFileCount;
	}

	public void setTotalFileCount(int totalFileCount) {
		this.totalFileCount = totalFileCount;
	}

	public int gettCount() {
		return tCount;
	}

	public void settCount(int tCount) {
		this.tCount = tCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public ArrayList<String> getaList() {
		return aList;
	}

	public void setaList(ArrayList<String> aList) {
		this.aList = aList;
	}

	public ArrayList<String> getFilePathList() {
		return filePathList;
	}

	public void setFilePathList(ArrayList<String> filePathList) {
		this.filePathList = filePathList;
	}

	public ArrayList<Long> getSizeList() {
		return sizeList;
	}

	public void setSizeList(ArrayList<Long> sizeList) {
		this.sizeList = sizeList;
	}

	public ArrayList<File> getfList() {
		return fList;
	}

	public void setfList(ArrayList<File> fList) {
		this.fList = fList;
	}

	public int getFolderCounter() {
		return folderCounter;
	}

	public void setFolderCounter(int folderCounter) {
		this.folderCounter = folderCounter;
	}

	public int getFilec() {
		return filec;
	}

	public void setFilec(int filec) {
		this.filec = filec;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public File getMyFile2() {
		return myFile2;
	}

	public void setMyFile2(File myFile2) {
		this.myFile2 = myFile2;
	}

	public File getDestinationFile() {
		return destinationFile;
	}

	public void setDestinationFile(File destinationFile) {
		this.destinationFile = destinationFile;
	}
	
	
	
	 
	 
	 
}