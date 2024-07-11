import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Course } from '../../models/course';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryPipe } from '../../../shared/pipes/category.pipe';
import { MatMiniFabButton, MatIconButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow } from '@angular/material/table';

@Component({
    selector: 'app-courses-list',
    templateUrl: './courses-list.component.html',
    styleUrls: ['./courses-list.component.scss'],
    standalone: true,
    imports: [MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatIcon, MatMiniFabButton, MatIconButton, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow, CategoryPipe]
})
export class CoursesListComponent implements OnInit {

  @Input() courses: Course[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  readonly displayedColumns = ['name', 'category', 'actions'];


  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
  }

  onAdd() {
    // console.log('onAdd');
    // relativeTo this eleminate /courses/
    // this.router.navigate(['new'], { relativeTo: this.route });
    this.add.emit(true);
  }
  onEdit(course: Course) {
    this.edit.emit(course);
  }
  onDelete(course: Course) {
    this.delete.emit(course)
  }
}
