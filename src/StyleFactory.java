import java.awt.*;

public class StyleFactory {
    public static Style createStyles(int number) {
        return switch (number)
        {
            case 0 -> new Style (0, Color.red,   48, 20); // style voor item-level 0

            case 1 -> new Style(20, Color.blue,  40, 10); // style voor item-level 1

            case 2 -> new Style(50, Color.black, 36, 10); // style voor item-level 2

            case 3 -> new Style(70, Color.black, 30, 10); // style voor item-level 3

            case 4 -> new Style(90, Color.black, 24, 10); // style voor item-level 4
            default -> throw new IllegalArgumentException("The value of number is invalid, please try again");
        };
    }
}
