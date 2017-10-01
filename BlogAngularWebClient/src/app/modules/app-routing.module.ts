import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthorDetailComponent } from '../components/author-detail.component';
import { AuthorsComponent } from '../components/authors.component';
import { BlogsComponent } from '../components/blogs.component';
import { UpdateBlogComponent } from '../components/update.blog.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'blogs',  component: BlogsComponent },
  { path: 'blogs/:id', component: UpdateBlogComponent },
  { path: 'author-detail/:id', component: AuthorDetailComponent },
  { path: 'authors',     component: AuthorsComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}