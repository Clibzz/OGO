package Style;

import java.awt.*;

public class StyleFactory
{
    private static Style[] styles;

    public static Style createStyle(int indent, Color color, int points, int leading)
    {
        return new Style(indent, color, points, leading);
    }
    public static void createStyles() {
        styles = new Style[5];
        styles[0] = createStyle(0, Color.red,   48, 20);
        styles[1] = createStyle(20, Color.blue,  40, 10);
        styles[2] = createStyle(50, Color.black, 36, 10);
        styles[3] = createStyle(70, Color.black, 30, 10);
        styles[4] = createStyle(90, Color.black, 24, 10);
    }

    public static Style getStyle(int level) {
        if (level >= styles.length) {
            level = styles.length - 1;
        }
        return styles[level];
    }
}
