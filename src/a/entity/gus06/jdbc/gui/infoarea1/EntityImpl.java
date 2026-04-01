package a.entity.gus06.jdbc.gui.infoarea1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.Properties;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20150622";}


	private JPanel panel;
	private JTextArea area;

	private G holder;
	

	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}


	public Object i() throws Exception
	{return panel;}

	

	public void p(Object obj) throws Exception
	{
		holder = (G) obj;
		updateGui();
	}



	private void updateGui() throws Exception
	{
		area.setText("");
		
		if(holder==null) return;
		Connection cx = (Connection) holder.g();
		if(cx==null) return;
		
		DatabaseMetaData dbmd = cx.getMetaData();
		
		println("--- DRIVER MANAGER ---");
		println("Time out: "+DriverManager.getLoginTimeout());
		println("");
		println("--- CONNECTION INFOS ---");
		println("Auto-commit mode: "+cx.getAutoCommit());
		println("Catalog name: "+cx.getCatalog());
		println("Holdability: "+cx.getHoldability());
		println("Read only: "+cx.isReadOnly());
		println("");
		println("--- CLIENT INFO ---");
		println(cx.getClientInfo());
		println("");
		println("--- META DATA ---");
		println("");
		println("USER");
		println("User name: "+dbmd.getUserName());
		println("Max user name length: "+dbmd.getMaxUserNameLength());
		println("");
		println("URL");
		println("URL: "+dbmd.getURL());
		println("Max connections: "+dbmd.getMaxConnections());
		println("");
		println("JDBC");
		println("JDBC major version: "+dbmd.getJDBCMajorVersion());
		println("JDBC minor version: "+dbmd.getJDBCMinorVersion());
		println("");
		println("DRIVER");
		println("Driver name: "+dbmd.getDriverName());
		println("Driver version: "+dbmd.getDriverVersion());
		println("Driver major version: "+dbmd.getDriverMajorVersion());
		println("Driver minor version: "+dbmd.getDriverMinorVersion());
		println("");
		println("DATABASE");
		println("Database major version: "+dbmd.getDatabaseMajorVersion());
		println("Database minor version: "+dbmd.getDatabaseMinorVersion());
		println("Database product version: "+dbmd.getDatabaseProductVersion());
		println("Database product name: "+dbmd.getDatabaseProductName());
		println("");
		println("CATALOG");
		println("Catalog separator: "+dbmd.getCatalogSeparator() );
		println("Catalog term: "+dbmd.getCatalogTerm());
		println("Max catalog name length: "+dbmd.getMaxCatalogNameLength());
		println("Catalog at start: "+dbmd.isCatalogAtStart());
		println("");
		println("PROCEDURE");
		println("All procedures are callable: "+dbmd.allProceduresAreCallable());
		println("Procedure term: "+dbmd.getProcedureTerm());
		println("Max procedure name length: "+dbmd.getMaxProcedureNameLength());
		println("");
		println("SCHEMA");
		println("Schema term: "+dbmd.getSchemaTerm());
		println("Max schema name length: "+dbmd.getMaxSchemaNameLength());
		println("");
		println("STATEMENT");
		println("Max statement length: "+dbmd.getMaxStatementLength());
		println("Max statements: "+dbmd.getMaxStatements());
		println("");
		println("COLUMN");
		println("Max column name length: "+dbmd.getMaxColumnNameLength());
		println("Max columns in GROUP BY: "+dbmd.getMaxColumnsInGroupBy());
		println("Max columns in index: "+dbmd.getMaxColumnsInIndex());
		println("Max columns in ORDER BY: "+dbmd.getMaxColumnsInOrderBy());
		println("Max columns in SELECT: "+dbmd.getMaxColumnsInSelect());
		println("Max columns in table: "+dbmd.getMaxColumnsInTable());
		println("");
		println("ROW");
		println("Max row size: "+dbmd.getMaxRowSize());
		println("Does max row size include Blobs: "+dbmd.doesMaxRowSizeIncludeBlobs());
		println("");
		println("TABLE");
		println("Max table name length: "+dbmd.getMaxTableNameLength());
		println("Max tables in SELECT: "+dbmd.getMaxTablesInSelect());
		println("All tables are selectable: "+dbmd.allTablesAreSelectable());
		println("");
		println("SQL");
		println("SQL keywords: "+dbmd.getSQLKeywords());
		println("SQL state type: "+dbmd.getSQLStateType());
		println("Supports UNION: "+dbmd.supportsUnion());
		println("Supports UNION ALL: "+dbmd.supportsUnionAll());
		println("");
		println("FUNCTION");
		println("String functions: "+dbmd.getStringFunctions());
		println("Numeric functions: "+dbmd.getNumericFunctions());
		println("System functions: "+dbmd.getSystemFunctions());
		println("Time date functions: "+dbmd.getTimeDateFunctions());
		println("");
		println("Auto-commit failure closes all ResultSets: "+dbmd.autoCommitFailureClosesAllResultSets());
		println("Data definition causes transaction commit: "+dbmd.dataDefinitionCausesTransactionCommit());
		println("Data definition ignored in transactions: "+dbmd.dataDefinitionIgnoredInTransactions());
		println("Default transaction isolation: "+dbmd.getDefaultTransactionIsolation());
		println("Identifier quote string: "+dbmd.getIdentifierQuoteString());
		println("Max char literal length: "+dbmd.getMaxCharLiteralLength());
		println("Max cursor name length: "+dbmd.getMaxCursorNameLength());
		println("Max index length: "+dbmd.getMaxIndexLength());
		println("ResultSet holdability: "+dbmd.getResultSetHoldability());
		println("Search string escape: "+dbmd.getSearchStringEscape());
		println("Locators update copy: "+dbmd.locatorsUpdateCopy());
	}
	

	
	
	private void println(String m)
	{area.append(m+"\n");}
	
	
	private void println(Properties p)
	{
		Iterator it = p.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = p.getProperty(key);
			println(" - "+key+"="+value);
		}
	}
}
