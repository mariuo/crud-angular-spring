import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { AppMaterialModule } from './app-material/app-material.module';
import { CategoryPipe } from './pipes/category.pipe';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';



@NgModule({
    imports: [
        AppMaterialModule,
        CommonModule,
        ErrorDialogComponent,
        CategoryPipe,
        ConfirmationDialogComponent
    ],
    exports: [ErrorDialogComponent, CategoryPipe, ConfirmationDialogComponent]
})
export class SharedModule { }
