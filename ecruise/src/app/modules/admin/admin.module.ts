import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { NgZorroImportsModuleTs } from '../../ng-zorro-imports-module.ts';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AdminRoutingModule,
    NgZorroImportsModuleTs,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
