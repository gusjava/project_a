package a.entity.gus06.sys.editor16x16.maingui;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.JColorChooser;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.awt.Font;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20250228";}
	
	public final static int NB = 16;
	public final static int WIDTH = 30;
	public final static int LENGTH = NB*WIDTH;
	public final static String TRANSPARENT = "255-255-255-0";
	
	public final static Dimension DIM = new Dimension(LENGTH, LENGTH);
	public final static Color SELECTION_COLOR = Color.BLACK;


	private Service tooltip;
	private Service buildSelector;
	private Service clipboardG;
	private Service clipboardG2;
	private Service clipboardP;
	private Service clearCopyPasteCut;
	private Service toRgba;
	private Service colorChooser;
	private Service imageToData;
	private Service encodeColor;
	private Service decodeColor;
	private Service brighten;
	private Service darken;
	private Service selectByColor;
	private Service chooseH;
	private Service chooseH2;
	private Service hueToTransform;
	private Service selectUp;
	private Service selectDown;
	private Service selectRight;
	private Service selectLeft;
	private Service selectAltUp;
	private Service selectAltDown;
	private Service selectAltRight;
	private Service selectAltLeft;
	private Service undoManager;
	private Service selectEnlarge;
	private Service labelOnClick;
	private Service enlargeSelectionToRect;
	
	private JTable table;
	private JLabel label1;
	private JLabel label2;
	private JPanel panel;
	
	private TableModel1 model;
	private TableCellRenderer1 renderer;
	private BufferedImage image;
	private String[][] data;
	private Object selector;
	private Set selection;
	
	
	public EntityImpl() throws Exception
	{
		clipboardG = Outside.service(this,"gus06.sys.editor16x16.clipboard.g");
		clipboardG2 = Outside.service(this,"gus06.sys.editor16x16.clipboard.g2");
		clipboardP = Outside.service(this,"gus06.sys.editor16x16.clipboard.p");
		encodeColor = Outside.service(this,"gus06.sys.editor16x16.c.encode1");
		decodeColor = Outside.service(this,"gus06.sys.editor16x16.c.decode1");
		selectByColor = Outside.service(this,"gus06.sys.editor16x16.select.bycolor");
		hueToTransform = Outside.service(this,"gus06.sys.editor16x16.hue.transform");
		brighten = Outside.service(this,"gus06.sys.editor16x16.c.brighten");
		darken = Outside.service(this,"gus06.sys.editor16x16.c.darken");
		chooseH = Outside.service(this,"gus06.sys.editor16x16.h.chooser");
		chooseH2 = Outside.service(this,"gus06.sys.editor16x16.h.chooser2");
		imageToData = Outside.service(this,"gus06.sys.editor16x16.t.imagetodata");
		clearCopyPasteCut = Outside.service(this,"gus06.swing.comp.action.clearcopypastecut");
		buildSelector = Outside.service(this,"gus06.swing.table.buildselector.selector1");
		colorChooser = Outside.service(this,"gus06.awt.color.chooser.choose1");
		tooltip = Outside.service(this,"gus06.swing.table.cust.tooltip1");
		toRgba = Outside.service(this,"gus06.find.bufferedimage.rgba");
		selectUp = Outside.service(this,"gus06.sys.editor16x16.select.d.up");
		selectDown = Outside.service(this,"gus06.sys.editor16x16.select.d.down");
		selectRight = Outside.service(this,"gus06.sys.editor16x16.select.d.right");
		selectLeft = Outside.service(this,"gus06.sys.editor16x16.select.d.left");
		selectAltUp = Outside.service(this,"gus06.sys.editor16x16.select.d.alt.up");
		selectAltDown = Outside.service(this,"gus06.sys.editor16x16.select.d.alt.down");
		selectAltRight = Outside.service(this,"gus06.sys.editor16x16.select.d.alt.right");
		selectAltLeft = Outside.service(this,"gus06.sys.editor16x16.select.d.alt.left");
		undoManager = Outside.service(this,"*gus06.sys.editor16x16.undomanager");
		selectEnlarge = Outside.service(this,"gus06.sys.editor16x16.select.enlarge");
		labelOnClick = Outside.service(this,"gus06.swing.label.cust3.onclick.execute");
		enlargeSelectionToRect = Outside.service(this,"gus06.sys.editor16x16.select.enlarge.rect");
		
		model = new TableModel1();
		renderer = new TableCellRenderer1();
		table = new JTable(model);
		
		table.setCellSelectionEnabled(true);
		table.setDefaultRenderer(String.class, renderer);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(WIDTH);
		
		table.setMinimumSize(DIM);
		table.setMaximumSize(DIM);
		table.setPreferredSize(DIM);
		
		clearCopyPasteCut.p(table);
		tooltip.p(table);
		
		label1 = new JLabel(" ");
		label2 = new JLabel(" ");
		
		E executeLabel2 = (E) this::enlargeSelectionToRect;
		labelOnClick.p(new Object[]{label2, executeLabel2});
		
		JPanel panelBottom = new JPanel(new BorderLayout());
		panelBottom.add(label1, BorderLayout.WEST);
		panelBottom.add(label2, BorderLayout.CENTER);
		
		
		JPanel panel0 = new JPanel(new BorderLayout());
		panel0.add(table, BorderLayout.CENTER);
		panel0.add(panelBottom, BorderLayout.SOUTH);
		
		panel = new JPanel();
		panel.add(panel0);
		
		selector = buildSelector.t(table);
		selection = (Set) ((G) selector).g();
		
		((S) selector).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{selectionChanged();}
		});
		
		table.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				
				if(e.isControlDown() && e.isAltDown())
				{
					if(code==KeyEvent.VK_UP) moveAltUp();
					else if(code==KeyEvent.VK_DOWN) moveAltDown();
					else if(code==KeyEvent.VK_LEFT) moveAltLeft();
					else if(code==KeyEvent.VK_RIGHT) moveAltRight();
					else if(code==KeyEvent.VK_V) pasteWithTransparency();
				}
				else if(e.isControlDown())
				{
					if(code==KeyEvent.VK_A) selectAll();
					else if(code==KeyEvent.VK_C) copy();
					else if(code==KeyEvent.VK_V) pasteWithoutTransparency();
					else if(code==KeyEvent.VK_X) cut();
					
					else if(code==KeyEvent.VK_Z) undo();
					else if(code==KeyEvent.VK_Y) redo();
					else if(code==KeyEvent.VK_U) undoAll();
					
					else if(code==KeyEvent.VK_E) selectByColors();
					else if(code==KeyEvent.VK_H) applyTransform();
					else if(code==KeyEvent.VK_R) applyTransform2();
					else if(code==KeyEvent.VK_W) applyWhiteBlack();
					else if(code==KeyEvent.VK_SPACE) enlargeSelection();
					
					else if(code==107) applyBrighten(); //PAV_PLUS
					else if(code==109) applyDarken(); //PAV_MINUS
					
					else if(code==KeyEvent.VK_UP) moveUp();
					else if(code==KeyEvent.VK_DOWN) moveDown();
					else if(code==KeyEvent.VK_LEFT) moveLeft();
					else if(code==KeyEvent.VK_RIGHT) moveRight();
				}
				else if(e.isAltDown())
				{
					if(code==KeyEvent.VK_A) reverseSelection();
					
					else if(code==KeyEvent.VK_UP) selectAltUp();
					else if(code==KeyEvent.VK_DOWN) selectAltDown();
					else if(code==KeyEvent.VK_LEFT) selectAltLeft();
					else if(code==KeyEvent.VK_RIGHT) selectAltRight();
				}
				else
				{
					if(code==KeyEvent.VK_F1) edit1();
					if(code==KeyEvent.VK_F2) edit2();
					if(code==KeyEvent.VK_F3) edit3();
					if(code==KeyEvent.VK_DELETE) delete();
					if(code==KeyEvent.VK_ESCAPE) clearSelection();
					
					else if(code==KeyEvent.VK_UP) selectUp();
					else if(code==KeyEvent.VK_DOWN) selectDown();
					else if(code==KeyEvent.VK_LEFT) selectLeft();
					else if(code==KeyEvent.VK_RIGHT) selectRight();
				}
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
		refreshLabel();
	}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		image = (BufferedImage) toRgba.t(obj);
		if(image==null) {reset();return;}
		
		if(image.getWidth(null)!=NB) throw new Exception("Invalid 16x16 image");
		if(image.getHeight(null)!=NB) throw new Exception("Invalid 16x16 image");
		
		data = (String[][]) imageToData.t(image);
		undoManager.e();
		undoManager.p(data);
		
		model.fireTableDataChanged();
		selection.clear();
		refreshLabel();
	}
	
	private String encodeColor(Color color)
	{
		try{return (String) encodeColor.t(color);}
		catch(Exception e){Outside.err(this,"encodeColor(Color)",e);}
		return null;
	}
	
	private Color decodeColor(String c)
	{
		try{return (Color) decodeColor.t(c);}
		catch(Exception e){Outside.err(this,"decodeColor(Color)",e);}
		return null;
	}
	
	
	private Set allData()
	{
		Set set = new HashSet();
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		set.add(data[i][j]);
		return set;
	}
	
	private Color[] colorArray()
	{
		List list = new ArrayList(allData());
		Collections.sort(list);
		Color[] array = new Color[list.size()];
		for(int i=0;i<array.length;i++)
		array[i] = decodeColor((String) list.get(i));
		return array;
	}
	
	
	
	
	
	private void reset() throws Exception
	{
		image = null;
		data = null;
		
		undoManager.e();
		model.fireTableDataChanged();
		selection.clear();
		refreshLabel();
	}
	
	
	private void refreshLabel()
	{
		if(selection.isEmpty())
		{
			label1.setText(" ");
			label2.setText(" ");
		}
		else
		{
			int[] n = getSelectionBounds();
			int size = selection.size();
			int rectW = n[1]-n[0]+1;
			int rectH = n[3]-n[2]+1;
			int rectArea = rectW*rectH;
			boolean rectFull = rectArea==size;
			
			label1.setText(size+"/256   ");
			label2.setText("["+rectW+"-"+rectH+"]");
			
			Font label2Font = label2.getFont();
			if(rectFull) label2Font = label2Font.deriveFont(Font.BOLD);
			else label2Font = label2Font.deriveFont(Font.PLAIN);
			label2.setFont(label2Font);
		}
	}
	
	
	private void selectionChanged()
	{
		try
		{
			table.repaint();
			refreshLabel();
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	private void selectAll()
	{
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		selection.add(i+"-"+j);
		selectionChanged();
	}
	
	
	private void clearSelection()
	{
		selection.clear();
		selectionChanged();
	}
	
	
	private void reverseSelection()
	{
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		{
			String key = i+"-"+j;
			if(selection.contains(key)) selection.remove(key);
			else selection.add(key);
		}
		selectionChanged();
	}
	
	private void enlargeSelection()
	{
		try
		{
			if(selection.isEmpty()) return;
			
			boolean changed = selectEnlarge.f(new Object[]{data,selection});
			if(changed) selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"enlargeSelection()",e);}
	}
	
	private void enlargeSelectionToRect()
	{
		try
		{
			if(selection.isEmpty()) return;
			
			boolean changed = enlargeSelectionToRect.f(selection);
			if(changed) selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"enlargeSelectionToRect()",e);}
	}


	private void selectByColors()
	{
		try
		{
			boolean changed = selectByColor.f(new Object[]{data,selection});
			if(changed) selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectByColors()",e);}
	}
	
	
	
	
	private void copy()
	{
		try
		{
			clipboardP.p(new Object[]{data, buildAppliableSet()});
		}
		catch(Exception e)
		{Outside.err(this,"copy()",e);}
	}
	
	private void pasteWithTransparency()
	{
		try
		{
			Object r = clipboardG.g();
			if(r==null) return;
			
			if(r instanceof String)
			{
				boolean changed = setColor((String) r);
				if(changed) afterEdition();
				return;
			}
			
			String[][] colors = (String[][]) r;
			
			boolean changed = false;
			for(int i=0;i<NB;i++)
			for(int j=0;j<NB;j++)
			if(colors[i][j]!=null)
			{
				if(setColor(i,j,colors[i][j])) changed = true;
			}
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"pasteWithTransparency()",e);}
	}
	
	private void pasteWithoutTransparency()
	{
		try
		{
			Object r = clipboardG2.g();
			if(r==null) return;
			
			if(r instanceof String)
			{
				boolean changed = setColor((String) r);
				if(changed) afterEdition();
				return;
			}
			
			String[][] colors = (String[][]) r;
			
			boolean changed = false;
			for(int i=0;i<NB;i++)
			for(int j=0;j<NB;j++)
			if(colors[i][j]!=null)
			{
				if(setColor(i,j,colors[i][j])) changed = true;
			}
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"pasteWithoutTransparency()",e);}
	}
	
	private void cut()
	{
		try
		{
			clipboardP.p(new Object[]{data, buildAppliableSet()});
			boolean changed = setColor(TRANSPARENT);
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"cut()",e);}
	}
	
	private void undo()
	{
		try
		{
			String[][] data_ = (String[][]) undoManager.r("undo");
			if(data_==null) return;
			
			rebuildImage(data_);
			table.repaint();
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"undo()",e);}
	}
	
	private void redo()
	{
		try
		{
			String[][] data_ = (String[][]) undoManager.r("redo");
			if(data_==null) return;
			
			rebuildImage(data_);
			table.repaint();
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"redo()",e);}
	}
	
	private void undoAll()
	{
		try
		{
			String[][] data_ = (String[][]) undoManager.r("undoAll");
			if(data_==null) return;
			
			rebuildImage(data_);
			table.repaint();
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"undoAll()",e);}
	}


	
	private void edit1()
	{
		try
		{
			Color initColor = decodeColor(selectedColor());
			Color newColor = JColorChooser.showDialog(null,"Choose color", initColor);
			if(newColor==null) return;
			
			boolean changed = setColor(encodeColor(newColor));
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"edit1()",e);}
	}
	
	private void edit2()
	{
		try
		{
			Color[] initColors = colorArray();
			Color newColor = (Color) colorChooser.t(initColors);
			if(newColor==null) return;
			
			boolean changed = setColor(encodeColor(newColor));
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"edit2()",e);}
	}
	
	private void edit3()
	{
		try
		{
			Color initColor = decodeColor(selectedColor());
			Color newColor = JColorChooser.showDialog(null,"Choose color", initColor);
			if(newColor==null) return;
			
			T t = (T) hueToTransform.t(newColor);
			if(t!=null) apply(t);
		}
		catch(Exception e)
		{Outside.err(this,"edit3()",e);}
	}
	
	private void delete()
	{
		try
		{
			boolean changed = setColor(TRANSPARENT);
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"delete()",e);}
	}
	
	
	
	
	
	// application d'une transformation pixel par pixel
	
	private void applyTransform()
	{
		try
		{
			T t = (T) chooseH.g();
			if(t!=null) apply(t);
		}
		catch(Exception e)
		{Outside.err(this,"applyTransform()",e);}
	}
	
	private void applyWhiteBlack()
	{
		try
		{
			boolean changed = setColor(encodeColor(Color.WHITE));
			if(changed) {afterEdition();return;}
			
			changed = setColor(encodeColor(Color.BLACK));
			if(changed) {afterEdition();return;}
		}
		catch(Exception e)
		{Outside.err(this,"applyWhiteBlack()",e);}
	}
	
	private void applyBrighten()
	{apply(brighten);}
	
	private void applyDarken()
	{apply(darken);}
	
	private void apply(T t)
	{
		apply(buildAppliableSet(), t);
	}
	
	private void apply(Set set, T t)
	{
		try
		{
			if(set.isEmpty()) return;
			Iterator it = set.iterator();
			Map map = new HashMap();
			
			boolean changed = false;
			while(it.hasNext())
			{
				String key = (String) it.next();
				int[] p = keyToPoint(key);
				String value = data[p[0]][p[1]];
				
				if(!map.containsKey(value)) map.put(value, t.t(value));
				String newValue = (String) map.get(value);
				if(setColor(key, newValue)) changed = true;
			}
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"apply(T)",e);}
	}
	
	
	
	
	
	// application d'une transformation sur une zone rectangulaire
	
	private void applyTransform2()
	{
		try
		{
			if(!isRect_appliableSet()) return;
			T t = (T) chooseH2.g();
			if(t!=null) apply2(t);
		}
		catch(Exception e)
		{Outside.err(this,"applyTransform2()",e);}
	}
	
	private void apply2(T t)
	{
		try
		{
			String[][] rectData = buildRectData();
			if(rectData==null) return;
			
			rectData = (String[][]) t.t(rectData);
			boolean changed = changeRectData(rectData);
			if(changed) afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"apply2(T)",e);}
	}
	
	
	// MOVE
	
	private void moveUp()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=0;i<NB;i++)
			for(int j=0;j<NB;j++)
			{
				String key = (i+1)+"-"+j;
				if(set.contains(key))
				{
					setColor(i,j,data[i+1][j]);
					setColor(i+1,j,TRANSPARENT);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveUp()",e);}
	}
	
	
	private void moveDown()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=NB-1;i>=0;i--)
			for(int j=0;j<NB;j++)
			{
				String key = (i-1)+"-"+j;
				if(set.contains(key))
				{
					setColor(i,j,data[i-1][j]);
					setColor(i-1,j,TRANSPARENT);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveDown()",e);}
	}
	
	
	private void moveRight()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=0;i<NB;i++)
			for(int j=NB-1;j>=0;j--)
			{
				String key = i+"-"+(j-1);
				if(set.contains(key))
				{
					setColor(i,j,data[i][j-1]);
					setColor(i,j-1,TRANSPARENT);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveRight()",e);}
	}
	
	
	private void moveLeft()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=0;i<NB;i++)
			for(int j=0;j<NB;j++)
			{
				String key = i+"-"+(j+1);
				if(set.contains(key))
				{
					setColor(i,j,data[i][j+1]);
					setColor(i,j+1,TRANSPARENT);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveLeft()",e);}
	}
	
	
	
	
	private void moveAltUp()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=0;i<NB;i++)
			for(int j=0;j<NB;j++)
			{
				String key = (i+1)+"-"+j;
				if(set.contains(key))
				{
					setColor(i,j,data[i+1][j]);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveAltUp()",e);}
	}
	
	
	private void moveAltDown()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=NB-1;i>=0;i--)
			for(int j=0;j<NB;j++)
			{
				String key = (i-1)+"-"+j;
				if(set.contains(key))
				{
					setColor(i,j,data[i-1][j]);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveAltDown()",e);}
	}
	
	
	private void moveAltRight()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=0;i<NB;i++)
			for(int j=NB-1;j>=0;j--)
			{
				String key = i+"-"+(j-1);
				if(set.contains(key))
				{
					setColor(i,j,data[i][j-1]);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveAltRight()",e);}
	}
	
	
	private void moveAltLeft()
	{
		try
		{
			Set newSelection = new HashSet();
			Set set = buildAppliableSet();
			
			for(int i=0;i<NB;i++)
			for(int j=0;j<NB;j++)
			{
				String key = i+"-"+(j+1);
				if(set.contains(key))
				{
					setColor(i,j,data[i][j+1]);
					newSelection.add(i+"-"+j);
				}
			}
			if(!selection.isEmpty())
			{
				selection.clear();
				selection.addAll(newSelection);
				selectionChanged();
			}
			afterEdition();
		}
		catch(Exception e)
		{Outside.err(this,"moveAltLeft()",e);}
	}


	// SELECT

	private void selectUp()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectUp.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectUp()",e);}
	}

	private void selectDown()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectDown.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectDown()",e);}
	}

	private void selectRight()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectRight.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectRight()",e);}
	}


	private void selectLeft()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectLeft.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectLeft()",e);}
	}

	private void selectAltUp()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectAltUp.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectAltUp()",e);}
	}

	private void selectAltDown()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectAltDown.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectAltDown()",e);}
	}

	private void selectAltRight()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectAltRight.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectAltRight()",e);}
	}

	private void selectAltLeft()
	{
		try
		{
			if(selection.isEmpty()) return;
			selectAltLeft.p(selection);
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"selectAltLeft()",e);}
	}



	
	
	
	// KEY TO POINT
	
	private int toInt(String s)
	{
		return Integer.parseInt(s);
	}
	
	private int[] keyToPoint(String key)
	{
		if(key==null) return null;
		String[] n = key.split("-");
		return new int[]{toInt(n[0]), toInt(n[1])};
	}
	
	
	// COLOR AT
	
	private String colorAt(int i, int j)
	{return data!=null ? data[i][j] : null;}
	
	private String colorAt(int[] p)
	{return p!=null ? colorAt(p[0], p[1]) : null;}
	
	private String colorAt(String key)
	{return colorAt(keyToPoint(key));}
	
	
	// SELECTED
	
	private String selectedKey()
	{
		if(selection.isEmpty()) return null;
		return (String) selection.iterator().next();
	}
	
	private String selectedColor()
	{return colorAt(selectedKey());}
	
	private Set selectedColors()
	{
		Set set = new HashSet();
		Iterator it = selection.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			set.add(colorAt(key));
		}
		return set;
	}
	
	
	// SET COLOR
	
	private boolean setColor(int[] p, String c)
	{return setColor(p[0], p[1], c);}
	
	private boolean setColor(String key, String c)
	{return setColor(keyToPoint(key), c);}
	
	private boolean setColor(String c)
	{return setColor(buildAppliableSet(), c);}
	
	private boolean setColor(Set set, String c)
	{
		if(image==null) return false;
		boolean changed = false;
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(setColor(key, c)) changed = true;
		}
		return changed;
	}
	
	private boolean setColor(int i, int j, String c)
	{
		if(data==null) return false;
		if(data[i][j].equals(c)) return false;
		
		data[i][j] = c;
		image.setRGB(j, i, decodeColor(c).getRGB());
		
		return true;
	}
	
	private void rebuildImage(String[][] data_) throws Exception
	{
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		{
			data[i][j] = data_[i][j];
			image.setRGB(j, i, decodeColor(data[i][j]).getRGB());
		}
	}
	
	
	
	private Set buildFullSet()
	{
		Set set = new HashSet();
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		set.add(i+"-"+j);
		return set;
	}
	
	private Set buildAppliableSet()
	{
		if(selection.isEmpty()) return buildFullSet();
		return selection;
	}
	
	
	private boolean isSelected(int row, int column)
	{return selection.contains(row+"-"+column);}
	
	
	// BOUNDS
	
	private int[] getSelectionBounds()
	{
		if(selection.isEmpty()) return null;
		
		int xMin = 16;
		int yMin = 16;
		
		int xMax = -1;
		int yMax = -1;
		
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		if(isSelected(i,j))
		{
			if(xMin>i) xMin = i;
			if(xMax<i) xMax = i;
			
			if(yMin>j) yMin = j;
			if(yMax<j) yMax = j;
		}
		return new int[]{xMin,xMax,yMin,yMax};
	}
	
	private int[] getAppliableBounds()
	{
		if(selection.isEmpty()) return new int[]{0,NB-1,0,NB-1};
		return getSelectionBounds();
	}
	
	
	// RECT
	
	private boolean isRect_appliableSet()
	{
		int[] n = getSelectionBounds();
		if(n==null) return true;
		
		int size = selection.size();
		int rectW = n[1]-n[0]+1;
		int rectH = n[3]-n[2]+1;
		int rectArea = rectW*rectH;
		return rectArea==size;
	}
	
	private int[] getRect_appliableSet()
	{
		int[] n = getSelectionBounds();
		if(n==null) return getAppliableBounds();
		
		int size = selection.size();
		int rectW = n[1]-n[0]+1;
		int rectH = n[3]-n[2]+1;
		int rectArea = rectW*rectH;
		return rectArea==size ? n : null;
	}
	
	private String[][] buildRectData()
	{
		int[] n = getRect_appliableSet();
		if(n==null) return null;
		
		int rectW = n[1]-n[0]+1;
		int rectH = n[3]-n[2]+1;
		
		String[][] rectData = new String[rectW][rectH];
		
		for(int i=0;i<rectW;i++)
		for(int j=0;j<rectH;j++)
		rectData[i][j] = data[i+n[0]][j+n[2]];
		
		return rectData;
	}
	
	private boolean changeRectData(String[][] rectData) throws Exception
	{
		int[] n = getRect_appliableSet();
		if(n==null) throw new Exception("RectData change not appliable width current selection");
		
		int rectW = n[1]-n[0]+1;
		int rectH = n[3]-n[2]+1;
		
		if(rectData.length!=rectW) 
			throw new Exception("Invalid rectData width for change: "+rectData.length);
		if(rectData[0].length!=rectH) 
			throw new Exception("Invalid rectData height for change: "+rectData[0].length);
		
		boolean changed = false;
		for(int i=0;i<rectW;i++)
		for(int j=0;j<rectH;j++)
		{
			String c = rectData[i][j];
			if(setColor(n[0]+i, n[2]+j, c)) changed = true;
		}
		return changed;
	}
	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getColumnCount() {return NB;}
		public int getRowCount() {return NB;}
		public boolean isCellEditable(int x, int y){return false;}
		public Class getColumnClass(int y){return String.class;}

		public Object getValueAt(int x, int y)
		{
			if(data==null) return null;
			return data[x][y];
		}
	}
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		private boolean selected = false;
		private Map cache = new HashMap();
		
		public TableCellRenderer1()
		{setOpaque(true);}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			String c = (String) value;
			setBackground(toColor(c));
			selected = isSelected(row,column);
			
			return this;
		}
		
		private Color toColor(String c)
		{
			if(!cache.containsKey(c)) 
			cache.put(c,decodeColor(c));
			return (Color) cache.get(c);
		}
		
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			Color c = getBackground();
			
			if(selected)
			{
				g2.setColor(SELECTION_COLOR);
				g2.fillRect(0, 0, getWidth(), getHeight());
			}
			
			if(c.getAlpha()<255)
			{
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gradientPaint = new GradientPaint(0, 0, Color.LIGHT_GRAY, getWidth(), getHeight(), c);
				g2.setPaint(gradientPaint);
			}
			else
			{
				g2.setColor(c);
			}
			
			if(selected) g2.fillRect(2, 2, getWidth()-4, getHeight()-4);
			else g2.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	
	
	
	
	private void afterEdition() throws Exception
	{
		undoManager.p(data);
		table.repaint();
		imageEdited();
	}
	
	private void imageEdited()
	{send(this,"imageEdited()");}
}