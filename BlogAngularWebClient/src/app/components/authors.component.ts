import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { Author } from './../classes/author';

import { AuthorService } from './../services/author.service';

@Component({
  selector: 'authors-listing',
  templateUrl: './../templates/authors.component.html'
})

export class AuthorsComponent implements OnInit{
    constructor(private authorService: AuthorService) { }

    author: Author;
    selectedAuthor: Author;
    authors: Author[];

    ngOnInit(): void {
      this.getAuthors();
    }

    getAuthors(): void {
      this.authorService.getAuthors().then(authors=>this.authors=authors);
    }

    add(name: string): void {
      name = name.trim();
      if (!name) { return; }
      this.authorService.create(name)
        .then(author => {
          this.authors.push(author);
          this.selectedAuthor = null;
        });
    }

    onSelect(author: Author): void {
      this.selectedAuthor = author;
    }
}
