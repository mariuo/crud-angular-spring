import { Component, OnInit } from '@angular/core';
import { Course } from '../models/course';
import { CoursesService } from '../services/courses.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {


  courses: Observable<Course[]>;

  displayedColumns = ['name', 'category'];

  //courseService: CoursesService;


  constructor(private courseService: CoursesService) {
    // this.courses = [];
    // this.courseService = new CoursesService();
    this.courses = this.courseService.list();
  }

  ngOnInit(): void {
  }

}
