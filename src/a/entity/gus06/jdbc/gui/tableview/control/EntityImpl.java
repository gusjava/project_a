package a.entity.gus06.jdbc.gui.tableview.control;

import a.framework.*;
import javax.swing.Action;
import java.sql.Connection;
import javax.swing.JTable;

public class EntityImpl extends S1 implements Entity, I, V, P {

	public String creationDate() {return "20190430";}


	public static final String ACTIONID_CELLS = "TABLE_cell#Cells";

	public static final String ACTIONID_CELL_EMPTY = "TABLE_cell_empty#Empty cell";
	public static final String ACTIONID_CELL_SETNULL = "TABLE_cell_setnull#Set cell to null";
	public static final String ACTIONID_CELL_EDIT = "TABLE_cell_edit#Edit cell";
	public static final String ACTIONID_CELL_COPY = "TABLE_cell_copy#Copy cell";
	public static final String ACTIONID_CELL_PASTE = "TABLE_cell_paste#Paste cell";
	public static final String ACTIONID_CELL_SQL_SELECT = "TABLE_cell_sql#SQL select from cell";
	public static final String ACTIONID_CELL_BROWSER = "TABLE_cell_next#Browse from cell";
	public static final String ACTIONID_CELL_WATCHER = "TABLE_cell_eye#Watch cell";
	
	public static final String ACTIONID_COLUMNS = "TABLE_column#Columns";

	public static final String ACTIONID_COLUMN_EMPTY = "TABLE_column_empty#Empty column";
	public static final String ACTIONID_COLUMN_EDIT = "TABLE_column_edit#Edit column";
	public static final String ACTIONID_COLUMN_COPY = "TABLE_column_copy#Copy column";
	public static final String ACTIONID_COLUMN_PASTE = "TABLE_column_paste#Paste column";
	public static final String ACTIONID_COLUMN_SQL_SELECT = "TABLE_cell_sql#SQL select from column";
	public static final String ACTIONID_COLUMN_SQL_ADD_FK = "TABLE_cell_sql2#SQL add FK from column";
	public static final String ACTIONID_COLUMN_WATCHER = "TABLE_column_eye#Watch latest cell";
	
	public static final String ACTIONID_ROWS = "TABLE_row#Rows";

	public static final String ACTIONID_ROW_EMPTY = "TABLE_row_empty#Empty row";
	public static final String ACTIONID_ROW_EDIT = "TABLE_row_edit#Edit row";
	public static final String ACTIONID_ROW_COPY = "TABLE_row_copy#Copy row";
	public static final String ACTIONID_ROW_PASTE = "TABLE_row_paste#Paste row";
	public static final String ACTIONID_ROW_SQL_SELECT = "TABLE_row_paste#SQL select from row";
	
	public static final String ACTIONID_ROW_DELETE = "TABLE_row_remove#Delete row";

	public static final String ACTIONID_TABLE_RELOAD = "TABLE_reload#Reload table";
	public static final String ACTIONID_TABLE_TRUNCATE = "TABLE_truncate#Truncate table";
	public static final String ACTIONID_TABLE_DELETE = "TABLE_delete#Delete table";
	public static final String ACTIONID_TABLE_COPY = "TABLE_copy#Copy table";
	public static final String ACTIONID_TABLE_SQL_SELECT = "TABLE_sql#SQL select from table";



	private Service actionBuilder;
	private Service barHolder;
	
	private Service cellEmpty;
	private Service cellSetNull;
	private Service cellEdit;
	private Service cellCopy;
	private Service cellPaste;
	private Service cellSqlSelect;
	private Service cellBrowser;
	private Service cellWatcher;
	
	private Service columnEmpty;
	private Service columnEdit;
	private Service columnCopy;
	private Service columnPaste;
	private Service columnSqlSelect;
	private Service columnSqlAddFK;
	private Service columnWatcher;
	
	private Service rowEmpty;
	private Service rowEdit;
	private Service rowCopy;
	private Service rowPaste;
	private Service rowSqlSelect;
	private Service rowDelete;
	
	private Service tableReload;
	private Service tableTruncate;
	private Service tableDelete;
	private Service tableCopy;
	private Service tableSqlSelect;



	private JTable table;
	private Object data;
	
	
	
	public EntityImpl() throws Exception
	{
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		barHolder = Outside.service(this,"*gus06.swing.toolbar.doublebar1");
		
		cellEmpty = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.empty");
		cellSetNull = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.setnull");
		cellEdit = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.edit");
		cellCopy = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.copy");
		cellPaste = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.paste");
		cellSqlSelect = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.sql.select");
		cellBrowser = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.browser");
		cellWatcher = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.watcher");
		
		columnEmpty = Outside.service(this,"gus06.jdbc.gui.tableview.perform.column.empty");
		columnEdit = Outside.service(this,"gus06.jdbc.gui.tableview.perform.column.edit");
		columnCopy = Outside.service(this,"gus06.jdbc.gui.tableview.perform.column.copy");
		columnPaste = Outside.service(this,"gus06.jdbc.gui.tableview.perform.column.paste");
		columnSqlSelect = Outside.service(this,"gus06.jdbc.gui.tableview.perform.column.sql.select");
		columnSqlAddFK = Outside.service(this,"gus06.jdbc.gui.tableview.perform.column.sql.add.fk");
		columnWatcher = Outside.service(this,"gus06.jdbc.gui.tableview.perform.column.watcher");
		
		rowEmpty = Outside.service(this,"gus06.jdbc.gui.tableview.perform.row.empty");
		rowEdit = Outside.service(this,"gus06.jdbc.gui.tableview.perform.row.edit");
		rowCopy = Outside.service(this,"gus06.jdbc.gui.tableview.perform.row.copy");
		rowPaste = Outside.service(this,"gus06.jdbc.gui.tableview.perform.row.paste");
		rowSqlSelect = Outside.service(this,"gus06.jdbc.gui.tableview.perform.row.sql.select");
		rowDelete = Outside.service(this,"gus06.jdbc.gui.tableview.perform.row.delete");
		
		tableReload = Outside.service(this,"gus06.jdbc.gui.tableview.perform.table.reload");
		tableTruncate = Outside.service(this,"gus06.jdbc.gui.tableview.perform.table.truncate");
		tableDelete = Outside.service(this,"gus06.jdbc.gui.tableview.perform.table.delete");
		tableCopy = Outside.service(this,"gus06.jdbc.gui.tableview.perform.table.copy");
		tableSqlSelect = Outside.service(this,"gus06.jdbc.gui.tableview.perform.table.sql.select");
		
		
		start(ACTIONID_CELLS);
		
		add(ACTIONID_CELL_EMPTY,this::cellEmpty);
		add(ACTIONID_CELL_SETNULL,this::cellSetNull);
		add(ACTIONID_CELL_EDIT,this::cellEdit);
		add(ACTIONID_CELL_COPY,this::cellCopy);
		add(ACTIONID_CELL_PASTE,this::cellPaste);
		add(ACTIONID_CELL_SQL_SELECT,this::cellSqlSelect);
		add(ACTIONID_CELL_BROWSER,this::cellBrowser);
//		add(ACTIONID_CELL_WATCHER,this::cellWatcher);
		
		start(ACTIONID_COLUMNS);
		
		add(ACTIONID_COLUMN_EMPTY,this::columnEmpty);
		add(ACTIONID_COLUMN_EDIT,this::columnEdit);
		add(ACTIONID_COLUMN_COPY,this::columnCopy);
		add(ACTIONID_COLUMN_PASTE,this::columnPaste);
		add(ACTIONID_COLUMN_SQL_SELECT,this::columnSqlSelect);
		add(ACTIONID_COLUMN_SQL_ADD_FK,this::columnSqlAddFK);
		
		start(ACTIONID_ROWS);
		
		add(ACTIONID_ROW_EMPTY,this::rowEmpty);
		add(ACTIONID_ROW_EDIT,this::rowEdit);
		add(ACTIONID_ROW_COPY,this::rowCopy);
		add(ACTIONID_ROW_PASTE,this::rowPaste);
		add(ACTIONID_ROW_SQL_SELECT,this::rowSqlSelect);
		separator();
		add(ACTIONID_ROW_DELETE,this::rowDelete);
		
		barHolder.v("end",null);
		separator();
		
		add(ACTIONID_TABLE_RELOAD,this::tableReload);
		add(ACTIONID_TABLE_TRUNCATE,this::tableTruncate);
		add(ACTIONID_TABLE_DELETE,this::tableDelete);
		add(ACTIONID_TABLE_COPY,this::tableCopy);
		add(ACTIONID_TABLE_SQL_SELECT,this::tableSqlSelect);
	}
	
	
	
	private void start(String key) throws Exception
	{barHolder.v("start",key);}
	
	
	private void separator() throws Exception
	{barHolder.v("separator",null);}
	
	private void add(String key, E execute) throws Exception
	{
		Action action = (Action) actionBuilder.t(new Object[]{key,execute});
		barHolder.v("action",action);
	}
	
	
	
	public Object i() throws Exception
	{return barHolder.i();}
	
	
	public void p(Object obj) throws Exception
	{data = obj;}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("table")) {table = (JTable) obj;return;}
		if(key.equals("perform")) {perform((String) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void perform(String action) throws Exception
	{
		if(action.equals("cellEmpty")) 		cellEmpty();
		else if(action.equals("cellSetNull"))	cellSetNull();
		else if(action.equals("cellEdit"))	cellEdit();
		else if(action.equals("cellCopy"))	cellCopy();
		else if(action.equals("cellPaste"))	cellPaste();
		else if(action.equals("cellSqlSelect"))	cellSqlSelect();
		else if(action.equals("cellBrowser"))	cellBrowser();
		else if(action.equals("cellWatcher"))	cellWatcher();
		
		else if(action.equals("columnEmpty"))	columnEmpty();
		else if(action.equals("columnEdit"))	columnEdit();
		else if(action.equals("columnCopy"))	columnCopy();
		else if(action.equals("columnPaste"))	columnPaste();
		else if(action.equals("columnSqlSelect"))columnSqlSelect();
		else if(action.equals("columnSqlAddFK"))	columnSqlAddFK();
		
		else if(action.equals("rowEmpty"))	rowEmpty();
		else if(action.equals("rowEdit"))	rowEdit();
		else if(action.equals("rowCopy"))	rowCopy();
		else if(action.equals("rowPaste"))	rowPaste();
		else if(action.equals("rowSqlSelect"))	rowSqlSelect();
		else if(action.equals("rowDelete"))	rowDelete();
		
		else if(action.equals("tableReload"))	tableReload();
		else if(action.equals("tableTruncate"))	tableTruncate();
		else if(action.equals("tableDelete"))	tableDelete();
		else if(action.equals("tableCopy"))	tableCopy();
		else if(action.equals("tableSqlSelect"))	tableSqlSelect();
		
		else throw new Exception("Unsupported action: "+action);
	}

	

	private void perform(F f)
	{
		try
		{
			boolean done = f.f(new Object[]{table,data});
			if(done) modified();
		}
		catch(Exception e)
		{Outside.err(this,"perform(F)",e);}
	}
	
	
	private void modified()
	{send(this,"modified()");}
	
	
	
	
	private void cellEmpty()	{perform(cellEmpty);}
	private void cellSetNull()	{perform(cellSetNull);}
	private void cellEdit()		{perform(cellEdit);}
	private void cellCopy()		{perform(cellCopy);}
	private void cellPaste()	{perform(cellPaste);}
	private void cellSqlSelect()	{perform(cellSqlSelect);}
	private void cellBrowser()	{perform(cellBrowser);}
	private void cellWatcher()	{perform(cellWatcher);}
	
	private void columnEmpty()	{perform(columnEmpty);}
	private void columnEdit()	{perform(columnEdit);}
	private void columnCopy()	{perform(columnCopy);}
	private void columnPaste()	{perform(columnPaste);}
	private void columnSqlSelect()	{perform(columnSqlSelect);}
	private void columnSqlAddFK()	{perform(columnSqlAddFK);}
	
	private void rowEmpty()		{perform(rowEmpty);}
	private void rowEdit()		{perform(rowEdit);}
	private void rowCopy()		{perform(rowCopy);}
	private void rowPaste()		{perform(rowPaste);}
	private void rowSqlSelect()	{perform(rowSqlSelect);}
	private void rowDelete()	{perform(rowDelete);}
	
	private void tableReload()	{perform(tableReload);}
	private void tableTruncate()	{perform(tableTruncate);}
	private void tableDelete()	{perform(tableDelete);}
	private void tableCopy()	{perform(tableCopy);}
	private void tableSqlSelect()	{perform(tableSqlSelect);}
}