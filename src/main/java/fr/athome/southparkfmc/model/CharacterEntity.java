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
public class CharacterEntity {
   private int characterId;
   private String characterName;
   private String background;

    public CharacterEntity(int characterId, String characterName, String background) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.background = background;
    }

    /**
     * @return the characterId
     */
    public int getCharacterId() {
        return characterId;
    }

    /**
     * @return the characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * @return the background
     */
    public String getBackground() {
        return background;
    }
   
    
    
}
