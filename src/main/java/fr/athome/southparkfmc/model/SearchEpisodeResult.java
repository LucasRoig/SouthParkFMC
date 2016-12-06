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
public class SearchEpisodeResult {
    private int episodeId;
    private String nameVF;
    private String nameVO;
    private float score;

    public SearchEpisodeResult(int episodeId, String nameVF, String nameVO, float score) {
        this.episodeId = episodeId;
        this.nameVF = nameVF;
        this.nameVO = nameVO;
        this.score = score;
    }

    /**
     * @return the episodeId
     */
    public int getEpisodeId() {
        return episodeId;
    }

    /**
     * @param episodeId the episodeId to set
     */
    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    /**
     * @return the nameVF
     */
    public String getNameVF() {
        return nameVF;
    }

    /**
     * @param nameVF the nameVF to set
     */
    public void setNameVF(String nameVF) {
        this.nameVF = nameVF;
    }

    /**
     * @return the nameVO
     */
    public String getNameVO() {
        return nameVO;
    }

    /**
     * @param nameVO the nameVO to set
     */
    public void setNameVO(String nameVO) {
        this.nameVO = nameVO;
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
