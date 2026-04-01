package a.entity.gus06.swing.textcomp.buildlabel.hiddenchars;


import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190315";}


	private Service find;
	private Service delayed;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.swing.textcomp.text.hiddenchars.asstring");
		delayed = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
	}


	public Object t(Object obj) throws Exception
	{return new JLabel1((JTextComponent) obj);}


	private String findHidden(JTextComponent comp)
	{
		try{return (String) find.t(comp);}
		catch(Exception e)
		{Outside.err(this,"findHidden(JTextComponent)",e);}
		return "###";
	}


	public class JLabel1 extends JLabel implements ActionListener {
	
		private JTextComponent comp;
		
		public JLabel1(JTextComponent comp) throws Exception
		{
			super();
			setFont(getFont().deriveFont(Font.PLAIN));
			setForeground(Color.RED);
			
			this.comp = comp;
			S sup = (S) delayed.t(comp);
			sup.addActionListener(this);
			update();
		}
	
		private void update()
		{
			String s = findHidden(comp);
			if(s.length()>0) s = " "+s+" ";
			if(s.length()>40) s = s.substring(0,40)+"...";
			setText(s);
		}
	
		public void actionPerformed(ActionEvent e) {update();}
	}

}
