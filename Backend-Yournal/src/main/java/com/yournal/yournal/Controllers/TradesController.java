package com.yournal.yournal.Controllers;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yournal.yournal.DTO.DashboardDTO;
import com.yournal.yournal.Models.Trade;
import com.yournal.yournal.Services.TradesService;
import org.springframework.web.bind.annotation.GetMapping;





@RestController 
@RequestMapping("/trade") 
public class TradesController {

    private final TradesService tradesService;
     public TradesController(TradesService tradesService) {
        this.tradesService = tradesService;
    }

    @PostMapping("/save")
   public ResponseEntity<Trade> saveTrade(@RequestBody Trade trade){
        Trade newTrade = tradesService.saveTrade(trade);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTrade);
   }


   @PostMapping("/edit/{id}")
   public ResponseEntity<Trade>editTrade(@PathVariable  Long id,@RequestBody  Trade trade){
    Trade newTrade = tradesService.editTrade(id, trade);
     return ResponseEntity.status(HttpStatus.CREATED).body(newTrade);
   }
   @PostMapping("/delete/{id}")
   public ResponseEntity<Void>deleteTrade(@PathVariable  Long id){
    tradesService.deleteTrade(id);
    return ResponseEntity.noContent().build();
   }

   @GetMapping("/metrics")
   public ResponseEntity<DashboardDTO>metricsThirtyDays(){
    DashboardDTO metrics = tradesService.get30DaysMetrics();
    return ResponseEntity.ok(metrics);
   }
   

    

}
