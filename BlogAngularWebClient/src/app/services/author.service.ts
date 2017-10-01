import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Author } from './../classes/author';

@Injectable()
export class AuthorService {
    private headers = new Headers({'Content-Type': 'application/json','Authorization':'Basic bmFiaWw6MTIzNDU2'});
    private baseUrl = '/api/authors';
    
    constructor(private http: Http) { }

    getAuthors(): Promise<Author[]> {
      return this.http.get(`${this.baseUrl}`, {headers: this.headers})
             .toPromise()
             .then(response => response.json() as Author[])
             .catch(this.handleError);
    }

    getAuthor(id: number): Promise<Author> {
      return this.getAuthors()
                 .then(authors => authors.find(author => author.authorId === id));
    }

    create(name: string): Promise<Author> {
      const url = `${this.baseUrl}`;
      return this.http
        .post(url, JSON.stringify({nickName: name}), {headers: this.headers})
        .toPromise()
        .then(res => res.json() as Author)
        .catch(this.handleError);
    }

    update(author: Author): Promise<Author> {
      const url = `${this.baseUrl}/save/${author.authorId}`;
      return this.http
        .put(url, JSON.stringify(author), {headers: this.headers})
        .toPromise()
        .then(() => author)
        .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
      console.error('An error occurred', error);
      return Promise.reject(error.message || error);
    }
}