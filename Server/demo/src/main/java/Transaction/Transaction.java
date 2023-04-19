package Transaction;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "date_time", "sender_id", "receiver_id", "amount", "trans_context", "loan_id" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    private String dateTime;
    private int senderId;
    private int receiverId;
    private double amount;
    private String transactionContext;
    private int loanId;
    private int id;

    public Transaction(String date_time, int senderId, int receiverId, double amount, String transactionContext,
            int loanId) {
        this.dateTime = date_time;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.transactionContext = transactionContext;
        this.loanId = loanId;
    }

    @JsonProperty("date_time")
    public String getDateTime() {
        return dateTime;
    }

    @JsonProperty("date_time")
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @JsonProperty("sender_id")
    public int getSenderId() {
        return senderId;
    }

    @JsonProperty("sender_id")
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @JsonProperty("receiver_id")
    public int getReceiverId() {
        return receiverId;
    }

    @JsonProperty("receiver_id")
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @JsonProperty("trans_context")
    public String getTransactionContext() {
        return transactionContext;
    }

    @JsonProperty("trans_context")
    public void setTransactionContext(String transactionContext) {
        this.transactionContext = transactionContext;
    }

    @JsonProperty("loan_id")
    public int getLoanId() {
        return loanId;
    }

    @JsonProperty("loan_id")
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
