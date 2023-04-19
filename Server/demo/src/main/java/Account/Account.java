package Account;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "person_id", "balance" })
public class Account {
    Account(){}
    private int id, person_id;
    private double balance;

    public Account(int person_id, double balance) {
        // this.id = id;
        this.person_id = person_id;
        this.balance = balance;
    }

    @JsonProperty("person_id")
    public void setPersonId(int person_id) {
        this.person_id = person_id;
    }

    @JsonProperty("balance")
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @JsonProperty("person_id")
    public int getPersonId() {
        return person_id;
    }

    @JsonProperty("balance")
    public double getBalance() {
        return balance;
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
