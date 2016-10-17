package parsers;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import parsers.dish.Dish;
import parsers.SAX.MenuSaxHandler;

public class SaxDemo {
    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("dishes.xml"));
// включение проверки действительности
        reader.setFeature("http://xml.org/sax/features/validation", true);
// включение обработки пространств имен
        reader.setFeature("http://xml.org/sax/features/namespaces", true);
// включение канонизации строк
        reader.setFeature("http://xml.org/sax/features/string-interning",
                true);
// отключение обработки схем
        reader.setFeature("http://apache.org/xml/features/validation/schema",
                false);
        List<Dish> menu = handler.getDishList();
        for (Dish dish : menu) {
            System.out.println(dish.getId() + " " + dish.getName());
        }
    }
}