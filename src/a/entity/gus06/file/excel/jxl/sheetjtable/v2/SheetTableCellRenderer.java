package a.entity.gus06.file.excel.jxl.sheetjtable.v2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import jxl.Cell;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.RGB;

public class SheetTableCellRenderer extends JLabel implements TableCellRenderer
{
    private Font defaultFont;
    private Color defaultBackground;
    private Color defaultForeground;
    
    public SheetTableCellRenderer()
    {
        setOpaque(true);
        defaultFont = getFont().deriveFont(Font.PLAIN);
        defaultBackground = Color.WHITE;
        defaultForeground = Color.BLACK;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
    	Cell cell = (Cell) value;
    	if(cell==null)
    	{
    		setText("");
    		setFont(defaultFont);
    		setForeground(defaultForeground);
    		setBackground(defaultBackground);
    		return this;
    	}
    	
    	CellFormat format = cell.getCellFormat();
    	
    	setBackground(background(format));
    	setForeground(foreground(format));
    	setText(cell.getContents());
    	setFont(font(format));
    	
    	if(isSelected) setBackground(Color.GRAY);
    	
        return this;
    }
    
    
    
    private Color background(CellFormat format)
    {
    	if(format==null) return defaultBackground;
    	Colour c = format.getBackgroundColour();
    	RGB rgb = c.getDefaultRGB();
    	return new Color(rgb.getRed(),rgb.getGreen(),rgb.getBlue());
    }
    
    private Color foreground(CellFormat format)
    {
    	if(format==null) return defaultForeground;
    	jxl.format.Font f = format.getFont();
    	Colour c = f.getColour();
    	RGB rgb = c.getDefaultRGB();
    	return new Color(rgb.getRed(),rgb.getGreen(),rgb.getBlue());
    }
    
    private Font font(CellFormat format)
    {
    	if(format==null) return defaultFont;
    	jxl.format.Font f = format.getFont();
    	int size = f.getPointSize();
    	Font font = defaultFont.deriveFont((float)size);
        if(f.isItalic()) font = font.deriveFont(Font.ITALIC);
        if(f.getBoldWeight()>500) font = font.deriveFont(Font.BOLD);
        return font;
    }
}
