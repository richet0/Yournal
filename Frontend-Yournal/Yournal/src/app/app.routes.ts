import { Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { NewTrade } from './components/newTrade-component/new-trade';
import { DashboardComponent } from './components/dashboard-component/dashboard-component';
import { LoginComponent } from './components/login-component/login-component';
import { RegisterComponent } from './components/register-component/register-component';
import { authGuard } from './guards/auth.guard';


export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'newTrade',
    component: NewTrade,
    canActivate: [authGuard]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: '**',
    redirectTo: 'login'
    
  }
];


