package com.example.mentor.debitmaneger;

public class Planettake {

    private int Id;
    private String Name1;
    private String Amount1;
    private  String Description1;
    private  String Tdate;

    public Planettake(int id,String name1,String amount1,String description1,String tdate)
    {
        this.Id = id;
        this.Name1 = name1;
        this.Amount1 = amount1;
        this.Description1 = description1;
        this.Tdate = tdate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName1() {
        return Name1;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public String getAmount1() {
        return Amount1;
    }

    public void setAmount1(String amount1) {
        Amount1 = amount1;
    }

    public String getDescription1() {
        return Description1;
    }

    public void setDescription1(String description1) {
        Description1 = description1;
    }

    public String getTdate() {
        return Tdate;
    }

    public void setTdate(String tdate) {
        Tdate = tdate;
    }
}

