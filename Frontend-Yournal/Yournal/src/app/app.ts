import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NewTrade } from './components/newTrade-component/new-trade';
import { RegisterComponent } from './components/register-component/register-component';
import { LoginComponent } from './components/login-component/login-component';
import { DashboardComponent } from './components/dashboard-component/dashboard-component';
import { HomeComponent } from './components/home-component/home-component';

@Component({
  imports: [RouterOutlet,NewTrade,RegisterComponent,LoginComponent,DashboardComponent,HomeComponent],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('Yournal');
}
