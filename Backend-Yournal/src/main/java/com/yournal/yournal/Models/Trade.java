package com.yournal.yournal.Models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    @JsonIgnore 
    private User user;

    public Trade(Long id, LocalDate date, int pnl, String urlImage, String result,User user){
        this.id=id;
        this.date=date;
        this.pnl=pnl;
        this.urlImage=urlImage;
        this.result=result;
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
