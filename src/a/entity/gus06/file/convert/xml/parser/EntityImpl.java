package a.entity.gus06.file.convert.xml.parser;

import a.framework.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

public class EntityImpl implements Entity, T {

	public String creationDate() { return "20251214"; }

	public EntityImpl() throws Exception {}

	public Object t(Object obj) throws Exception {
		if (!(obj instanceof String)) throw new Exception("Expected a String as input");
		String xml = (String) obj;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
		doc.getDocumentElement().normalize();

		return parseElement(doc.getDocumentElement());
	}

	// Chaque élément devient une Map<String,Object> à une seule clé : tagName -> contenu
	private Object parseElement(Element elem) {
		Map<String, Object> elementMap = new HashMap<>();

		// Attributs
		if (elem.hasAttributes()) {
			NamedNodeMap attrsNode = elem.getAttributes();
			Map<String, String> attrs = new HashMap<>();
			for (int i = 0; i < attrsNode.getLength(); i++) {
				Node attr = attrsNode.item(i);
				attrs.put(attr.getNodeName(), attr.getNodeValue());
			}
			elementMap.put("_attributes", attrs);
		}

		// Contenu enfant
		NodeList children = elem.getChildNodes();
		List<Object> childList = new ArrayList<>();
		StringBuilder textContent = new StringBuilder();

		for (int i = 0; i < children.getLength(); i++) {
			Node n = children.item(i);
			if (n instanceof Element) {
				Object child = parseElement((Element) n);
				// Chaque enfant est une Map à une seule clé
				childList.add(child);
			} else if (n.getNodeType() == Node.TEXT_NODE) {
				String text = n.getTextContent().trim();
				if (!text.isEmpty()) textContent.append(text);
			}
		}

		Map<String, Object> contentMap = new HashMap<>();

		if (childList.isEmpty()) {
			// Pas d'enfant élémentaire, on stocke juste le texte ou une chaîne vide
			contentMap.put(elem.getTagName(), textContent.toString());
		} else {
			// On stocke la liste des enfants, même si un seul
			contentMap.put(elem.getTagName(), childList);
		}

		// Si la Map a des attributs, on les fusionne dedans
		if (elementMap.containsKey("_attributes")) {
			Map<String, Object> merged = new HashMap<>();
			merged.putAll(contentMap);
			merged.put("_attributes", elementMap.get("_attributes"));
			return merged;
		}
		return contentMap;
	}
}