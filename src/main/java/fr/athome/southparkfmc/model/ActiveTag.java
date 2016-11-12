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
public class ActiveTag {
    private int idTag;
    private int idEpisode;
    private String note;
    private String tagName;
    private String episodeName;

    public ActiveTag(int idTag, int idEpisode, String note, String tagName, String episodeName) {
        this.idTag = idTag;
        this.idEpisode = idEpisode;
        this.note = note;
        this.tagName = tagName;
        this.episodeName = episodeName;
    }

    /**
     * @return the idTag
     */
    public int getIdTag() {
        return idTag;
    }

    /**
     * @return the idEpisode
     */
    public int getIdEpisode() {
        return idEpisode;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @return the tagName
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @return the episodeName
     */
    public String getEpisodeName() {
        return episodeName;
    }
}
