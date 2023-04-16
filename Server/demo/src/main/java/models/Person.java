package models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.sym.Name;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name","address","email","password" })
public class Person {
    int personID;
    String name, address,email,password;

    public Person (String name,String address,String email, String password){
        this.name=name;
        this.address=address;
        this.email=email;
        this.password=password;
    }
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    // @JsonProperty("")
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    public int getPersonID() {
        return personID;
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
