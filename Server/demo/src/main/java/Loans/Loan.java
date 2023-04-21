package Loans;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "application_id", "account_id", "rate_of_Integererest", "principal_amt", "time" })
public class Loan {
    Integer id, applicationId, accountId, time;
    Double rateOfIntegererest, principalAmt;

    public String getloansjson(){
        return "{\"loan_id\":\""+id.toString()+"\",\"application_id\":\""+applicationId.toString()+"\",\"principal_amt\":\""+principalAmt.toString()+"\",\"time\":\""+time.toString()+"\"}";
    }
    public Loan() {

    }

    public Loan(Integer applicationId, Integer accountId, Double rateOfIntegererest, Double principalAmt, Integer time) {
        this.applicationId = applicationId;
        this.accountId = accountId;
        this.rateOfIntegererest = rateOfIntegererest;
        this.principalAmt = principalAmt;
        this.time = time;
        this.id = (Integer) null;
    }

    public Loan(Integer applicationId, Integer accountId, Double rateOfIntegererest, Double principalAmt, Integer time, Integer id) {
        this.applicationId = applicationId;
        this.accountId = accountId;
        this.rateOfIntegererest = rateOfIntegererest;
        this.principalAmt = principalAmt;
        this.time = time;
        this.id = id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("application_id")
    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @JsonProperty("application_id")
    public Integer getApplicationId() {
        return applicationId;
    }

    @JsonProperty("account_id")
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("account_id")
    public Integer getAccountId() {
        return accountId;
    }

    @JsonProperty("rate_of_Integererest")
    public void setRateOfInterest(Double rateOfIntegererest) {
        this.rateOfIntegererest = rateOfIntegererest;
    }

    @JsonProperty("rate_of_Integererest")
    public Double getRateOfInterest() {
        return rateOfIntegererest;
    }

    @JsonProperty("principal_amt")
    public void setPrincipalAmt(Double principalAmt) {
        this.principalAmt = principalAmt;
    }

    @JsonProperty("principal_amt")
    public Double getPrincipalAmt() {
        return principalAmt;
    }

    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

    @JsonProperty("time")
    public Integer getTime() {
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
