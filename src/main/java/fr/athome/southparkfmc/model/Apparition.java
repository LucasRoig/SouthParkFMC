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
public class Apparition {
    private int characterId;
    private int episodeId;
    private String role;
    private String note;
    private String characterName;
    private String episodeName;

    public Apparition(int characterId, int episodeId, String role, String note, String characterName, String episodeName) {
        this.characterId = characterId;
        this.episodeId = episodeId;
        this.role = role;
        this.note = note;
        this.characterName = characterName;
        this.episodeName = episodeName;
    }

    /**
     * @return the characterId
     */
    public int getCharacterId() {
        return characterId;
    }

    /**
     * @return the episodeId
     */
    public int getEpisodeId() {
        return episodeId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @return the characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * @return the episodeName
     */
    public String getEpisodeName() {
        return episodeName;
    }
    
    
}
