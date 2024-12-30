import axios from "axios";


export const getAuthorByCode = async (authorCode: string) => {
    return axios.get(`http://localhost:8080/api/bookstore/authors/${authorCode}`);
};

export const getBookById = async (bookId: number) => {
    const token = localStorage.getItem("token");  // Or get the token from your auth system
    if (!token) {
        throw new Error("No authentication token available");
    }

    try {
        return await axios.get(`https://192.168.0.122:8080/api/bookstore/books/${bookId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    } catch (error) {
        console.error("Error fetching book:", error);
        throw error;  // You can add more error handling here if necessary
    }
};

export const getBookByTitle = async (title: string) => {
    const token = localStorage.getItem("token");
    if (!token) {
        console.warn("No authentication token found. Redirecting to login.");
        return Promise.reject(new Error("User not authenticated"));
    }


    try {
        return await axios.get(`https://192.168.0.122:8080/api/bookstore/books/search/${title}`, {
            headers: {
                'Authorization': `Bearer ${token}`,
            },

    });
    }   catch(error) {
        console.error("Error fetching book:", error);
        throw error;
    }
}




