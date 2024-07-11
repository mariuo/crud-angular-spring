import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Course } from '../../models/course';
import { CoursesService } from '../../services/courses.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { CoursePage } from '../../models/course-page';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  pageIndex = 0;
  pageSize = 10;

  courses$: Observable<CoursePage> | null = null;


  // displayedColumns = ['_id', 'name', 'category'];

  //courseService: CoursesService;


  constructor(
    private courseService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    // this.courses = [];
    // this.courseService = new CoursesService();
    this.refresh();
  }

  refresh(pageEvent: PageEvent = { length: 0, pageIndex: 0, pageSize: 10 }) {
    this.courses$ = this.courseService.list(pageEvent.pageIndex, pageEvent.pageSize)
      .pipe(
        tap(() => {
          this.pageIndex = pageEvent.pageIndex;
          this.pageSize = pageEvent.pageSize;
        }),
        catchError(error => {
          this.onError('Error when loading courses.');
          //console.log('Error to load courses.')
          return of({ courses: [], totalElements: 0, totalPages: 0 })
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

  onAdd() {
    // console.log('onAdd');
    // relativeTo this eleminate /courses/
    this.router.navigate(['new'], { relativeTo: this.route });
  }
  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], { relativeTo: this.route });
  }
  onDelete(course: Course) {

    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Are you sure?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.courseService.delete(course._id).subscribe(
          () => {

            this.snackBar.open('Deleted successfully', 'X',
              { duration: 3000, verticalPosition: 'top', horizontalPosition: 'center' });
            this.refresh();

          },
          () => this.onError('Error when try to delete.')
        );
      }
    });

  }



}
