package com.formatter.model;

public class Node {

    private String ownHashKey;
    private String childHashKey;
    private String parentHashkey;
    private String nodeValue;

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getOwnHashKey() {
        return ownHashKey;
    }

    public void setOwnHashKey(String ownHashKey) {
        this.ownHashKey = ownHashKey;
    }

    public String getChildHashKey() {
        return childHashKey;
    }

    public void setChildHashKey(String childHashKey) {
        this.childHashKey = childHashKey;
    }

    public String getParentHashkey() {
        return parentHashkey;
    }

    public void setParentHashkey(String parentHashkey) {
        this.parentHashkey = parentHashkey;
    }

}
