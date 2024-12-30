"use client";
import { Author } from "@/app/ds/author";
import { Book } from "@/app/ds/book";
import { useEffect, useState } from "react";
import { getAuthorByCode } from "@/app/service/BookStoreService";
import Image from "next/image";
import {useCart} from "@/context/CartContext";
import {useRouter} from "next/navigation";
import {isUserLoggedIn} from "@/app/service/AuthService";

export const AuthorComponent = ({ authorCode }: { authorCode: string }) => {
  const initialAuthor: Author = {
    id: 0,
    name: "",
    nationality: "",
    dateOfBirth: "",
    biography: "",
    authorProfileUrl: "",
    authorCode: "",
    books: [] as Book[],
  };

  const [author, setAuthor] = useState<Author>(initialAuthor);
  const [books, setBooks] = useState<Book[]>([]);
  const [selectedBook, setSelectedBook] = useState<Book | null>(null);
  const { addToCart } = useCart();
  const [toastVisible, setToastVisible] = useState(false);
  const router = useRouter();

  useEffect(() => {
    console.log("Fetching author with code:", authorCode);
    console.log(books);
    getAuthorByCode(authorCode)
      .then((res) => setAuthor(res.data))
      .catch((e) => console.error(e));
    const fetchDataForAllBooks = async () => {
      try {
        const books = await fetch('http://localhost:8080/api/bookstore/books');
        const dataForBooks = await books.json();
        setBooks(dataForBooks);
      } catch (error) {
        console.log("Error fetching books:", error);
      }
    };
    fetchDataForAllBooks();
  }, [authorCode]);

  const showToast = () => {
    setToastVisible(true);
    setTimeout(() => {
      setToastVisible(false);
    }, 4000);
  };

  const handleAddToCart = (book: Book) => {
    if (isUserLoggedIn()) {
      addToCart({
        id: book.id,
        title: book.title,
        price: book.price,
        quantity: 1,
      });
      showToast();
    } else {
      router.push("/login");
    }
  };

  const handleBookClick = (book: Book) => {
    console.log("Selected Book:", book);
    setSelectedBook(book);
  };

  const closeModal = () => {
    setSelectedBook(null);
  };

  const handleOverlayClick = (e: React.MouseEvent) => {
    if ((e.target as HTMLElement).classList.contains('overlay')) {
      closeModal();
    }
  };

  return (
      <>
    <section className="bg-primaryColor text-white pt-20 pb-12 px-4 sm:px-8 mt-20">
      <div className="container mx-auto text-left mb-12">
        <div className="flex flex-col items-start">
          <Image
              className="rounded-full shadow-lg mb-6"
              src={`/authors/${author.authorProfileUrl}`}
              alt={author.name || "Author Image"}
              width={250}
              height={250}
          />
          <h1 className="text-4xl font-bold text-primaryHighlight mb-2">
            {author.name}
          </h1>
          <p className="text-lg text-gray-400 mb-4">{author.nationality}</p>
          <p className="text-base text-gray-400 max-w-3xl mb-6">{author.biography}
          </p>
        </div>
      </div>
      <hr className={'mb-10 opacity-20'}/>
      <div className="container mx-auto">
        <h2 className="text-3xl font-bold text-gray-400 mb-6 text-center">
          Books by {author.name}
        </h2>
        <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
          {author.books.map((book: Book) => (
              <div
                  key={book.id || `${book.title}-${book.bookCoverUrl}`}
                  className="bg-cardColor rounded-lg py-4 shadow-md flex flex-col items-center hover:scale-105 transition duration-2100 "
              >
                <Image
                    src={`/books/${book.bookCoverUrl}`}
                    width={100}
                    height={100}
                    className="rounded-lg mb-3 object-cover"
                    alt={book.title}
                />
                <div className="text-center">
                  <h3 className="text-white mb-2 font-light">
                    {book.title}
                  </h3>
                  <p className="text-sm text-gray-400 mb-3">
                    <span className="">{book.price}$</span>
                  </p>
                  <p className="text-sm text-gray-300 mb-3">
                  <span className="text-primaryHighlight opacity-70 font-light">
                    {book.authorName}
                  </span>
                  </p>
                  <button
                      onClick={() => handleBookClick(book)}
                      className="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mb-2 transition ease-in-out duration-200"
                  >
                    <span>See Details</span>
                  </button>
                </div>
              </div>
          ))}
        </div>
      </div>
    </section>
        {selectedBook && (
            <div
                className="overlay fixed inset-0 flex items-center justify-center bg-black bg-opacity-70 z-40"
                onClick={handleOverlayClick}
            >
              <div className="bg-primaryColor rounded-lg p-6 w-11/12 max-w-md mx-auto relative">
                <button
                    onClick={closeModal}
                    className="absolute top-2 right-2 text-gray-300 hover:text-white text-2xl font-bold"
                    aria-label="Close"
                >
                  &times;
                </button>
                <h2 className="text-xl font-bold mb-4 text-gray-300">
                  {selectedBook.title}
                </h2>
                <Image
                    src={`/books/${selectedBook.bookCoverUrl}` || `/books/untitlebookcover.jpg`}
                    width={200}
                    height={200}
                    className="rounded-lg mb-4 object-cover mx-auto"
                    alt={selectedBook.title}
                />
                <p className="text-gray-300 mb-4">
                  <strong>Author:</strong> {selectedBook.authorName}
                </p>
                <p className="text-gray-300 mb-4">
                  <strong>Price:</strong> ${selectedBook.price}
                </p>
                <p className="text-gray-300 mb-4">
                  <strong>Publication Date:</strong>
                  {selectedBook.publicationDate?.[0]}-
                  {selectedBook.publicationDate?.[1]}-
                  {selectedBook.publicationDate?.[2]}
                </p>
                <p className="text-gray-300 mb-4">
                  <strong>Category:</strong> {selectedBook.categoryName}
                </p>
                <p className="text-gray-300">
                  <strong>Description:</strong> {selectedBook.description}
                </p>
                <button
                    onClick={() => handleAddToCart(selectedBook)}
                    className="mt-5 text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mb-2 transition ease-in-out duration-200"
                >
                  Add to Cart
                </button>
              </div>
            </div>
        )}


        {toastVisible && (
            <div className="fixed bottom-5 left-1/2 transform -translate-x-1/2 bg-green-600 text-white py-3 px-6 rounded-md shadow-lg z-50">
              <p>Item added to cart! Please check My Cart in the menu bar!</p>
            </div>
        )}
    </>
  );
};
