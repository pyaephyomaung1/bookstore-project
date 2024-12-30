"use client";

import { useEffect, useState } from "react";
import { Author } from "@/app/ds/author";
import Image from "next/image";
import Link from "next/link";

export default function AuthorPage() {
  const [data, setData] = useState<Author[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/bookstore/authors"
        );
        const data = await response.json();
        setData(data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);
  return (
    <section className="bg-primaryColor text-white pt-20 pb-12 px-4 sm:px-8">
      <div className="container mx-auto text-center">
        <h2 className="text-4xl font-bold mb-8 mt-8 text-primaryHighlight">
          Authors
        </h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5">
          {data.length > 0 ? (
            data.map((author) => (
              <div
                key={author.id || `${author.authorCode}-${author.nationality}`}
                className="bg-cardColor rounded-lg shadow-lg py-6 px-2 flex flex-col items-center transform transition-transform hover:scale-105"
              >
                <Image
                  src={`/authors/${author.authorProfileUrl}`}
                  width={100}
                  height={100}
                  className="rounded-full mb-4"
                  alt={author.name}
                />
                <h3 className="text-xl font-bold text-white mb-2">
                  {author.name}
                </h3>
                <p className={'font-light text-gray-400 mb-2'}>{author.nationality}</p>
                <Link
                  href={`/author-profile/${author.authorCode}`}
                  className="text-gray-400 hover:text-accentHover transition-colors duration-200 underline -outline-offset-0"
                >
                  View Details
                </Link>
              </div>
            ))
          ) : (
            <p className="text-gray-300">No authors found</p>
          )}
        </div>
      </div>
    </section>
  );
}
