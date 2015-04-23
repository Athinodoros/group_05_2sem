/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

/**
 *
 * @author bo
 */
public class Partner {
    
    private String companyName; //primary key
    private int companyID;      // -> project

    public Partner() {}
    
    public Partner(String companyName, int companyID) {
        this.companyName = companyName;
        this.companyID = companyID;
    }
     
    public Partner(Partner p) {
        this.companyName = p.companyName;
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    @Override
    public String toString() {
        return "Partner{" + "companyName=" + companyName + ", companyID=" + companyID + '}';
    }
    
     
}
