package a.entity.gus06.sys.dirdoubloon1.gui.gui1;

import a.framework.*;
import javax.swing.JLabel;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221218";}


	private Service buildSummary;
	private Service formPanel;
	private Service formatSize;
	
	private Map map;
	
	private JLabel labelGroupNb;
	private JLabel labelTotalNb;
	private JLabel labelTotalLost;
	

	public EntityImpl() throws Exception
	{
		buildSummary = Outside.service(this,"gus06.sys.dirdoubloon1.build.summary1");
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel");
		formatSize = Outside.service(this,"gus06.string.transform.format.datasize.en");
		
		labelGroupNb = new JLabel(" ");
		labelTotalNb = new JLabel(" ");
		labelTotalLost = new JLabel(" ");
		
		formPanel.v("group nb",labelGroupNb);
		formPanel.v("doubloon nb",labelTotalNb);
		formPanel.v("lost space",labelTotalLost);
	}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		if(map==null)
		{
			labelGroupNb.setText(" ");
			labelTotalNb.setText(" ");
			labelTotalLost.setText(" ");
			return;
		}
		
		Map summary = (Map) buildSummary.t(map);
		
		Long totalLost = (Long) summary.get("totalLost");
		Integer totalNb = (Integer) summary.get("totalNb");
		Integer groupNb = (Integer) summary.get("groupNb");
		
		String totalLostS = (String) formatSize.t(totalLost);
		
		labelGroupNb.setText(""+groupNb);
		labelTotalNb.setText(""+totalNb);
		labelTotalLost.setText(totalLostS);
	}
}