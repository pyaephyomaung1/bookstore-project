import React from "react";

export const Footer = () => {
  return (
    <>
      <footer className="bg-black text-white py-12">
        <div className="container mx-auto px-4 sm:px-8">
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8">
            <div>
              <h3 className="text-xl font-bold mb-4 text-primaryHighlight">
                About BIBLO
              </h3>
              <p className="text-gray-400">
                At BIBLO, we bring stories to life. Whether you love timeless
                classics or modern bestsellers, our curated collection ensures
                there’s something for every reader. Visit us or shop online!
              </p>
            </div>

            <div>
              <h3 className="text-xl font-bold mb-4 text-primaryHighlight">
                Quick Links
              </h3>
              <ul className="space-y-2">
                {["Home", "About Us", "Shop", "Events", "Contact"].map(
                  (link, index) => (
                    <li key={index}>
                      <a
                        href={`#${link.toLowerCase()}`}
                        className="text-gray-400 hover:text-accentColor transition duration-300"
                      >
                        {link}
                      </a>
                    </li>
                  )
                )}
              </ul>
            </div>
            <div>
              <h3 className="text-xl font-bold mb-4 text-primaryHighlight">
                Stay Updated
              </h3>
              <p className="text-gray-400 mb-4">
                Subscribe to our newsletter and never miss out on the latest
                books, events, and offers.
              </p>
              <form className="flex flex-col gap-4">
                <input
                  type="email"
                  placeholder="Enter your email"
                  className="px-4 py-2 bg-cardColor text-white rounded-lg placeholder-gray-400 focus:outline-none"
                />
                <button
                  type="submit"
                  className="bg-accentColor hover:bg-accentHover text-white py-2 px-4 rounded-lg transition duration-300"
                >
                  Subscribe
                </button>
              </form>
            </div>
            <div>
              <h3 className="text-xl font-bold mb-4 text-primaryHighlight">
                Contact Us
              </h3>
              <p className="text-gray-400 mb-2">
                <strong>Address:</strong> 123 Bookstreet Lane, Melbourne, VIC
                3000
              </p>
              <p className="text-gray-400 mb-2">
                <strong>Email:</strong> contact@biblo.com
              </p>
              <p className="text-gray-400 mb-4">
                <strong>Phone:</strong> +61 123 456 789
              </p>
              <div className="flex space-x-4">
                {[
                  { name: "Facebook", icon: "FaFacebook", link: "#" },
                  { name: "Twitter", icon: "FaTwitter", link: "#" },
                  { name: "Instagram", icon: "FaInstagram", link: "#" },
                ].map((social, index) => (
                  <a
                    key={index}
                    href={social.link}
                    className="text-gray-400 hover:text-accentColor transition duration-300"
                    aria-label={social.name}
                  >
                    {React.createElement(
                      require("react-icons/fa6")[social.icon],
                      { size: 24 }
                    )}
                  </a>
                ))}
              </div>
            </div>
          </div>
          <div className="mt-8 border-t border-gray-700 pt-4 text-center">
            <p className="text-gray-400 text-sm">
              © {new Date().getFullYear()} BIBLO. All rights reserved.
            </p>
          </div>
        </div>
      </footer>
    </>
  );
};
