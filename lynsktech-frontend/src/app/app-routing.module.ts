import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { TechpageComponent } from './techpage/techpage.component';
import { CoffeepageComponent } from './coffeepage/coffeepage.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/home-component',
    pathMatch: 'full'
  },
  {path: 'home-component', component: HomeComponent},
  {path: 'about-component', component: AboutComponent},
  {path: 'techpage-component', component: TechpageComponent},
  {path: 'coffeepage-component', component: CoffeepageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
