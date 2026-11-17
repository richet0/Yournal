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
        component: HomeComponent,
         canActivate : [authGuard]

    },
    {
        path:'NewTrade',
        component:NewTrade,
         canActivate : [authGuard]
    },
    {
        path:'Dashboard',
        component:DashboardComponent,
         canActivate : [authGuard]
    },
    {
        path:'Login',
        component:LoginComponent,
       
    },
    {
        path:'Register',
        component:RegisterComponent
    },
    {
        path:'**',
        redirectTo:''
    }

];
