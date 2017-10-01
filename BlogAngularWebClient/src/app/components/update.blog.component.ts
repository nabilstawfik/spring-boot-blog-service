import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';
import 'rxjs/add/operator/switchMap';

import { Blog } from '../classes/blog';
import { BlogService } from '../services/blog.service';

@Component({
  selector: 'update-blog',
  templateUrl: './../templates/update.blog.component.html'
})
export class UpdateBlogComponent implements OnInit {
  @Input() blog: Blog;

    constructor(
      private blogService: BlogService,
      private route: ActivatedRoute,
      private location: Location
    ) {}

    ngOnInit(): void {
      this.route.paramMap
        .switchMap((params: ParamMap) => this.blogService.getBlog(+params.get('id')))
        .subscribe(blog => this.blog = blog);
    }
    
    save(): void {
      this.blogService.update(this.blog)
        .then(() => this.goBack());
    }
    goBack(): void {
      this.location.back();
    }
}