package LoanApplication;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "account_id", "applicant_id", "amount", "reason", "status" })
public class LoanApplication {

    private int accountId;
    private int applicantId;
    private double amount;
    private String reason;
    private String status;
    private int id;

    public LoanApplication(int accountId, int applicantId, double amount, String reason, String status) {
        this.accountId = accountId;
        this.applicantId = applicantId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    public LoanApplication() {
    }

    @JsonProperty("account_id")
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("applicant_id")
    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    @JsonProperty("amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("account_id")
    public int getAccountId() {
        return accountId;
    }

    @JsonProperty("applicant_id")
    public int getApplicantId() {
        return applicantId;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
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
