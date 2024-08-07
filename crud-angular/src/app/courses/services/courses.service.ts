import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, of, tap } from 'rxjs';

import { Course } from '../models/course';
import { CoursePage } from '../models/course-page';
@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = '/api/courses';

  private cache: Course[] = [];

  // private readonly API = '/assets/courses.json';

  constructor(private httpClient: HttpClient) { }

  list(page = 0, pageSize = 10) {
    return this.httpClient.get<CoursePage>(this.API, { params: { page, pageSize } }).pipe(
      first(),
      // map(data => data.courses),
      tap(data => (this.cache = data.courses))
    );
  }

  save(record: Partial<Course>) {
    // console.log(record);
    if (record._id) {
      // console.log('update');
      return this.update(record);
    }
    // console.log('create');
    return this.create(record)
  }
  findById(id: string) {
    if (this.cache.length > 0) {
      const record = this.cache.find(course => `${course._id}` === `${id}`);
      return record != null ? of(record) : this.getById(id);
    }
    return this.getById(id);
  }
  private getById(id: string) {
    return this.httpClient.get<Course>(`${this.API}/${id}`).pipe(first());
  }
  private create(record: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, record).pipe(first());
  }
  private update(record: Partial<Course>) {
    return this.httpClient.put<Course>(`${this.API}/${record._id}`, record).pipe(first());
  }
  delete(id: string) {
    return this.httpClient.delete<Course>(`${this.API}/${id}`).pipe(first());
  }

}
