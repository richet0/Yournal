import { Injectable,inject } from "@angular/core"
import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs"
import { Trade } from "../data/trade.model"

@Injectable({
    providedIn: 'root'
})

export class TradeService{
    private http = inject(HttpClient)
    private apiUrl='http://localhost:8080/'



    newTrade(trade : Trade): Observable<Trade>{
        return this.http.post<Trade>(this.apiUrl.concat('trade/save'), trade);
    }


}

