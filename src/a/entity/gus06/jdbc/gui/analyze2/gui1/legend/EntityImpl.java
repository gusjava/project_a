package a.entity.gus06.jdbc.gui.analyze2.gui1.legend;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Iterator;
import javax.swing.BorderFactory;
import java.sql.Connection;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20230227";}
	
	public static final String TYPE_BOOLEAN = "BOOLEAN";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_LONG = "LONG";
	public static final String TYPE_DOUBLE = "DOUBLE";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_STRING = "STRING";
	public static final String TYPE_LSTRING = "LSTRING";



	private Service hline;
	private Service typeToColor;
	

	private JPanel panel;
	
	private Connection cx;
	private String dbName;
	private String tableName;
	private Object holder;
	
	
	private JLabel labelCol;
	private JLabel labelRow;
	private JLabel labelNN;
	private JLabel labelPk;
	private JLabel labelUk;
	private JLabel labelFk;
	private JLabel labelFk0;
	private JLabel labelFk0e;
	private JLabel labelRefFk;
	
	private JLabel labelBoolean;
	private JLabel labelInteger;
	private JLabel labelDouble;
	private JLabel labelLong;
	private JLabel labelDate;
	private JLabel labelString;
	private JLabel labelLString;
	
	
	private int nbCol = 0;
	private int nbRow = 0;
	private int nbNN = 0;
	private int nbPk = 0;
	private int nbUk = 0;
	private int nbFk = 0;
	private int nbFk0 = 0;
	private int nbFk0e = 0;
	private int nbRefFk = 0;
	
	private int nbBoolean = 0;
	private int nbInteger = 0;
	private int nbDouble = 0;
	private int nbLong = 0;
	private int nbDate = 0;
	private int nbString = 0;
	private int nbLString = 0;



	public EntityImpl() throws Exception
	{
		hline = Outside.service(this,"gus06.swing.panel.build.hline");
		typeToColor = Outside.service(this,"gus06.jdbc.gui.analyze1.tool.datatypetocolor");
		
		labelCol = new JLabel(" ");
		labelRow = new JLabel(" ");
		labelNN = new JLabel(" ");
		labelPk = new JLabel(" ");
		labelUk = new JLabel(" ");
		labelFk = new JLabel(" ");
		labelFk0 = new JLabel(" ");
		labelFk0e = new JLabel(" ");
		labelRefFk = new JLabel(" ");
		
		labelBoolean = colorLabel(TYPE_BOOLEAN);
		labelInteger = colorLabel(TYPE_INTEGER);
		labelDouble = colorLabel(TYPE_DOUBLE);
		labelLong = colorLabel(TYPE_LONG);
		labelDate = colorLabel(TYPE_DATE);
		labelString = colorLabel(TYPE_STRING);
		labelLString = colorLabel(TYPE_LSTRING);
			
		JPanel panel1 = (JPanel) hline.t(new Object[]{
			labelCol, 
			labelRow,
			labelNN,
			labelPk,
			labelUk,
			labelFk,
			labelFk0,
			labelFk0e,
			labelRefFk
		});
		
		JPanel panel2 = (JPanel) hline.t(new Object[]{
			labelBoolean, 
			labelInteger, 
			labelDouble,
			labelLong,
			labelDate,
			labelString,
			labelLString
		});
		
		panel = new JPanel(new GridLayout(2,1,5,5));
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel.add(panel1);
		panel.add(panel2);
	}
		
	private JLabel colorLabel(String type) throws Exception
	{
		Color c = (Color) typeToColor.t(type);
		JLabel label = new JLabel(" ");
		label.setForeground(c);
		return label;
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		cx = (Connection) o[0];
		dbName = (String) o[1];
		tableName = (String) o[2];
		holder = o[3];
		
		nbCol = (Integer) ((R)holder).r("nbCol");
		nbRow = (Integer) ((R)holder).r("nbRow");
		nbNN = (Integer) ((R)holder).r("nbNN");
		nbPk = (Integer) ((R)holder).r("nbPk");
		nbUk = (Integer) ((R)holder).r("nbUk");
		nbFk = (Integer) ((R)holder).r("nbFk");
		nbFk0 = (Integer) ((R)holder).r("nbFk0");
		nbFk0e = (Integer) ((R)holder).r("nbFk0e");
		nbRefFk = (Integer) ((R)holder).r("nbRefFk");
		
		nbBoolean = (Integer) ((R)holder).r("nbBoolean");
		nbInteger = (Integer) ((R)holder).r("nbInteger");
		nbDouble = (Integer) ((R)holder).r("nbDouble");
		nbLong = (Integer) ((R)holder).r("nbLong");
		nbDate = (Integer) ((R)holder).r("nbDate");
		nbString = (Integer) ((R)holder).r("nbString");
		nbLString = (Integer) ((R)holder).r("nbLString");
		
		setText(labelCol, "cols", nbCol);
		setText(labelRow, "rows", nbRow);
		setText(labelNN, "NN", nbNN);
		setText(labelPk, "PK", nbPk);
		setText(labelUk, "UK", nbUk);
		setText(labelFk, "FK", nbFk);
		setText(labelFk0, "FK0", nbFk0);
		setText(labelFk0e, "FK0E", nbFk0e);
		setText(labelRefFk, "Ref", nbRefFk);
		
		setText(labelBoolean, "boolean", nbBoolean);
		setText(labelInteger, "int", nbInteger);
		setText(labelDouble, "double", nbDouble);
		setText(labelLong, "long", nbLong);
		setText(labelDate, "date", nbDate);
		setText(labelString, "string", nbString);
		setText(labelLString, "long string", nbLString);
	}
	
	
	private void setText(JLabel label, String key, int count)
	{label.setText("<html>&nbsp;<b>"+key+":&nbsp;</b>"+count+"&nbsp;&nbsp;</html>");}
}