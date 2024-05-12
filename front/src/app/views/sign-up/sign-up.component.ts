import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth/auth.service';
import { SignUpService } from '../../service/auth/sign-up.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
})
export class SignUpComponent {
  checkoutForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });
  loading$ = this.signUpService.loading$;
  error$ = this.signUpService.error$;
  submitted = false;

  constructor(
    public signUpService: SignUpService,
    public authService: AuthService,
    public route: Router
  ) {}

  get f() {
    return this.checkoutForm.controls;
  }

  signUp() {
    this.submitted = true;
    if (this.checkoutForm.invalid) {
      return;
    }
    this.signUpService
      .register(this.checkoutForm.value.email, this.checkoutForm.value.password)
      .subscribe((response) => {
        this.authService.authenticate(response.token);
        this.route.navigate(['/']);
        this.reset();
      });
  }

  reset() {
    this.submitted = false;
    this.checkoutForm.reset();
  }
}
