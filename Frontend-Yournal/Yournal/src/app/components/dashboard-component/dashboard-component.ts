import { Component, OnInit,inject } from '@angular/core';
import { TradeService } from '../../services/trade.service';
import { DecimalPipe } from '@angular/common';
import { DashboardDTO } from '../../data/dashboard.dto';
@Component({
  imports: [DecimalPipe],
  selector: 'app-dashboard-component',
  styleUrl: './dashboard-component.css',
  templateUrl: './dashboard-component.html',
})
export class DashboardComponent {

  private tradeService = inject(TradeService);

  metrics : DashboardDTO = {
    totalPnl:0,
    winRate:0,
    wins:0,
    losses:0,
    avgWin:0,
    avgLoss:0,
    breakEvens:0
  }

  ngOnInit(){
    this.tradeService.getDashboardMetrics().subscribe({
      next: (data)=>{
        this.metrics=data;
      },
      error : (err) => console.log(err)
    });
  }

}
