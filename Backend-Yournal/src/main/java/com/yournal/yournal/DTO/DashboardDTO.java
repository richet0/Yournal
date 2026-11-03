package com.yournal.yournal.DTO;

public class DashboardDTO {


private int totalPnl;
private double winRate;
private long wins;
private long losses;
private double avgWin;
private double avgLoss;
private long breakEvens;

public DashboardDTO(int totalPnl, double winRate, long wins, long losses,double avgWin, double avgLoss, long breakEvens){
    this.totalPnl=totalPnl;
    this.winRate=winRate;
    this.wins=wins;
    this.losses=losses;
    this.avgWin=avgWin;
    this.avgLoss=avgLoss;
    this.breakEvens=breakEvens;
}


public long getBreakEvens() {
    return breakEvens;
}


public void setBreakEvens(long breakEvens) {
    this.breakEvens = breakEvens;
}


public int getTotalPnl() {
    return totalPnl;
}
public void setTotalPnl(int totalPnl) {
    this.totalPnl = totalPnl;
}
public double getWinRate() {
    return winRate;
}
public void setWinRate(int winRate) {
    this.winRate = winRate;
}
public long getWins() {
    return wins;
}
public void setWins(int wins) {
    this.wins = wins;
}
public long getLosses() {
    return losses;
}
public void setLosses(int losses) {
    this.losses = losses;
}
public double getAvgWin() {
    return avgWin;
}
public void setAvgWin(int avgWin) {
    this.avgWin = avgWin;
}
public double getAvgLoss() {
    return avgLoss;
}
public void setAvgLoss(int avgLoss) {
    this.avgLoss = avgLoss;
}



}
