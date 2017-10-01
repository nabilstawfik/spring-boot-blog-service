import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Author } from './../classes/author';
import { Blog } from './../classes/blog';

@Injectable()
export class BlogService {
    private headers = new Headers({'Content-Type': 'application/json','Authorization':'Basic bmFiaWw6MTIzNDU2'});
    private baseUrl = '/api/blogs';
    
    constructor(private http: Http) { }

    getBlogs(): Promise<Blog[]> {
      return this.http.get(`${this.baseUrl}?page=0&pageSize=100`, {headers: this.headers})
             .toPromise()
             .then(response => response.json() as Blog[])
             .catch(this.handleError);
    }

    getBlog(id: number): Promise<Blog> {
      return this.getBlogs()
                 .then(blogs => blogs.find(blog => blog.id === id));
    }

    create(blog: Blog): Promise<Blog> {
      const url = `${this.baseUrl}`;
      return this.http
        .post(url, JSON.stringify(blog), {headers: this.headers})
        .toPromise()
        .then(res => res.json() as Blog)
        .catch(this.handleError);
    }

    update(blog: Blog): Promise<Blog> {
      const url = `${this.baseUrl}`;
      return this.http
        .put(url, JSON.stringify(blog), {headers: this.headers})
        .toPromise()
        .then(() => blog)
        .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
      console.error('An error occurred', error);
      return Promise.reject(error.message || error);
    }
}