package a.entity.gus06.file.wav.split.todir.input;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JTextField;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20260206";}

	public static final double[] DEFAULT_PARAMS = new double[]{
		0.01, //windowSize 
		0.02, //silenceThreshold
		0.5,  //minSilenceLength
		0.3   //marginFrameNb
	};

	private Service dialogOkCancel;
	private Service formPanel;
	
	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private JTextField field4;

	public EntityImpl() throws Exception
	{
		dialogOkCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel");
		
		field1 = new JTextField(" ");
		field2 = new JTextField(" ");
		field3 = new JTextField(" ");
		field4 = new JTextField(" ");
		
		formPanel.v("windowSize", field1);
		formPanel.v("silenceThreshold", field2);
		formPanel.v("minSilenceLength", field3);
		formPanel.v("marginFrameNb", field4);
	}
	
	public Object g() throws Exception
	{
		field1.setText(""+DEFAULT_PARAMS[0]);
		field2.setText(""+DEFAULT_PARAMS[1]);
		field3.setText(""+DEFAULT_PARAMS[2]);
		field4.setText(""+DEFAULT_PARAMS[3]);
		
		dialogOkCancel.v("width","400");
		dialogOkCancel.v("height","200");
		
		boolean ok = dialogOkCancel.f(formPanel.i());
		return ok? buildParams():null;
	}
	
	private double[] buildParams()
	{
		Double d1 = fieldToDouble(field1);
		if(d1==null) return null;
		Double d2 = fieldToDouble(field2);
		if(d2==null) return null;
		Double d3 = fieldToDouble(field3);
		if(d3==null) return null;
		Double d4 = fieldToDouble(field4);
		if(d4==null) return null;
		
		return new double[]{d1,d2,d3,d4};
	}
	
	private Double fieldToDouble(JTextField l)
	{
		String s = l.getText();
		try{return Double.valueOf(s);}
		catch(NumberFormatException e) {return null;}
	}
}
