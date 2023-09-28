import {Component, OnInit} from '@angular/core';
import {User} from "../../model/User";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StatusResponse} from "../../dto/StatusResponse";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  registrationForm: FormGroup;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder
  ) {
    this.registrationForm = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  register() {
    let name = this.findInRegistrationForm('name')
    let surname = this.findInRegistrationForm('surname')
    let email = this.findInRegistrationForm('email')

    let userDTO = {name, surname, email}

    this.userService.requestToRegistration(userDTO)
      .subscribe((res: StatusResponse) => {
          alert(res)
        }
      )
  }

  private findInRegistrationForm(
    controlName: string
  ) {
    return this.registrationForm.get(controlName)?.value as string
  }


}
