package com.yournal.yournal.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "Trades") 
public class Trade {


   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private LocalDate date;
    private int pnl;
    private String urlImage;
    private String result;

    public Trade(Long id, LocalDate date, int pnl, String urlImage, String result){
        this.id=id;
        this.date=date;
        this.pnl=pnl;
        this.urlImage=urlImage;
        this.result=result;
    }

    public Trade(){
        id=0l;
        date=LocalDate.now();
        pnl=0;
        urlImage="";
        result="";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPnl() {
        return pnl;
    }

    public void setPnl(int pnl) {
        this.pnl = pnl;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    
}
