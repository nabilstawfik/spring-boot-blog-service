import { Author } from './author';

export class Blog {
    blogId: number;
    title: string;
    body: string;
    creationTime: string;
    authorDto: Author;

    links: any;
}