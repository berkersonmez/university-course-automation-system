/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Oguzzo
 */
public class ClassRoom {

    private Integer id;
    private Integer quota;
    private String name;
    private Integer buildingID;
    private boolean lab;
    
    public ClassRoom() {
    }

    public ClassRoom(Integer id, Integer quota, String name, Integer buildingID, boolean is_lab) {
        this.id = id;
        this.quota = quota;
        this.name = name;
        this.buildingID = buildingID;
        this.lab = lab;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Integer buildingID) {
        this.buildingID = buildingID;
    }

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }
    
}
