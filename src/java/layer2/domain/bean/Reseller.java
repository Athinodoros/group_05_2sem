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
public class Reseller {

    private String resellerName;
    private int budget;

    public Reseller() {
    }

    @Override
    public String toString() {
        
        String str = "";
        
        str += "\n";
        str += "------------- Company ---------------";
        str += "\n";
        
        str +=  "Company Name   :: ";
        str +=  resellerName;
        str +=  "\n";
        str +=  "Budget         :: ";
        str +=  budget;
        
        return str;
    }

    public Reseller(String companyName, int budget) {
        this.resellerName = companyName;
        this.budget = budget;
    }

    public Reseller(Reseller company) {
        this.resellerName = company.getCompanyName();
        this.budget = company.getBudget();
    }
    
    public String getCompanyName() {
        return resellerName;
    }

    public int getBudget() {
        return budget;
    }

    public void setCompanyName(String companyName) {
        this.resellerName = companyName;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

}
