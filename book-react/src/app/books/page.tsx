"use client";
import Link from "next/link";
import Image from "next/image";
import { Book } from "@/app/ds/book";
import { useEffect, useState } from "react";
import { useCart } from "@/context/CartContext";
import {useRouter} from "next/navigation";
import {isUserLoggedIn} from "@/app/service/AuthService";

export default function BookPage() {
  const [books, setBooks] = useState<Book[]>([]);
  const [bestSellerBooks, setBestSellerBooks] = useState<Book[]>([]);
  const [newArrivalBooks, setNewArrivalBooks] = useState<Book[]>([]);
  const [selectedBook, setSelectedBook] = useState<Book | null>(null);
  const [toastVisible, setToastVisible] = useState(false);

  const { addToCart } = useCart();
  const router = useRouter();

  useEffect(() => {
    const fetchDataForAllBooks = async () => {
      try {
        const books = await fetch("http://localhost:8080/api/bookstore/books");
        const bestSellerBooks = await fetch(
            "http://localhost:8080/api/bookstore/best_seller"
        );
        const newArrivalBooks = await fetch(
            "http://localhost:8080/api/bookstore/new_arrivals"
        );
        const dataForBooks = await books.json();
        const dataForBestSellerBooks = await bestSellerBooks.json();
        const dataForNewArrivalBooks = await newArrivalBooks.json();
        setBooks(dataForBooks.slice(0, 10));
        setBestSellerBooks(dataForBestSellerBooks);
        setNewArrivalBooks(dataForNewArrivalBooks);
      } catch (error) {
        console.log(error);
      }
    };
    fetchDataForAllBooks();
  }, []);

  const showToast = () => {
    setToastVisible(true);
    setTimeout(() => {
      setToastVisible(false);
    },4000)
  }

  const handleAddToCart = (book: Book) => {
    if (isUserLoggedIn()) {
      addToCart({
        id: book.id,
        title: book.title,
        price: book.price,
        quantity: 1,
      });
    } else {
      router.push("/login");
    }
    showToast();
  };

  const handleBookClick = (book: Book) => {
    setSelectedBook(book);
  };

  const closeModal = () => {
    setSelectedBook(null);
  };

  const renderBooks = (bookList: Book[]) => {
    return bookList.map((book: Book) => (
        <div
            key={book.id || `${book.title}-${book.bookCoverUrl}`}
            className="bg-cardColor rounded-lg py-4 shadow-md flex flex-col items-center hover:scale-105 transition duration-300"
        >
          <Image
              src={`/books/${book.bookCoverUrl}`}
              width={100}
              height={100}
              className="rounded-lg mb-3 object-cover"
              alt={book.title}
          />
          <div className="text-center">
            <h3 className="text-white mb-2 font-light">{book.title}</h3>
            <p className="text-sm text-gray-400 mb-3">{book.price}$</p>
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
    ));
  };

  return (
      <section className="bg-primaryColor text-white pt-20 pb-12 px-4 sm:px-8">
        <div className="container mx-auto">
          <div
              className="p-4 mb-6 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-700 dark:text-red-400 text-center"
              role="alert"
          >
            We are diligently expanding our book collection. We apologize if you
            don&#39;t find every title you&#39;re searching for at this time.
          </div>
          <h2 className="text-2xl font-light mb-8 text-primaryHighlight text-center">
            Best Sellers
          </h2>
          <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
            {renderBooks(bestSellerBooks)}
          </div>
          <div className="my-10 text-right">
            <Link href="/all-books">
            <span className="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 transition duration-300">
              See all books
            </span>
            </Link>
          </div>
          <h2 className="text-2xl font-light mb-8 text-primaryHighlight text-center">
            New Arrivals
          </h2>
          <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
            {renderBooks(newArrivalBooks)}
          </div>
          <div className="my-10 text-right">
            <Link href="/all-books">
            <span className="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 transition duration-300">
              See all books
            </span>
            </Link>
          </div>
          <h2 className="text-2xl font-light mb-8 text-primaryHighlight text-center">
            All Books
          </h2>
          <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
            {renderBooks(books)}
          </div>
          <div className="my-10 text-right">
            <Link href="/all-books">
            <span className="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 transition duration-300">
              See all books
            </span>
            </Link>
          </div>
        </div>

        {selectedBook && (
            <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-70 z-40">
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
                    src={`/books/${selectedBook.bookCoverUrl}`}
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
                  <strong>Publication Date:</strong> {selectedBook.publicationDate?.[0]}-
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
              <p>Item added to cart! Please check My Cart in menu bar!</p>
            </div>
        )}
      </section>
  );
}