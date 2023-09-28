import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {VerificationComponent} from "./component/verification/verification.component";
import {RegistrationComponent} from "./component/registration/registration.component";

const routes: Routes = [
  {path: 'verification', component: VerificationComponent},
  {path: 'registration', component: RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
