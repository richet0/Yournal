package com.yournal.yournal.Services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.yournal.yournal.DTO.DashboardDTO;
import com.yournal.yournal.Models.Trade;
import com.yournal.yournal.Repositories.TradesRepository;

@Service 
public class TradesService {

   
    private final TradesRepository tradesRepository ;

    public TradesService(TradesRepository tradesRepository) {
        this.tradesRepository = tradesRepository;
    }

    public Trade saveTrade(Trade trade){
       return tradesRepository.save(trade);
    }

    public Trade editTrade(Long id, Trade trade){
       Optional <Trade> editedTrade = tradesRepository.findById(id);

        Trade newTrade = editedTrade.get();

        newTrade.setPnl(trade.getPnl());;
        newTrade.setDate(trade.getDate());
        newTrade.setPnl(trade.getPnl());
        newTrade.setUrlImage(trade.getUrlImage());
    
        return tradesRepository.save(newTrade);
      
    }

    public void deleteTrade(Long id){
       tradesRepository.deleteById(id);
        
    }

    public DashboardDTO get30DaysMetrics(){
    LocalDate thirtyDays = LocalDate.now().minusDays(30);

    List<Trade> thirtyDaysTrades = tradesRepository.findByDateGreaterThanEqual(thirtyDays);

    int netPnl = thirtyDaysTrades.stream().mapToInt(Trade::getPnl).sum();
    long winCount = thirtyDaysTrades.stream().filter(t->t.getResult().equalsIgnoreCase("Win")).count();
    long lossCount = thirtyDaysTrades.stream().filter(t->t.getResult().equalsIgnoreCase("Loss")).count();
    double winRate = (double) winCount/thirtyDaysTrades.size() * 100;
    double winsPnl = thirtyDaysTrades.stream().filter(t->"Win".equals(t.getResult())).mapToDouble(Trade::getPnl).sum();
    double lossesPnl = thirtyDaysTrades.stream().filter(t->"Loss".equals(t.getResult())).mapToDouble(Trade::getPnl).sum();
    double avgWin = winsPnl/winCount;
    double avgLoss = lossesPnl/lossCount;
    long breakEvens = thirtyDaysTrades.size()-(winCount + lossCount);


    DashboardDTO metrics = new DashboardDTO(netPnl, winRate, winCount, lossCount, avgWin, avgLoss, breakEvens);
    return metrics;


    }

}
