package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Berker
 */
public class Student {
    private Integer id = null;
    private Integer number;
    private String name;
    
    public Student(Integer nNumber, String nName) {
        number = nNumber;
        name = nName;
    }

    public void setId(Integer newId) {
        this.id = newId;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setNumber(Integer newNumber) {
        this.number = newNumber;
    }

    public Integer getNumber() {
        return this.number;
    }
    
    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }
}
