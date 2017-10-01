import { Author } from './author';

export class Blog {
    id: number;
    title: string;
    body: string;
    creationTime: string;
    authorDto: Author;

    links: any;
}