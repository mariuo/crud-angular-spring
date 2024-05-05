import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Course } from '../../models/course';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.scss']
})
export class CoursesListComponent implements OnInit {

  @Input() courses: Course[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
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
}
