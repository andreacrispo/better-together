import { Component, OnInit, OnDestroy } from '@angular/core';

import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../core/auth/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit, OnDestroy {

  username: string;
  password: string;

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit() {
    document.body.style.backgroundColor = 'cadetblue';
  }

  login() {
    this.authService.obtainToken(this.username, this.password)
        .subscribe(
          (resp: any) => {
            this.authService.saveToken(resp.token);
            this.redirectAfterLogin();
          },
          err => this.toastr.warning('Wrong credentials')
        );
  }

  redirectAfterLogin() {
    this.router.navigate(['/services']);
  }


  ngOnDestroy() {
    document.body.style.backgroundColor = '';
  }


}
