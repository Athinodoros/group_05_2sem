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
public class Budget {
    
    private int quarter;    // maybe this shoud be a special type fx. num.?
    private int qyear;      // maybe this should be a data and not integer?
    private int budget;

    public Budget(int quarter, int qyear, int budget) {
        this.quarter = quarter;
        this.qyear = qyear;
        this.budget = budget;
    }

    public Budget() {
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getQyear() {
        return qyear;
    }

    public void setQyear(int qyear) {
        this.qyear = qyear;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
    
}
