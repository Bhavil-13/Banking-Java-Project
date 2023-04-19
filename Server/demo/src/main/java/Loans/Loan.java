package Loans;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "application_id", "account_id", "rate_of_interest", "principal_amt", "time" })
public class Loan {
    int id, applicationId, accountId, time;
    double rateOfInterest, principalAmt;

    public Loan() {

    }

    public Loan(int applicationId, int accountId, double rateOfInterest, double principalAmt, int time) {
        this.applicationId = applicationId;
        this.accountId = accountId;
        this.rateOfInterest = rateOfInterest;
        this.principalAmt = principalAmt;
        this.time = time;
        this.id = (Integer) null;
    }

    public Loan(int applicationId, int accountId, double rateOfInterest, double principalAmt, int time, int id) {
        this.applicationId = applicationId;
        this.accountId = accountId;
        this.rateOfInterest = rateOfInterest;
        this.principalAmt = principalAmt;
        this.time = time;
        this.id = id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("application_id")
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    @JsonProperty("application_id")
    public int getApplicationId() {
        return applicationId;
    }

    @JsonProperty("account_id")
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("account_id")
    public int getAccountId() {
        return accountId;
    }

    @JsonProperty("rate_of_interest")
    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    @JsonProperty("rate_of_interest")
    public double getRateOfInterest() {
        return rateOfInterest;
    }

    @JsonProperty("principal_amt")
    public void setPrincipalAmt(double principalAmt) {
        this.principalAmt = principalAmt;
    }

    @JsonProperty("principal_amt")
    public double getPrincipalAmt() {
        return principalAmt;
    }

    @JsonProperty("time")
    public void setTime(int time) {
        this.time = time;
    }

    @JsonProperty("time")
    public int getTime() {
        return time;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonSetter
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
