import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../model/User";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private userService: UserService
  ) {
  }

  user: User

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      let data = params['data'] || null;

      if (data) {
        this.userService.confirmRegistration(data)
          .subscribe(res => {
            this.user = res
            console.log(res)
          }, err => {
            if (err) {
              alert('invalid confirmation link');
            }
          })
      } else {
        alert('missing data')
      }
    })
  }


}
