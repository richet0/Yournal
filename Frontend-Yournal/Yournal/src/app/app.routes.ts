import { Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { NewTrade } from './components/newTrade-component/new-trade';
import { DashboardComponent } from './components/dashboard-component/dashboard-component';
import { LoginComponent } from './components/login-component/login-component';
import { RegisterComponent } from './components/register-component/register-component';

export const routes: Routes = [

    {
        path: '',
        component: HomeComponent,

    },
    {
        path:'NewTrade',
        component:NewTrade,
    },
    {
        path:'Dashboard',
        component:DashboardComponent,
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
