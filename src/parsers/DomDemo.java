package parsers;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import parsers.dish.Dish;

public class DomDemo {
    public static void main(String[] args) throws SAXException, IOException {
//создание DOM-анализатора (Xerces)
        DOMParser parser = new DOMParser();
        parser.parse("dishes.xml");
        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        List<Dish> menu = new ArrayList<Dish>();
        NodeList dishNodes = root.getElementsByTagName("dish");
        Dish dish = null;
        for (int i = 0; i < dishNodes.getLength(); i++) {
            dish = new Dish();
            Element dishElement = (Element) dishNodes.item(i);
            dish.setId(Integer.parseInt(dishElement.getAttribute("id")));
            dish.setName(getSingleChild(dishElement,
                    "name").getTextContent().trim());
            dish.setDescription(getSingleChild(dishElement,
                    "description").getTextContent().trim());
            menu.add(dish);
        }
        for (Dish f: menu) {
            System.out.println(f.getId() + " \"" + f.getName() + "\" - " + f.getDescription());
        }
    }
    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}
