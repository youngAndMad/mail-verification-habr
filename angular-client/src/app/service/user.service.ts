import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserDTO} from "../dto/user.dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private http = inject(HttpClient)

  private BASE_URL = 'http://localhost:3000/api/v1/user';

  requestToRegistration(
    userDTO: UserDTO
  ): Observable<any> {
    return this.http
      .post(`${this.BASE_URL}/register`, userDTO);
  }

  confirmRegistration(
    data: string
  ): Observable<any> {
    return this.http
      .post(`${this.BASE_URL}/confirm-registration?data=` + data, {});
  }

}
