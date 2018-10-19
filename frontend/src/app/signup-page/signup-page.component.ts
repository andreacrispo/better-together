import { Component, OnInit } from '@angular/core';
import { AuthService } from '../shared/auth/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css']
})
export class SignupPageComponent implements OnInit {

  username: string;
  password: string;

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit() { }



  signup() {
    this.authService.signup(this.username, this.password)
        .subscribe( 
          (resp: any) => { 
            this.toastr.success("Account created")
            this.redirectAfterSignup();
          },
          err => this.toastr.error("Errors during signup process")
        );
  }

  redirectAfterSignup() {
    this.router.navigate(['/login']);
  }

}
