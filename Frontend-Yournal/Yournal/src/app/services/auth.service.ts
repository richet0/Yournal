import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

export interface AuthResponse {
  token: string;
  refreshToken?: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiUrl = 'http://localhost:8080/auth';

  login(credentials: { username: string; password: string }) {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, credentials).pipe(
      tap(tokens => this.saveTokens(tokens))
    );
  }

  register(credentials: { username: string; password: string; email: string }) {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, credentials).pipe(
      tap(tokens => this.saveTokens(tokens))
    );
  }

  refreshToken() {
    const refreshToken = localStorage.getItem('refresh_token');
    return this.http.post<AuthResponse>(`${this.apiUrl}/refresh-token`, { refreshToken }).pipe(
      tap(tokens => this.saveTokens(tokens))
    );
  }

  logout() {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    this.router.navigate(['/login']);
  }


  getAccessToken(): string | null {
    const token = localStorage.getItem('access_token');
    if (!token || token === 'undefined' || token === 'null') {
      return null;
    }
    return token;
  }
  private saveTokens(tokens: any) {
  

  // Busca el token sin importar cómo lo haya nombrado el DTO de Spring Boot
  const access = tokens?.token || tokens?.accessToken || tokens?.access_token || tokens?.jwt;
  const refresh = tokens?.refreshToken || tokens?.refresh_token;

  if (access) {
    localStorage.setItem('access_token', access);
  }
  if (refresh) {
    localStorage.setItem('refresh_token', refresh);
  }
}
}