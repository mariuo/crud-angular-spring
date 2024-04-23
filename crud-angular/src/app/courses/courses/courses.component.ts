import { Component, OnInit } from '@angular/core';
import { Course } from '../models/course';
import { CoursesService } from '../services/courses.service';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {


  courses$: Observable<Course[]>;

  displayedColumns = ['name', 'category'];

  //courseService: CoursesService;


  constructor(
    private courseService: CoursesService,
    public dialog: MatDialog
  ) {
    // this.courses = [];
    // this.courseService = new CoursesService();
    this.courses$ = this.courseService.list()
      .pipe(
        catchError(error => {
          this.onError('Error when loading courses.');
          //console.log('Error to load courses.')
          return of([])
        })
      );
  }
  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  ngOnInit(): void {
  }


}
