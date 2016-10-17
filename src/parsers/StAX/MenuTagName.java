package parsers.StAX;

public enum MenuTagName {
    DISH_TYPE, PHOTO, NAME, DESCRIPTION, PRICE, PORTION, DISH, MENU;
    public static MenuTagName getElementTagName(String element) {
        switch (element) {
            case "dish-type":
                return DISH_TYPE;
            case "photo":
                return PHOTO;
            case "name":
                return NAME;
            case "description":
                return DESCRIPTION;
            case "price":
                return PRICE;
            case "portion":
                return PORTION;
            case "dish":
                return DISH;
            case "menu":
                return MENU;
            default:
                throw new EnumConstantNotPresentException(MenuTagName.class,
                        element);
        }
    }
}