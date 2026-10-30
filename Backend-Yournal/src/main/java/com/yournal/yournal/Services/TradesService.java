package com.yournal.yournal.Services;
import java.util.Optional;
import org.springframework.stereotype.Service;
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


}
