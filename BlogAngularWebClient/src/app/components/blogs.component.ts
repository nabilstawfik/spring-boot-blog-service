import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { Author } from './../classes/author';
import { Blog } from './../classes/blog';
import { AuthorService } from './../services/author.service';
import { BlogService } from './../services/blog.service';

@Component({
  selector: 'my-blogs',
  templateUrl: './../templates/blogs.component.html'
})

export class BlogsComponent implements OnInit {
    blog: Blog= new Blog();
    blogs: Blog[] = [];
    authors: Author[];

    constructor(
        private blogService: BlogService,
        private authorService: AuthorService
    ) { }

    ngOnInit(): void {
        this.getBlogs();
        this.getAuthors();
    }

    getBlogs(): void {
      this.blogService.getBlogs().then(blogs => this.blogs = blogs);
    }

    getAuthors(): void {
      this.authorService.getAuthors().then(authors=>this.authors=authors);
    }

    add(blog: Blog): void {
      this.blogService.create(blog)
        .then(blog => {
          this.blogs.unshift(blog);
        });
    }
}