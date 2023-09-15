package com.formatter;

import com.formatter.model.Node;
import com.formatter.model.Tree;
import java.util.ArrayList;
import java.util.List;

import static com.formatter.util.FormatterConstants.*;

public class XmlFormatter {
    public XmlFormatter() {
    }

    public void format(byte[] buffer) {
        parseFromBuffer(buffer);
    }

    public void parseFromBuffer(byte[] byteArray) {
        String bufferString = new String(byteArray);
        parse(bufferString);
    }

    public void parse(String bufferString) {
        // Do most of the manipulation here
        decomposeString(collapseString(bufferString));
        // rebuildXml();
    }

    public String collapseString(String bufferString) {
        String collapsed = bufferString.replace(TAB_CHAR, "");
        collapsed = collapsed.replace(NEW_LINE_CHAR, "");
        collapsed = collapsed.replaceAll("\\s*(\\<)", "$1");
        return collapsed;
    }

    public void decomposeString(String collapsedString) {
        // Decomposition step to place each known section to a List
        List<String> decomposedStringList = new ArrayList<String>();
        char[] charArray = collapsedString.toCharArray();
        String tagSlice = "";
        String valSlice = "";

        int i = 0;
        int peek = 0;

        System.out.println(collapsedString);
        while (i < charArray.length) {
            if (charArray[i] == OPEN_TAG_CHAR) {
                // node and node close
                peek = peekArray(charArray, END_TAG_CHAR, i);
                tagSlice = storeSlice(charArray, i + 1, peek);
                System.out.println("tagSlice: " + tagSlice);
                // must reset i to the peek result and go to next iter
                i = peek;
                continue;
            }

            // value
            if (charArray[i] == END_TAG_CHAR && i + 1 < charArray.length) {
                peek = peekArray(charArray, OPEN_TAG_CHAR, i);
                valSlice = storeSlice(charArray, i + 1, peek); // can return blank slice, means that's next node

                System.out.println("valSlice: " + valSlice);
                i = peek;
                continue;
            }

            // idk just loop it none of the above case
            i++;
        }
        // for (String stringSlice : decomposedStringList) {
        // System.out.println(stringSlice + "\n");
        // }

    }

    // <?xml version="1.0"
    // encoding="UTF-8"?><note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't
    // forget me this weekend!</body><contact><name>Jani D</name><phone>12-3456
    // 7890</phone></contact></note>
    // store buffer until end - 1;
    private String storeSlice(char[] charArray, int startPos, int endPos) {
        String slice = "";
        int idx = startPos;
        while (idx < endPos) {
            slice = slice + String.valueOf(charArray[idx]);
            idx++;
        }
        return slice;
    }

    // peek from current idx
    private int peekArray(char[] charArray, char findingChar, int idx) {
        while (charArray[idx] != findingChar) {
            idx++;
        }
        return idx;
    }

    public void rebuildXml() {
        // char[] charArray = bufferString.toCharArray();
        // for (char character : charArray) {
        // if (character == OPEN_TAG_CHAR) {
        // System.out.println("Hello char" + character);
        // }
        // }
    }
}
