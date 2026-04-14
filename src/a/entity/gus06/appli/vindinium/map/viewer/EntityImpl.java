package a.entity.gus06.appli.vindinium.map.viewer;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170917";}

	
	private Service readFile;
	private Service parseJson;
	private Service generateJson;
	private Service updateData;
	
	private Service decodeBoard;
	private Service encodeBoard;
	
	private Service boardViewer;
	
	
	private JPanel panel;
	private JButton button_new;
	private JButton button_save;
	private JButton button_save_as;
	private JButton button_delete;
	
	private File file;
	
	private Map data;
	private Map game;
	private Map board;
	
	private int[][] tiles;



	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		parseJson = Outside.service(this,"gus.x.json.parse1");
		generateJson = Outside.service(this,"gus.x.json.build1");
		updateData = Outside.service(this,"gus06.appli.vindinium.map.viewer.updatedata");
		
		decodeBoard = Outside.service(this,"gus06.appli.vindinium.engine.board.decode");
		encodeBoard = Outside.service(this,"gus06.appli.vindinium.engine.board.encode");
		
		boardViewer = Outside.service(this,"gus06.appli.vindinium.map.viewer.board");
		
		button_new = new JButton("New");
		button_save = new JButton("Save");
		button_save_as = new JButton("Save as");
		button_delete = new JButton("Delete");
		
		buttonSetEnabled(false);
		
		button_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {create();}
		});
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {save();}
		});
		button_save_as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {saveAs();}
		});
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {delete();}
		});
		boardViewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {edited();}
		});
		
		
		JPanel p_buttons = new JPanel(new GridLayout(1,4,5,5));
		p_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		p_buttons.add(button_new);
		p_buttons.add(button_save);
		p_buttons.add(button_save_as);
		p_buttons.add(button_delete);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) boardViewer.i(),BorderLayout.CENTER);
		panel.add(p_buttons,BorderLayout.SOUTH);
	}


	public Object i() throws Exception
	{return panel;}
	

	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.exists())
		{
			data = null;
			game = null;
			board = null;
			tiles = null;
			boardViewer.p(null);
			
			buttonSetEnabled(false);
		}
		else
		{
			String text1 = (String) readFile.t(file);
			
			data = (Map) parseJson.t(text1);
			game = (Map) data.get(DATA.K_GAME);
			board = (Map) game.get(DATA.G_BOARD);
			
			String text2 = (String) board.get(DATA.B_TILES);
			tiles = (int[][]) decodeBoard.t(text2);
			boardViewer.p(tiles);
			
			buttonSetEnabled(true);
			button_save.setEnabled(false);
		}
	}
	
	
	private void buttonSetEnabled(boolean v)
	{
		button_save.setEnabled(v);
		button_save_as.setEnabled(v);
		button_delete.setEnabled(v);
	}
	
	
	private void edited()
	{
		button_save.setEnabled(true);
	}
	
	private void create()
	{
		try
		{
			String newName = JOptionPane.showInputDialog(null,"Enter map name:");
			if(newName==null || newName.equals("")) return;
			
			String size_ = JOptionPane.showInputDialog(null,"Enter map size:");
			if(size_==null || size_.equals("")) return;
			
			int size = Integer.parseInt(size_);
			if(size%2==1) size++;
			if(size<10) size = 10;
			
			file = new File(file.getParentFile(),"["+size+"]"+newName+".txt");
			
			generateDefaultTiles(size);
			printToFile();
		}
		catch(Exception e)
		{Outside.err(this,"saveAs()",e);}
	}
	
	private void save()
	{
		try
		{
			if(data==null || file==null) return;
			
			tiles = (int[][]) boardViewer.g();
			int size = tiles.length;
			if(!file.getName().startsWith("["+size+"]"))
			{
				JOptionPane.showMessageDialog(null,"Map size has changed.\nPlease, choose \"Save as\"");
				return;
			}
			
			printToFile();
			button_save.setEnabled(false);
		}
		catch(Exception e)
		{Outside.err(this,"save()",e);}
	}
	
	
	private void saveAs()
	{
		try
		{
			if(data==null || file==null) return;
			
			tiles = (int[][]) boardViewer.g();
			int size = tiles.length;
			
			String newName = JOptionPane.showInputDialog(null,"Enter map name:");
			if(newName==null || newName.equals("")) return;
			
			file = new File(file.getParentFile(),"["+size+"]"+newName+".txt");
			
			printToFile();
		}
		catch(Exception e)
		{Outside.err(this,"saveAs()",e);}
	}
	
	
	private void delete()
	{
		try
		{
			if(data==null || file==null) return;
			
			int r = JOptionPane.showConfirmDialog(null,"Are you sure to delete file: "+file.getName()+" ?");
			if(r!=JOptionPane.YES_OPTION) return;
			
			boolean d = file.delete();
			if(!d) throw new Exception("Failed to delete file: "+file);
			
			data = null;
			game = null;
			board = null;
			tiles = null;
			boardViewer.p(null);
			
			buttonSetEnabled(false);
		}
		catch(Exception e)
		{Outside.err(this,"delete()",e);}
	}
	
	
	private void generateDefaultTiles(int size) throws Exception
	{
		tiles = new int[size][size];
		for(int i=0;i<size;i++)
		for(int j=0;j<size;j++)
		{
			tiles[i][j] = 0;
		}
	}
	
	
	
	
	private void printToFile() throws Exception
	{
		String text1 = (String) encodeBoard.t(tiles);
		board.put(DATA.B_TILES,text1);
		
		updateData.p(new Object[]{data,tiles});
		
		String text2 = (String) generateJson.t(data);
		PrintStream p = new PrintStream(file);
		p.print(text2);
		p.close();
	}
}
