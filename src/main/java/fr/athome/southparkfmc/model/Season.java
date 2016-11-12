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
public class Season {
    private int seasonId;
    private int diffusionYear;

    public Season(int seasonId, int diffusionYear) {
        this.seasonId = seasonId;
        this.diffusionYear = diffusionYear;
    }

    /**
     * @return the seasonId
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * @return the diffusionYear
     */
    public int getDiffusionYear() {
        return diffusionYear;
    }
    
    
}
