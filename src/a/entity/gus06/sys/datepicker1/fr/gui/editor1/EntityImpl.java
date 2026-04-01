package a.entity.gus06.sys.datepicker1.fr.gui.editor1;

import a.framework.*;
import java.util.Date;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20160617";}


	private Service gui1;
	private Service today;
	private Service selected;
	
	private Service arrayToDate;
	private Service dateToArray;
	
	private JPanel panel;
	private JLabel labelToday;
	private JLabel labelSelected;
	


	public EntityImpl() throws Exception
	{
		gui1 = Outside.service(this,"*gus06.sys.datepicker1.fr.gui.panel");
		today = Outside.service(this,"*gus06.sys.datepicker1.fr.gui.label.today");
		selected = Outside.service(this,"*gus06.sys.datepicker1.fr.gui.label.selected");
		
		arrayToDate = Outside.service(this,"gus06.convert.intarraytodate");
		dateToArray = Outside.service(this,"gus06.convert.datetointarray3");
		
		labelToday = (JLabel) today.i();
		labelSelected = (JLabel) selected.i();
		
		JPanel p_bottom = new JPanel(new GridLayout(2,1));
		p_bottom.add(labelToday);
		p_bottom.add(labelSelected);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) gui1.i(),BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
		
		selected.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selectedClicked();}
		});
		today.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{todayClicked();}
		});
		gui1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("dataModified()")) gui1_dataModified();
				else if(s.equals("ymModified()")) gui1_ymModified();
			}
		});
	}
	
	
	public Object g() throws Exception
	{return arrayToDate.t(t1());}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{setData((int[]) dateToArray.t(obj));}
	
	
	
	private void setData(int[] data) throws Exception
	{
		gui1.p(data);
		selected.p(data);
		updateLabelFont();
	}
	
	private void setYM(int[] ym) throws Exception
	{
		gui1.v("ym",ym);
		updateLabelFont();
	}


	
	
	
	
	
	private void todayClicked()
	{
		try
		{
			setYM(t0());
		}
		catch(Exception e)
		{Outside.err(this,"todayClicked()",e);}
	}
	
	private void selectedClicked()
	{
		try
		{
			setYM(t1());
		}
		catch(Exception e)
		{Outside.err(this,"selectedClicked()",e);}
	}



	private void gui1_dataModified()
	{
		try
		{
			selected.p(t1());
			updateLabelFont();
		}
		catch(Exception e)
		{Outside.err(this,"gui1_dataModified()",e);}
	}



	private void gui1_ymModified()
	{
		try
		{
			updateLabelFont();
		}
		catch(Exception e)
		{Outside.err(this,"gui1_ymModified()",e);}
	}
	
	
	
	
	private void updateLabelFont() throws Exception
	{
		int[] t0 = t0();
		int[] t1 = t1();
		int[] ym = ym();
		
		if(equals(ym,t1)) initBold(labelSelected);
		else initPlain(labelSelected);
			
		if(equals(ym,t0)) initBold(labelToday);
		else initPlain(labelToday);
	}
	
	
	
	private int[] t0() throws Exception
	{return (int[]) today.g();}
	
	private int[] t1() throws Exception
	{return (int[]) gui1.g();}
	
	private int[] ym() throws Exception
	{return (int[]) gui1.r("ym");}
	
	
	private void initBold(JLabel l)
	{l.setFont(l.getFont().deriveFont(Font.BOLD));}
	
	private void initPlain(JLabel l)
	{l.setFont(l.getFont().deriveFont(Font.PLAIN));}
	
	
	private boolean equals(int[] a, int[] b)
	{
		if(a==null) return b==null;
		if(b==null) return a==null;
		
		for(int i=0;i<a.length;i++) if(a[i]!=b[i]) return false;
		return true;
	}
}
