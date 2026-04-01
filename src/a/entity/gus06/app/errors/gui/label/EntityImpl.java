package a.entity.gus06.app.errors.gui.label;

import a.framework.*;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20190718";}


	private Service onDoubleClicked;
	private Service onDel;
	private Service showViewer;
	private List errors;
	
	private int readNb;
	private Icon icon;
	private Icon icon0;
	
	private JLabel label;

	
	public EntityImpl() throws Exception
	{
		onDoubleClicked = Outside.service(this,"gus06.swing.label.cust3.ondoubleclick.execute");
		onDel = Outside.service(this,"gus06.swing.comp.cust3.execute.del");
		showViewer = Outside.service(this,"gus06.exception.gui.viewer.show");
		
		errors = (List) Outside.resource(this,"errlist");
		icon = (Icon) Outside.resource(this,"icon#UTIL_error");
		icon0 = (Icon) Outside.resource(this,"icon#UTIL_error_blank");
		
		label = new JLabel("  ");
		
		E eShowDetails = (E) this::showDetails;
		E eClear = (E) this::clear;
		
		onDoubleClicked.p(new Object[]{label,eShowDetails});
		onDel.p(new Object[]{label,eClear});
		
		readNb = 0;
		updateGui();
		((S) errors).addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return label;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		label.setIcon(findIcon());
		label.setText(findText());
	}
	
	private Icon findIcon()
	{
		if(errors.isEmpty()) return null;
		return errors.size()>readNb ? icon : icon0;
	}
	
	private String findText()
	{
		int d = errors.size() - readNb;
		if(d==0) return "";
		return ""+d;
	}
	
	
	
	private void showDetails()
	{
		try
		{
			showViewer.e();
			clear();
		}
		catch(Exception e)
		{Outside.err(this,"showDetails()",e);}
	}
	
	
	private void clear() throws Exception
	{
		readNb = errors.size();
		updateGui();
	}
}