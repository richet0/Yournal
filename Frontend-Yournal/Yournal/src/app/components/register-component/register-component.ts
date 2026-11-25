import { Component,inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder,FormGroup,ReactiveFormsModule,Validators } from '@angular/forms';
import {Router, RouterLink} from '@angular/router'
@Component({
  imports: [ReactiveFormsModule,RouterLink],
  selector: 'app-register-component',
  styleUrl: './register-component.css',
  templateUrl: './register-component.html',
})
export class RegisterComponent {
  private authService = inject(AuthService);
  private fb = inject(FormBuilder);
  private router = inject(Router);

  RegisterForm: FormGroup = this.fb.group({
    username:['', Validators.required],
    password:['',Validators.required],
    email:['',Validators.required]
  })

  onSubmit(){
    if(this.RegisterForm.valid){
     const cretendials = {
      username : this.RegisterForm.value.username, 
      password:this.RegisterForm.value.password, 
      email:this.RegisterForm.value.email}
      
      this.authService.register(cretendials).subscribe({
         next: (backendResponse)=>{
          console.log(backendResponse)
          this.RegisterForm.reset();
          this.router.navigate(['/dashboard']);
        },
        error :(err) => console.error(err)
      })

    }
  }
}
