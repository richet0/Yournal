import { Component, PLATFORM_ID, computed, inject, signal } from '@angular/core';
import { TradeService } from '../../services/trade.service';
import { CommonModule, DecimalPipe, isPlatformBrowser } from '@angular/common';
import { DashboardDTO } from '../../data/dashboard.dto';
import { Trade } from '../../data/trade.model';
import { RouterLink } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { catchError, map, of } from 'rxjs';

@Component({
  imports: [DecimalPipe, CommonModule, RouterLink],
  selector: 'app-dashboard-component',
  styleUrl: './dashboard-component.css',
  templateUrl: './dashboard-component.html',
})
export class DashboardComponent {
  private platformId = inject(PLATFORM_ID);
  private tradeService = inject(TradeService);

  private defaultMetrics: DashboardDTO = {
    totalPnl: 0,
    winRate: 0,
    wins: 0,
    losses: 0,
    avgWin: 0,
    avgLoss: 0,
    breakEvens: 0,
  };

  trades = toSignal(
    isPlatformBrowser(this.platformId)
      ? this.tradeService.getUserTrades().pipe(
          map((data) => (Array.isArray(data) ? data : [])),
          catchError((err) => {
            console.error('Error cargando trades:', err);
            return of([] as Trade[]);
          })
        )
      : of([] as Trade[]),
    { initialValue: [] as Trade[] }
  );

  metricsSignal = toSignal(
    isPlatformBrowser(this.platformId)
      ? this.tradeService.getDashboardMetrics().pipe(
          map((data) => ({
            totalPnl: Number.isFinite(data?.totalPnl) ? data.totalPnl : 0,
            winRate: Number.isFinite(data?.winRate) ? data.winRate : 0,
            wins: Number.isFinite(data?.wins) ? data.wins : 0,
            losses: Number.isFinite(data?.losses) ? data.losses : 0,
            avgWin: Number.isFinite(data?.avgWin) ? data.avgWin : 0,
            avgLoss: Number.isFinite(data?.avgLoss) ? data.avgLoss : 0,
            breakEvens: Number.isFinite(data?.breakEvens) ? data.breakEvens : 0,
          })),
          catchError((err) => {
            console.error('Error cargando métricas:', err);
            return of(this.defaultMetrics);
          })
        )
      : of(this.defaultMetrics),
    { initialValue: this.defaultMetrics }
  );

  get metrics(): DashboardDTO {
    return this.metricsSignal();
  }

  pageSize = 5;
  currentPage = signal<number>(0);

  paginatedTrades = computed(() => {
    const start = this.currentPage() * this.pageSize;
    return this.trades().slice(start, start + this.pageSize);
  });

  totalPages = computed(() => Math.ceil(this.trades().length / this.pageSize));
  hasNextPage = computed(() => this.currentPage() + 1 < this.totalPages());
  hasPrevPage = computed(() => this.currentPage() > 0);

  nextPage(): void {
    if (this.hasNextPage()) {
      this.currentPage.update((page) => page + 1);
    }
  }

  prevPage(): void {
    if (this.hasPrevPage()) {
      this.currentPage.update((page) => page - 1);
    }
  }
}