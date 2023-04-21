package LoanApplication;

import Loans.Loan;

public class LoanAppWithExtras extends LoanApplication {
    private String personName;
    private Integer creditScore;
    public String getloansAppjson(){
        return "{\"application_id\":\""+getApplicationId().toString()+"\",\"amount\":\""+getAmount().toString()+"\",\"credit_score\":\""+creditScore.toString()+"\"}";
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getBranchName() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

}
