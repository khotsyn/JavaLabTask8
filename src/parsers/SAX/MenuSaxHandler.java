package parsers.SAX;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import parsers.dish.Dish;

public class MenuSaxHandler extends DefaultHandler {
    private List<Dish> dishList = new ArrayList<Dish>();
    private Dish dish;
    private StringBuilder text;
    public List<Dish> getDishList() {
        return dishList;
    }
    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }
    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
//        System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName
//                        + ", qName: " + qName);
        text = new StringBuilder();
        if (qName.equals("dish")){
            dish = new Dish();
            dish.setId((Integer.parseInt(attributes.getValue("id"))));
        }
    }
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch(tagName){
            case DISH_TYPE:
                dish.setDishType(text.toString());
                break;
            case PHOTO:
                dish.setPhoto(text.toString());
                break;
            case NAME:
                dish.setName(text.toString());
                break;
            case DESCRIPTION:
                dish.setDescription(text.toString());
                break;
            case PRICE:
                dish.setPrice(text.toString());
                break;
            case PORTION:
                dish.setPortion(text.toString());
                break;
            case DISH:
                dishList.add(dish);
                dish = null;
                break;
        }
    }
    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }
    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
        throw (exception);
    }
}