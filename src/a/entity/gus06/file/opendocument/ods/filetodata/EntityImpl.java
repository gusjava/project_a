package a.entity.gus06.file.opendocument.ods.filetodata;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170304";}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		SpreadSheet spreadSheet = SpreadSheet.createFromFile(file);
		
		List list = new ArrayList();
		
		int count = spreadSheet.getSheetCount();
		for(int i=0;i<count;i++)
		{
			Sheet sheet = spreadSheet.getSheet(i);
			list.add(getSheetMap(sheet));
		}
		return list;
	}
	
	
	private Map getSheetMap(Sheet sheet)
	{
		Map map = new HashMap();
		map.put("name",sheet.getName());
		map.put("rows",getRows(sheet));
		return map;
	}
	
	
	private List getRows(Sheet sheet)
	{
		List list = new ArrayList();
		int count = sheet.getRowCount();
		for(int i=0;i<count;i++)
		list.add(getRow(sheet,i));
		
		return list;
	}
	
	
	private List getRow(Sheet sheet, int index)
	{
		List list = new ArrayList();
		int limit = getRowLimit(sheet,index);
		for(int i=0;i<limit;i++)
		list.add(sheet.getValueAt(i,index));
		return list;
	}
	
	
	private int getRowLimit(Sheet sheet, int index) {
		int columnCount = sheet.getColumnCount();
		for(int i=columnCount;i>0;i--)
			if(!sheet.getValueAt(i-1,index).equals("")) return i;
		return 0;
	}
}
