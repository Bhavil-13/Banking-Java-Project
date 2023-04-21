package LoanApplication;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "application_id","account_id", "applicant_id", "amount", "reason", "status" })
public class LoanApplication {
    private Integer applicationId;
    private Integer accountId;
    private Integer applicantId;
    private Double amount;
    private String reason;
    private String status;
    private Integer id;

    @JsonProperty("application_id")
    public Integer getApplicationId() {
        return applicationId;
    }

    @JsonProperty("application_id")
    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public LoanApplication(Integer accountId, Integer applicantId, Double amount, String reason, String status) {
        this.accountId = accountId;
        this.applicantId = applicantId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    public LoanApplication() {
    }

    @JsonProperty("account_id")
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("applicant_id")
    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("account_id")
    public Integer getAccountId() {
        return accountId;
    }

    @JsonProperty("applicant_id")
    public Integer getApplicantId() {
        return applicantId;
    }

    @JsonProperty("amount")
    public Double getAmount() {
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
