'use client';

import { useState, useEffect } from 'react';
import Link from 'next/link';
import Image from 'next/image';
import { FaUserCircle } from 'react-icons/fa';
import {getLoggedInUser, logout} from "@/app/service/AuthService";

export default function NavbarComponent() {
  const [isOpen, setIsOpen] = useState(false); // For mobile menu toggle
  const [isAccountDropdownOpen, setIsAccountDropdownOpen] = useState(false);


  const [isLoggedIn, setIsLoggedIn] = useState(false);


  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      const target = event.target as HTMLElement;
      if (!target.closest('.dropdown-books') && !target.closest('.dropdown-account')) {
        setIsAccountDropdownOpen(false);
      }
      const user = getLoggedInUser();
      if(user != null){
        setIsLoggedIn(true);
      } else {
        setIsLoggedIn(false);
      }
    };
    document.addEventListener('click', handleClickOutside);
    return () => {
      document.removeEventListener('click', handleClickOutside);
    };
  }, []);

  function logoutHandler(){
    setIsLoggedIn(false);
    logout();
  }

  return (
      <nav className="bg-primaryColor opacity-85 text-white fixed top-0 left-0 w-full z-50 shadow-lg">
        <div className="container mx-auto px-4 py-4 flex justify-between items-center">

          <Link href="/" className="text-xl font-bold flex items-center hover:text-accentColor transition-all duration-200">
            <Image
                src="/images/logo.png"
                alt="Logo"
                width={50}
                height={50}
                className="mr-2"
            />
            <span className="text-lg md:text-xl font-extrabold">BIBLO</span>
          </Link>


          <button
              className="block md:hidden text-white focus:outline-none"
              onClick={() => setIsOpen(!isOpen)}
          >
            <svg
                className="w-6 h-6"
                fill="none"
                stroke="currentColor"
                strokeWidth="2"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
            >
              {isOpen ? (
                  <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      d="M6 18L18 6M6 6l12 12"
                  />
              ) : (
                  <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      d="M4 6h16M4 12h16m-7 6h7"
                  />
              )}
            </svg>
          </button>


          <div
              className={`${
                  isOpen ? 'block' : 'hidden'
              } md:block absolute md:static top-20 left-0 w-full md:w-auto bg-primaryColor md:bg-transparent transition-all duration-300`}
          >
            <ul className="flex flex-col md:flex-row space-y-4 md:space-y-0 md:space-x-6 p-4 md:p-0">

              <li className="relative dropdown-books">
                <Link href="/books" className="hover:text-accentColor transition-all duration-200">
                  Books
                </Link>
              </li>

              <li>
                <Link href="/authors" className="hover:text-accentColor transition-all duration-200">
                  Authors
                </Link>
              </li>


              <li>
                <Link href="/about" className="hover:text-accentColor transition-all duration-200">
                  About
                </Link>
              </li>

              <Link href="/cart" className="hover:text-accentColor transition-all duration-200">
                Cart
              </Link>



              <li className="relative dropdown-account">
                <button
                    onClick={() => setIsAccountDropdownOpen(!isAccountDropdownOpen)}
                    className="flex items-center hover:text-accentColor transition-all duration-200"
                >
                  <FaUserCircle size={20} className="mr-2" />
                  Account
                </button>
                {isAccountDropdownOpen && (
                    <ul className="absolute bg-white text-primaryColor rounded-lg shadow-lg mt-2 py-2 w-48">
                      {
                          !isLoggedIn &&
                          <li className="px-4 py-2 hover:bg-gray-100">
                            <Link href="/login">Login</Link>
                          </li>
                      }
                      {
                          !isLoggedIn &&
                          <li className="px-4 py-2 hover:bg-gray-100">
                            <Link href="/register">Register</Link>
                          </li>
                      }
                      {
                          isLoggedIn &&
                          <li className="px-4 py-2 hover:bg-gray-100">
                            <Link href="/login" onClick={logoutHandler}>Logout</Link>
                          </li>
                      }

                    </ul>
                )}
              </li>
            </ul>
          </div>
        </div>
      </nav>
  );
}
