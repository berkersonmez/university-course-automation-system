package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Berker
 */
public class Options {    

    public Options(boolean isAddDrop) {
        this.isAddDrop = isAddDrop;
    }

    
    private boolean isAddDrop;
    

    public Options(){
        
    };

    public boolean isIsAddDrop() {
        return isAddDrop;
    }

    public void setIsAddDrop(boolean isAddDrop) {
        this.isAddDrop = isAddDrop;
    }
}