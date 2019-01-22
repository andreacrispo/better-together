import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../core/auth/auth.service';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css']
})
export class SignupPageComponent implements OnInit, OnDestroy {

  email: string;
  username: string;
  password: string;

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit() {
    document.body.style.backgroundColor = 'cadetblue';
  }


  signup() {
    this.authService.signup(this.username, this.password)
        .subscribe(
          (resp: any) => {
            this.toastr.success('Account created');
            this.redirectAfterSignup();
          },
          err => this.toastr.error('Errors during signup process')
        );
  }

  redirectAfterSignup() {
    this.router.navigate(['/login']);
  }


  ngOnDestroy() {
    document.body.style.backgroundColor = '';
  }

}
