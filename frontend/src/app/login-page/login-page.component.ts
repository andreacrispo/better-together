import { Component, OnInit } from '@angular/core';
import { AuthService } from '../shared/auth/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  username: string;
  password: string;

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit() {
  }



  login() {
    this.authService.obtainToken(this.username, this.password)
        .subscribe( 
          (resp: any) => { 
            this.authService.saveToken(resp.token);
            this.redirectAfterLogin();
          },
          err => this.toastr.warning("Wrong credentials")
        );
  }

  redirectAfterLogin() {
    this.router.navigate(['/services']);
  }


}
