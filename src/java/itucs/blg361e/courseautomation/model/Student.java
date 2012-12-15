package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Berker
 */
public class Student extends User{
    private Integer number;
    private Integer studentID = null; // corresponds to the id in the student table (not student no)
    private Integer creditLimit = null;
    private Integer currentCredit = null;
    
    public Student(String nName, String nUsername, String nPassword, Integer nNumber) {
        super(nName, nUsername, nPassword);
        number = nNumber;
    }
    
    public Student(String nName, String nUsername, String nPassword, Integer nNumber, Integer nCreditLimit, Integer nCurrentCredit) {
        super(nName, nUsername, nPassword);
        number = nNumber;
        creditLimit = nCreditLimit;
        currentCredit = nCurrentCredit;
    }

    public void setNumber(Integer newNumber) {
        this.number = newNumber;
    }

    public Integer getNumber() {
        return this.number;
    }
    
    public void setStudentID(Integer newStudentID) {
        this.studentID = newStudentID;
    }

    public Integer getStudentID() {
        return this.studentID;
    }
    
    public void setCreditLimit(Integer newCreditLimit) {
        this.creditLimit = newCreditLimit;
    }

    public Integer getCreditLİmit() {
        return this.creditLimit;
    }
    
    public void setCurrentCredit(Integer newCurrentCredit) {
        this.currentCredit = newCurrentCredit;
    }

    public Integer getCurrentCredit() {
        return this.currentCredit;
    }
    
   /* public boolean isSuitableToTakeCRN(int CRN){
        crn.getders.getcredit;
        if(current_credit + > creditLimit)
            return false;
       
        
        return true;
    }*/
}
