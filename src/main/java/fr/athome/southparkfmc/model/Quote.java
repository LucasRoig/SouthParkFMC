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
public class Quote {
    private int quoteId;
    private int episodeId;
    private int characterId;
    private String quoteText;
    private String quoteNote;
    private String episodeName;
    private String characterName;

    public Quote(int quoteId, int episodeId, int characterId, String quoteText, String quoteNote, String episodeName, String characterName) {
        this.quoteId = quoteId;
        this.episodeId = episodeId;
        this.characterId = characterId;
        this.quoteText = quoteText;
        this.quoteNote = quoteNote;
        this.episodeName = episodeName;
        this.characterName = characterName;
    }

    /**
     * @return the quoteId
     */
    public int getQuoteId() {
        return quoteId;
    }

    /**
     * @return the episodeId
     */
    public int getEpisodeId() {
        return episodeId;
    }

    /**
     * @return the characterId
     */
    public int getCharacterId() {
        return characterId;
    }

    /**
     * @return the quoteText
     */
    public String getQuoteText() {
        return quoteText;
    }

    /**
     * @return the quoteNote
     */
    public String getQuoteNote() {
        return quoteNote;
    }

    /**
     * @return the episodeName
     */
    public String getEpisodeName() {
        return episodeName;
    }

    /**
     * @return the characterName
     */
    public String getCharacterName() {
        return characterName;
    }
    
    
}
