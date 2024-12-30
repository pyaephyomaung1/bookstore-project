import {Book} from "@/app/ds/book";

export interface Author  {
    id: number;
    name: string;
    nationality: string;
    dateOfBirth: string;
    biography: string;
    authorProfileUrl: string;
    authorCode: string;
    books: Book[];
}