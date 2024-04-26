import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { HttpClient } from '@angular/common/http';
import { tap, first, delay } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = 'api/courses';
  // private readonly API = '/assets/courses.json';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Course[]>(this.API)
      .pipe(
        first(),
        delay(500),
        tap(courses => console.log(courses))
      );
  }

  save(record: Partial<Course>) {
    // console.log(record);
    return this.httpClient.post<Course>(this.API, record).pipe(first());
  }
}
