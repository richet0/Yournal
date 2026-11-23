import { CanActivateFn, Router } from '@angular/router';
import { inject,PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);
  const token = authService.getAccessToken();
  if (authService.getAccessToken()) {
    return true; 
  }

  if (!isPlatformBrowser(platformId)) {
    return true;
  }
  if (token && token !== 'null' && token !== 'undefined' && token.trim() !== '') {
    return true;
  }
 return router.createUrlTree(['login']);

};