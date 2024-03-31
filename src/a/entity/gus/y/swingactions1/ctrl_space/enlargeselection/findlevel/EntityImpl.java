package a.entity.gus.y.swingactions1.ctrl_space.enlargeselection.findlevel;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240120";}

	public static final String SELECTION_EMPTY = "EMPTY";
	public static final String SELECTION_WORD1 = "WORD1";
	public static final String SELECTION_WORD2 = "WORD2";
	public static final String SELECTION_WORD3 = "WORD3";
	public static final String SELECTION_WORD4 = "WORD4";
	public static final String SELECTION_LINE = "LINE";
	public static final String SELECTION_BLOC = "BLOC";
	public static final String SELECTION_ALL = "ALL";

	public Object t(Object obj) throws Exception {
		JTextComponent comp = (JTextComponent) obj;

		String text = comp.getText();
		String selection = comp.getSelectedText();
		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();

		if (selection == null)
			return SELECTION_EMPTY;
		if (start == end)
			return SELECTION_EMPTY;
		if (start == 0 && end == text.length())
			return SELECTION_ALL;

		if (selection.matches("[a-zA-Z0-9_\\.#]+")) {
			if (selection.contains("#"))
				return SELECTION_WORD4;
			if (selection.contains("."))
				return SELECTION_WORD3;
			if (selection.contains("_"))
				return SELECTION_WORD2;
			return SELECTION_WORD1;
		}
		if (!selection.contains("\n"))
			return SELECTION_LINE;
		return SELECTION_BLOC;
	}
}