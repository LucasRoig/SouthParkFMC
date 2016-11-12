/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.model;

/**
 *
 * @author Lucas
 */
public class Tag {
    private int tagId;
    private String tagName;

    public Tag(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    /**
     * @return the tagId
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * @return the tagName
     */
    public String getTagName() {
        return tagName;
    }
    
    
}
