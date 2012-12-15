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

    private int id;
    private int quota;
    private String name;
    private int buildingID;
    private boolean lab;
    
    public ClassRoom() {
    }

    public ClassRoom(int id, int quota, String name, int buildingID, boolean is_lab) {
        this.id = id;
        this.quota = quota;
        this.name = name;
        this.buildingID = buildingID;
        this.lab = lab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }
    
}
