import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';


import { AppComponent } from './app.component';
import { AuthorDetailComponent } from './components/author-detail.component';
import { AuthorsComponent } from './components/authors.component';
import { BlogsComponent } from './components/blogs.component';
import { UpdateBlogComponent } from './components/update.blog.component';

import { AppRoutingModule }     from './modules/app-routing.module';

import { AuthorService } from './services/author.service';
import { BlogService } from './services/blog.service';

@NgModule({
  declarations: [
    AppComponent,AuthorDetailComponent,AuthorsComponent,BlogsComponent,UpdateBlogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [AuthorService, BlogService],
  bootstrap: [AppComponent]
})
export class AppModule { }

