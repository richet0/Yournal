import { Component, inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder,FormGroup,ReactiveFormsModule,Validators } from '@angular/forms';
import {Router} from '@angular/router'

@Component({
  imports: [ReactiveFormsModule],
  selector: 'app-login-component',
  styleUrl: './login-component.css',
  templateUrl: './login-component.html',
})
export class LoginComponent {

  private authService = inject(AuthService);
  private fb = inject(FormBuilder);
  private router = inject(Router);

  LoginForm: FormGroup = this.fb.group({
    username:['', Validators.required],
    password:['',Validators.required]
  })

  onSubmit(){
    if(this.LoginForm.valid){
     const cretendials = {username : this.LoginForm.value.username, password:this.LoginForm.value.password}
      this.authService.login(cretendials).subscribe({
         next: (backendResponse)=>{
          console.log(backendResponse)
          this.LoginForm.reset();
          this.router.navigate(['/dashboard']);
        },
        error :(err) => console.error(err)
      })

    }
  }
}
