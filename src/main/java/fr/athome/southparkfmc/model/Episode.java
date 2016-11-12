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
public class Episode {
    private int episodeId;
    private int productionCode;
    private int seasonId;
    private String nameVO;
    private String nameVF;
    private String plot;
    private int indexInSeason;

    public Episode(int episodeId, int productionCode, int seasonId, String nameVO, String nameVF, String plot, int indexInSeason) {
        this.episodeId = episodeId;
        this.productionCode = productionCode;
        this.seasonId = seasonId;
        this.nameVO = nameVO;
        this.nameVF = nameVF;
        this.plot = plot;
        this.indexInSeason = indexInSeason;
    }

    /**
     * @return the episodeId
     */
    public int getEpisodeId() {
        return episodeId;
    }

    /**
     * @return the productionCode
     */
    public int getProductionCode() {
        return productionCode;
    }

    /**
     * @return the seasonId
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * @return the nameVO
     */
    public String getNameVO() {
        return nameVO;
    }

    /**
     * @return the nameVF
     */
    public String getNameVF() {
        return nameVF;
    }

    /**
     * @return the plot
     */
    public String getPlot() {
        return plot;
    }

    /**
     * @return the indexInSeason
     */
    public int getIndexInSeason() {
        return indexInSeason;
    }
    
    /**
     * Retourne le nom VF s'il existe, le nom VO sinon
     * @return 
     */
    public String getName(){
        if(this.nameVF != null && !this.nameVF.isEmpty())
            return this.nameVF;
        else 
            return this.nameVO;
    }
    
}
