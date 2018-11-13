package com.example.mentor.debitmaneger;

public class Planet {

private int Id;
private String Name = "null";
private String Amount = "null";
private  String Description = "null";
private  String GDate = "null";

public Planet(int id,String name,String amount,String description,String gdate)
{
this.Id = id;
this.Name = name;
this.Amount = amount;
this.Description = description;
this.GDate = gdate;
}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getGDate() {
        return GDate;
    }

    public void setGDate(String gDate) {
        GDate = gDate;
    }
}
