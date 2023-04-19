package CreditScore;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "score",
        "salary",
        "netWorth",
        "totalLoans",
        "repaidLoans",
        "occupation"
})
public class CreditScore {
    @JsonProperty("id")
    private int id;

    @JsonProperty("score")
    private int score;

    @JsonProperty("salary")
    private double salary;

    @JsonProperty("netWorth")
    private double netWorth;

    @JsonProperty("totalLoans")
    private double totalLoans;

    @JsonProperty("repaidLoans")
    private double repaidLoans;

    @JsonProperty("occupation")
    private String occupation;

    public CreditScore() {}

    public CreditScore(int score, double salary, double netWorth, double totalLoans, double repaidLoans, String occupation) {
        this.score = score;
        this.salary = salary;
        this.netWorth = netWorth;
        this.totalLoans = totalLoans;
        this.repaidLoans = repaidLoans;
        this.occupation = occupation;
    }

    public CreditScore(int id, int score, double salary, double netWorth, double totalLoans, double repaidLoans, String occupation) {
        this.id = id;
        this.score = score;
        this.salary = salary;
        this.netWorth = netWorth;
        this.totalLoans = totalLoans;
        this.repaidLoans = repaidLoans;
        this.occupation = occupation;
    }

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("score")
    public int getScore() {
        return score;
    }

    @JsonSetter("score")
    public void setScore(int score) {
        this.score = score;
    }

    @JsonGetter("salary")
    public double getSalary() {
        return salary;
    }

    @JsonSetter("salary")
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @JsonGetter("netWorth")
    public double getNetWorth() {
        return netWorth;
    }

    @JsonSetter("netWorth")
    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    @JsonGetter("totalLoans")
    public double getTotalLoans() {
        return totalLoans;
    }

    @JsonSetter("totalLoans")
    public void setTotalLoans(double totalLoans) {
        this.totalLoans = totalLoans;
    }

    @JsonGetter("repaidLoans")
    public double getRepaidLoans() {
        return repaidLoans;
    }

    @JsonSetter("repaidLoans")
    public void setRepaidLoans(double repaidLoans) {
        this.repaidLoans = repaidLoans;
    }

    @JsonGetter("occupation")
    public String getOccupation() {
        return occupation;
    }
    @JsonSetter("occupation")
    public void setOccupation(String occString) {
        this.occupation = occString;
    }
}
