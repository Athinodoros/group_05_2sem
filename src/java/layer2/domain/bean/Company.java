/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

/**
 *
 * @author Bancho
 */
public class Company {

    private String companyName;
    private int budget;

    public Company() {
    }

    @Override
    public String toString() {
        
        String str = "";
        
        str += "\n";
        str += "------------- Company ---------------";
        str += "\n";
        
        str +=  "Company Name   :: ";
        str +=  companyName;
        str +=  "\n";
        str +=  "Budget         :: ";
        str +=  budget;
        
        return str;
    }

    public Company(String companyName, int budget) {
        this.companyName = companyName;
        this.budget = budget;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getBudget() {
        return budget;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

}
