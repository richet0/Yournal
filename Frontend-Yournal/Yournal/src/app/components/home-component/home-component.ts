import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-component',
  standalone: true,
  imports: [],
  templateUrl: './home-component.html',
  styleUrl: './home-component.css',
})
export class HomeComponent {
  private router = inject(Router);

  goToLogin(): void {
    this.router.navigate(['/login']);
  }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }

   goToHome(): void {
    this.router.navigate(['/']);
  }
}