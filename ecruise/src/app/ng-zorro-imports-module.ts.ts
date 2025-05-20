import{NzMessageService} from 'ng-zorro-antd/message';
import {NzSpinModule} from 'ng-zorro-antd/spin';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {NzInputModule} from 'ng-zorro-antd/input';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NgModule } from '@angular/core';

@NgModule({
  exports:[
    NzSpinModule,
    NzFormModule,
    NzButtonModule,
    NzInputModule,
    NzLayoutModule,
    NzDatePickerModule
  ]
})

export class NgZorroImportsModuleTs {
}
