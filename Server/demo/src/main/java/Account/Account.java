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
    // public String toString() {
        // return "\"MobilePhone [MobileBrand "+ brand + ", MobileName = " + name + ", RAM = " + ram + ", ROM = " + rom + "]";
    // }
    public String getaccountsjson(){
        return "{\"account_id\":\""+id.toString()+"\",\"balance\":\""+balance.toString()+"\"}";
    }
    Account(){}
    private Integer id, person_id;
    private Double balance;

    public Account(Integer person_id, Double balance) {
        // this.id = id;
        this.person_id = person_id;
        this.balance = balance;
    }

    @JsonProperty("person_id")
    public void setPersonId(Integer person_id) {
        this.person_id = person_id;
    }

    @JsonProperty("balance")
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @JsonProperty("person_id")
    public Integer getPersonId() {
        return person_id;
    }

    @JsonProperty("balance")
    public Double getBalance() {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
