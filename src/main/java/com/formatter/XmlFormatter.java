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
        String slice = "";
        String sliceState = "INIT";
        System.out.println(collapsedString);
        for (int i = 0; i < charArray.length; i++) {
            // System.out.println(String.valueOf(charArray[i]));
            // char open
            if (charArray[i] == OPEN_TAG_CHAR
                    && (sliceState == SLICE_STATE_INIT
                            || sliceState == SLICE_STATE_CLOSED)) {
                sliceState = SLICE_STATE_OPEN;
                slice = slice + String.valueOf(charArray[i]);
                decomposedStringList.add(slice);
            }
            // // tag closing
            // if (charArray[i] == CLOSE_TAG_CHAR
            // && sliceState == SLICE_STATE_OPEN) {
            // decomposedStringList.add(slice);
            // slice = "";
            // sliceState = SLICE_STATE_CLOSED;
            // }
            // // char is a value
            // if (charArray[i] != CLOSE_TAG_CHAR
            // && charArray[i] != OPEN_TAG_CHAR
            // && sliceState == SLICE_STATE_CLOSED) {
            // slice.concat(String.valueOf(charArray[i]));
            // if (i + 1 > charArray.length) {
            // if (charArray[i + 1] == OPEN_TAG_CHAR) {
            // decomposedStringList.add(slice);
            // slice = "";
            // }
            // }
            // }
            // // char tag text
            // if (charArray[i] != CLOSE_TAG_CHAR
            // && charArray[i] != OPEN_TAG_CHAR
            // && sliceState == SLICE_STATE_OPEN) {
            // slice.concat(String.valueOf(charArray[i]));
            // }

        }
        for (String stringSlice : decomposedStringList) {
            System.out.println(stringSlice + "\n");
        }

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
