package a.entity.gus06.file.excel.poi.filetodata;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180407";}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Workbook workbook = WorkbookFactory.create(file);
		int count = workbook.getNumberOfSheets();
		
		List list = new ArrayList();
		for(int i=0;i<count;i++)
		{
			Sheet sheet = workbook.getSheetAt(i);
			list.add(getSheetMap(sheet));
		}
		workbook.close();
		return list;
	}
	
	
	private Map getSheetMap(Sheet sheet)
	{
		Map map = new HashMap();
		map.put("name",sheet.getSheetName());
		map.put("rows",getRows(sheet));
		return map;
	}
	
	
	private List getRows(Sheet sheet)
	{
		List list = new ArrayList();
		Iterator<Row> it = sheet.rowIterator();
		while (it.hasNext()) list.add(getRow(it.next()));
		return list;
	}
	
	
	private List getRow(Row row)
	{
		List list = new ArrayList();
		Iterator<Cell> it = row.cellIterator();
		while (it.hasNext()) list.add(getCell(it.next()));
		return list;
	}
	
	
	private String getCell(Cell cell)
	{
		DataFormatter dataFormatter = new DataFormatter(true);
		return dataFormatter.formatCellValue(cell);
	}
}
