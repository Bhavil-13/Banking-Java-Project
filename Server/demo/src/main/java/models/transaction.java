package models;

import java.time.LocalDateTime;

public class Transaction {
    private int accountid;
    private int senderid;
    private int receiverid;
    private double amount;
    private String context;
    private String referenceid;
    private LocalDateTime date_time;

    public Transaction() {
    }

    public Transaction(int accountid, int senderid, int receiverid, double amount, String context, String referenceid, LocalDateTime date_time) {
        this.accountid = accountid;
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.amount = amount;
        this.context = context;
        this.referenceid = referenceid;
        this.date_time = date_time;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
    }

    public int getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(int receiverid) {
        this.receiverid = receiverid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReferenceid() {
        return referenceid;
    }

    public void setReferenceid(String referenceid) {
        this.referenceid = referenceid;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }
}

