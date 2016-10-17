package parsers;

import parsers.StAX.MenuTagName;
import parsers.dish.Dish;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
public class StaxDemo {
    public static void main(String[] args) throws FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new FileInputStream("dishes.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Dish> menu = process(reader);
            for (Dish dish : menu) {
                System.out.println(dish.getDishType() + " " + dish.getName());
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
    private static List<Dish> process(XMLStreamReader reader)
            throws XMLStreamException {
        List<Dish> menu = new ArrayList<Dish>();
        Dish dish = null;
        MenuTagName elementName = null;
        while (reader.hasNext()) {
// определение типа "прочтённого" элемента (тега)
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader
                            .getLocalName());
                    switch (elementName) {
                        case DISH:
                            dish = new Dish();
                            Integer id =
                                    Integer.parseInt(reader.getAttributeValue(
                                            null, "id"));
                            dish.setId(id);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case DISH_TYPE:
                            dish.setDishType(text);
                            break;
                        case PHOTO:
                            dish.setPhoto(text);
                            break;
                        case NAME:
                            dish.setName(text);
                            break;
                        case DESCRIPTION:
                            dish.setDescription(text);
                            break;
                        case PRICE:
                            dish.setPrice(text);
                            break;
                        case PORTION:
                            dish.setPortion(text);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader
                            .getLocalName());
                    switch (elementName) {
                        case DISH:
                            menu.add(dish);
                    }
            }
        }
        return menu;
    }
}