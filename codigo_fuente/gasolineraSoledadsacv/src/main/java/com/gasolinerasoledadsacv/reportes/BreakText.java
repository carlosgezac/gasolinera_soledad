package com.gasolinerasoledadsacv.reportes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class BreakText {

    public List<String> getLines(String text, int fontSize, PDFont pdFont, float width) throws IOException {
        List<String> lines = new ArrayList<>();
        int lastSpace = -1;
        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0) {
                spaceIndex = text.length();
            }
            String subString = text.substring(0, spaceIndex);
            float size = fontSize * pdFont.getStringWidth(subString) / 1000;
            if (size > width) {
                if (lastSpace < 0) {
                    lastSpace = spaceIndex;
                }
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();
                //System.out.printf("'%s' subs is line\n ", subString);
                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                lines.add(text);
                //System.out.printf("'%s' textis line\n text", text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }
        return lines;
    }
}
