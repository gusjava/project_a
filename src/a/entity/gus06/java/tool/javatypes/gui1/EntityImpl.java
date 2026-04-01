package a.entity.gus06.java.tool.javatypes.gui1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191112";}

	private JTable table;
	private JScrollPane scrollPane;

	public EntityImpl() throws Exception
	{
		String[] d = new String[]{"type","max value","min value","bit nb","byte nb"};
		String[][] c = new String[7][5];
		
		c[0][0] = Byte.class.getSimpleName();
		c[0][1] = Byte.toString(Byte.MAX_VALUE);
		c[0][2] = Byte.toString(Byte.MIN_VALUE);
		c[0][3] = ""+Byte.SIZE;
		c[0][4] = ""+Byte.BYTES;
		
		c[1][0] = Short.class.getSimpleName();
		c[1][1] = Short.toString(Short.MAX_VALUE);
		c[1][2] = Short.toString(Short.MIN_VALUE);
		c[1][3] = ""+Short.SIZE;
		c[1][4] = ""+Short.BYTES;
		
		c[2][0] = Character.class.getSimpleName();
		c[2][1] = ""+(int)Character.MAX_VALUE;
		c[2][2] = ""+(int)Character.MIN_VALUE;
		c[2][3] = ""+Character.SIZE;
		c[2][4] = ""+Character.BYTES;

		c[3][0] = Integer.class.getSimpleName();
		c[3][1] = Integer.toString(Integer.MAX_VALUE);
		c[3][2] = Integer.toString(Integer.MIN_VALUE);
		c[3][3] = ""+Integer.SIZE;
		c[3][4] = ""+Integer.BYTES;

		c[4][0] = Long.class.getSimpleName();
		c[4][1] = Long.toString(Long.MAX_VALUE);
		c[4][2] = Long.toString(Long.MIN_VALUE);
		c[4][3] = ""+Long.SIZE;
		c[4][4] = ""+Long.BYTES;
		
		c[5][0] = Float.class.getSimpleName();
		c[5][1] = Float.toString(Float.MAX_VALUE);
		c[5][2] = Float.toString(Float.MIN_VALUE);
		c[5][3] = ""+Float.SIZE;
		c[5][4] = ""+Float.BYTES;

		c[6][0] = Double.class.getSimpleName();
		c[6][1] = Double.toString(Double.MAX_VALUE);
		c[6][2] = Double.toString(Double.MIN_VALUE);
		c[6][3] = ""+Double.SIZE;
		c[6][4] = ""+Double.BYTES;

		table = new JTable(new DefaultTableModel(c,d));
		scrollPane = new JScrollPane(table);
	}

	public Object i() throws Exception
	{return scrollPane;}
}
