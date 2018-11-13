package com.example.mentor.debitmaneger;

public class RepoetPlanet {

private String Nameg;
private String Namet;
private String GiveAmount;
private  String TakeAmount;

public RepoetPlanet(String name,String namet, String giveamount, String takeamt)
{
this.Nameg = name;
this.Namet = namet;
this.GiveAmount = giveamount;
this.TakeAmount = takeamt;
}

    public String getNameg() {
        return Nameg;
    }

    public void setNameg(String nameg) {
        Nameg = nameg;
    }

    public String getNamet() {
        return Namet;
    }

    public void setNamet(String namet) {
        Namet = namet;
    }

    public String getGiveAmount() {
        return GiveAmount;
    }

    public void setGiveAmount(String giveAmount) {
        GiveAmount = giveAmount;
    }

    public String getTakeAmount() {
        return TakeAmount;
    }

    public void setTakeAmount(String takeAmount) {
        TakeAmount = takeAmount;
    }
}
