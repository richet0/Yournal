import { Component,inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder,FormGroup,ReactiveFormsModule,Validators } from '@angular/forms';
import { TradeService } from '../../services/trade.service';
import { Trade } from '../../data/trade.model';


@Component({
  imports: [CommonModule,ReactiveFormsModule],
  selector: 'app-new-trade',
  styleUrl: './new-trade.css',
  templateUrl: './new-trade.html',
})
export class NewTrade {

  private formBuilder = inject(FormBuilder);
  private TradeService = inject(TradeService);

  tradeOutcome=[
    {result: 'Win'},
    {result: 'Loss'},
    {result: 'Break even'}
  ]

  newTradeForm: FormGroup = this.formBuilder.group({
    date:['',Validators.required],
    pnl:[0,Validators.required],
    urlImage:['',Validators.required],
    result:['',Validators.required]
  })
  onSubmit(){
    if(this.newTradeForm.valid){
      const newTrade = this.newTradeForm.value;

      this.TradeService.newTrade(newTrade).subscribe({
        next: (backendResponse)=>{
          console.log(backendResponse)
          this.newTradeForm.reset();
        },
        error :(err) => console.error(err)
      });
    }
  }

}
