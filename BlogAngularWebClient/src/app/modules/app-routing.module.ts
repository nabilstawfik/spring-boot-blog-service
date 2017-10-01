import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { AuthorsComponent } from '../components/authors.component';
import { BlogsComponent } from '../components/blogs.component';
import { UpdateBlogComponent } from '../components/update.blog.component';

const routes: Routes = [
  { path: '', redirectTo: '/authors', pathMatch: 'full' },
  { path: 'blogs',  component: BlogsComponent },
  { path: 'blogs/:id', component: UpdateBlogComponent },
  { path: 'authors',     component: AuthorsComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}