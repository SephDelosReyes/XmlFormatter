package com.formatter.model;

public class Node {
    private String parentHashkey = "";
    private String ownHashkey;
    private String nodeTag;
    private String nodeValue;
    private String nodeProps; // later implementation for tag props

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getParentHashkey() {
        return parentHashkey;
    }

    public void setParentHashkey(String parentHashkey) {
        this.parentHashkey = parentHashkey;
    }

    public String getNodeProps() {
        return nodeProps;
    }

    public void setNodeProps(String nodeProps) {
        this.nodeProps = nodeProps;
    }

    public String getNodeTag() {
        return nodeTag;
    }

    public void setNodeTag(String nodeTag) {
        this.nodeTag = nodeTag;
    }

    public String getOwnHashkey() {
        return ownHashkey;
    }

    public void setOwnHashkey(String ownHashkey) {
        this.ownHashkey = ownHashkey;
    }

}
