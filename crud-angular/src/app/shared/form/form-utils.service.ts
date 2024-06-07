import { Injectable } from '@angular/core';
import { UntypedFormArray, UntypedFormControl, UntypedFormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormUtilsService {

  constructor() { }

  validateAllFormFields(formGroup: UntypedFormGroup | UntypedFormArray) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof UntypedFormControl) {
        control?.markAsTouched({ onlySelf: true });
      } else if (control instanceof UntypedFormGroup || control instanceof UntypedFormArray) {
        this.validateAllFormFields(control);
      }
    })
  }

  getErrorMessage(formGoup: UntypedFormGroup, fieldName: string) {
    const field = formGoup.get(fieldName) as UntypedFormControl;

    return this.getErrorMessageFromField(field);
  }
  getErrorMessageFromField(field: UntypedFormControl) {

    if (field?.hasError('required')) {
      return 'Required Field';
    }

    if (field?.hasError('minlength')) {
      const requiredLenght = field?.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Min length is ${requiredLenght} chars.`;
    }
    if (field?.hasError('maxlength')) {
      const requiredLenght = field?.errors ? field.errors['maxlength']['requiredLength'] : 100;
      return `Max length is ${requiredLenght} chars.`;
    }

    return 'Invalid Field';
  }

  getFormArrayFieldErrorMessage(formGroup: UntypedFormGroup, formArrayName: string, fieldName: string, index: number) {
    const formArray = formGroup.get(formArrayName) as UntypedFormArray;
    const field = formArray.controls[index].get(fieldName) as UntypedFormControl;

    return this.getErrorMessageFromField(field);
  }

  isFormArrayRequired(formGroup: UntypedFormGroup, fromArrayName: string) {
    const formArray = formGroup.get(fromArrayName) as UntypedFormArray;
    return !formArray.valid && formArray.hasError('required') && formArray.touched;
  }
}
