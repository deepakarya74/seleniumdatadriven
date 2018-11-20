package tests;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ReadExcel {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	
	
	int arrivalPortRow;
	int arrivalPortCol;
	int shipmentLocationRow ;
	int shipmentColumn;
	String portName;
	int dataRowID;
	int dataColID;
	String[][] data; 


	public Sheet readExcel(String filePath,String fileName,String sheetName){

		
		filePath= filePath + fileName;
	
		
		try {
			FileInputStream ExcelFile = new FileInputStream(filePath);

			/*---When the file extension is XSLS then we use below class--*/
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);


		}catch(Exception e) {
			e.printStackTrace();
		}




		return ExcelWSheet;
	}

	public void UpdateArrivalPort() throws Exception {
		
		getCellPosition("SHIPMENT1");
		shipmentLocationRow = dataRowID;
		shipmentColumn = dataColID;
		
		getCellPosition("Chennai");
		arrivalPortRow = dataRowID;
		arrivalPortCol = dataColID;
		
		if(shipmentLocationRow==dataRowID) {
			setCellData(arrivalPortRow,arrivalPortCol, "Agra");
		}
		
		
	}

	//Return Cell Data
	public  static String getCellData(int RowNum, int ColNum) throws Exception {

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			int dataType = Cell.getCellType();

			if  (dataType == 3) {

				return "";

			}else{

				String CellData = Cell.getStringCellValue();

				return CellData;

			}
		}catch (Exception e){

			System.out.println(e.getMessage());

			throw (e);

		}

	}
	
	public void getCellPosition(String look) {
		
		//Row Count
				int rowNum = ExcelWSheet.getLastRowNum()+1;

				//Column count
				int colNum = ExcelWSheet.getRow(0).getLastCellNum();

				data = new String[rowNum][colNum];

				for(int i=0;i<rowNum;i++) {

					Row = ExcelWSheet.getRow(i);

					for(int j=0; j<colNum;j++) {
						Cell = Row.getCell(j);
						String value = Cell.toString();
						if(value.equals(look)) {
							dataRowID=i;
							dataColID=j;


						}
						

						data[i][j] = value;
					
					}
				}
		
		
	}
	public void setCellData(int row, int col,String value) {
		 Row = ExcelWSheet.getRow(row);
		 Cell = Row.getCell(col);
		Cell.setCellValue(value);
	}
	
	public Object[][] getSheetData(){
		
				try {
					
					
					int rowNum = ExcelWSheet.getLastRowNum()+1;

					//Column count
					int colNum = ExcelWSheet.getRow(0).getLastCellNum();

					data = new String[rowNum][colNum];
					
					for(int i=0;i<rowNum;i++) {

						Row = ExcelWSheet.getRow(i);

						for(int j=0; j<colNum;j++) {
							Cell = Row.getCell(j);
							String value = Cell.toString();
							

							data[i][j] = value;
							
						}
					}


				}catch(Exception e) {
					e.printStackTrace();
				}




				return data ;
	}

	


}
