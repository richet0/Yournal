import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service'
import { catchError, switchMap, throwError } from 'rxjs';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getAccessToken();


  let clonedReq = req;
  if (token && !req.url.includes('/auth')) {
    clonedReq = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }

 
  return next(clonedReq).pipe(
    catchError((error: HttpErrorResponse) => {
      
     
      if (error.status === 401 && !req.url.includes('/auth/refresh-token')) {
        
      
        return authService.refreshToken().pipe(
          switchMap((newTokens) => {
          
            const retryReq = req.clone({
              setHeaders: { Authorization: `Bearer ${newTokens.token}` }
            });
            return next(retryReq);
          }),
          catchError((refreshError) => {
           
            authService.logout();
            return throwError(() => refreshError);
          })
        );
      }

      return throwError(() => error);
    })
  );
};