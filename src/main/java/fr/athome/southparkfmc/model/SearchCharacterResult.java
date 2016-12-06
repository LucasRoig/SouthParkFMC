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
public class SearchCharacterResult {

    private int characterId;
    private String characterName;
    private float score;

    public SearchCharacterResult(int characterId, String characterName, float score) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.score = score;
    }

    /**
     * @return the characterId
     */
    public int getCharacterId() {
        return characterId;
    }

    /**
     * @param characterId the characterId to set
     */
    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    /**
     * @return the characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * @param characterName the characterName to set
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }
    
    
}
