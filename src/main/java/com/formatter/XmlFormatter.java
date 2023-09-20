package com.formatter;

import com.formatter.model.Node;
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
        var mainList = decomposeString(collapseString(bufferString));
        rebuildXml(mainList);
    }

    //@todo: preserve value characters
    public String collapseString(String bufferString) {
        String collapsed = bufferString.replace(SPACE_CHAR, EMPTY);
        collapsed = collapsed.replace(NEW_LINE_CHAR, EMPTY);
        collapsed = collapsed.replaceAll("\\s*(\\<)", "$1");
        return collapsed;
    }

    public List<String> decomposeString(String collapsedString) {
        // Decomposition step to place each known section to a List
        List<String> mainList = new ArrayList<>();

        char[] charArray = collapsedString.toCharArray();
        String tagSlice = EMPTY;
        String valSlice = EMPTY;

        int i = 0;
        int peek = 0;

        while (i < charArray.length) {
            if (charArray[i] == OPEN_TAG_CHAR) {
                // node and node close
                peek = peekArray(charArray, END_TAG_CHAR, i);
                tagSlice = storeSlice(charArray, i + 1, peek);
                // add new node
                mainList.add(tagSlice);
                // must reset i to the peek result and go to next iter
                i = peek;
                continue;
            }

            // value
            if (charArray[i] == END_TAG_CHAR && i + 1 < charArray.length) {
                peek = peekArray(charArray, OPEN_TAG_CHAR, i);
                valSlice = storeSlice(charArray, i + 1, peek); // can return blank slice, means that's next node
                if (!valSlice.isBlank()) {
                    mainList.add(valSlice);
                }
                i = peek;
                continue;
            }

            // idk just loop it none of the above case
            i++;
        }

        return mainList;

    }

    private String storeSlice(char[] charArray, int startPos, int endPos) {
        String slice = EMPTY;
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

    public void rebuildXml(List<String> mainList) {

        int i = 0;
        // List<Node> nodeList = new ArrayList<>();

        List<String> formattedList = new ArrayList<>();

        String tabString = EMPTY;

        while (i < mainList.size()) {
            if (mainList.get(i).contains("xml")) {
                // skip xml def handling for now
                formattedList.add(OPEN_TAG_CHAR + mainList.get(i) + END_TAG_CHAR);
                i++;
                continue;
            }
            // node
            if (!mainList.get(i).contains(String.valueOf(CLOSE_TAG_CHAR))
                    && !mainList.get(i + 1).contains(String.valueOf(CLOSE_TAG_CHAR))) {

                if (i > 1 && !formattedList.get(formattedList.size()-1).contains(String.valueOf(CLOSE_TAG_CHAR))) {
                    //only start adding the tabs post first non-xml prolog node
                    tabString = tabString.concat(SPACE_CHAR);
                }
                System.out.println("new node: " + mainList.get(i));
                //only add new nodes, don't care for value append and append due to close
                formattedList.add(tabString + OPEN_TAG_CHAR + mainList.get(i) + END_TAG_CHAR);

                // Node tagNode = new Node();
                // assign own hash also for parent referencing. ?
                // tagNode.setNodeTag(mainList.get(i));
                // tagNode.setOwnHashkey(String.valueOf(tagNode.hashCode()));
                // nodeList.add(tagNode);
            }
            // value
            if (!mainList.get(i).contains(String.valueOf(CLOSE_TAG_CHAR))
                    && mainList.get(i + 1).contains(String.valueOf(CLOSE_TAG_CHAR))) {
                System.out.println(String.format("value to i-1 node: %s, %s ", mainList.get(i - 1), mainList.get(i)));
                var valueOverwrite =  formattedList.get(formattedList.size() - 1) + mainList.get(i);
                formattedList.set(formattedList.size() - 1, valueOverwrite);
                // appendNodeValue(nodeList, mainList.get(i - 1), mainList.get(i));
            }
            // end tag handling
            if (mainList.get(i).contains(String.valueOf(CLOSE_TAG_CHAR))) {
                // find parent
                System.out.println("close node: " + mainList.get(i));

                //is previous close node, then dont append
                if (mainList.get(i-1).contains(String.valueOf(CLOSE_TAG_CHAR))) {
                    if (i > 1 && formattedList.get(formattedList.size()-1).contains(String.valueOf(CLOSE_TAG_CHAR))) {
                        //only start adding the tabs post first non-xml prolog node
                        System.out.println("tabs: " + i);
                        tabString = tabString.replaceFirst(SPACE_CHAR, "");
                    }
                    formattedList.add(tabString + OPEN_TAG_CHAR + mainList.get(i) + END_TAG_CHAR);
                } else {
                    //append like a value node
                    var valueOverwrite =  formattedList.get(formattedList.size() - 1) + OPEN_TAG_CHAR + mainList.get(i) + END_TAG_CHAR;
                    formattedList.set(formattedList.size() - 1, valueOverwrite);
                }
                // findParentNode(nodeList, mainList.get(i));
            }
            i++;
        }
        // check
        for (String string : formattedList) {
            System.out.println(string);
        }
}
