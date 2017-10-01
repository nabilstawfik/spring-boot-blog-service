import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';
import 'rxjs/add/operator/switchMap';

import { Author } from '../classes/author';
import { AuthorService } from '../services/author.service';

@Component({
  selector: 'author-detail',
  templateUrl: './../templates/author.detail.component.html'
})
export class AuthorDetailComponent implements OnInit {
  @Input() author: Author;

    constructor(
      private authorService: AuthorService,
      private route: ActivatedRoute,
      private location: Location
    ) {}

    ngOnInit(): void {
      this.route.paramMap
        .switchMap((params: ParamMap) => this.authorService.getAuthor(+params.get('id')))
        .subscribe(author => this.author = author);
    }
    
    save(): void {
      this.authorService.update(this.author)
        .then(() => this.goBack());
    }
    goBack(): void {
      this.location.back();
    }
}