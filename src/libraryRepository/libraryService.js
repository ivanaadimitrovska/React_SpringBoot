import axios from '../custom-axios/axios';

const libraryService={
    fetchBooks:()=>{
        return axios.get("/books");
    },

    fetchAuthors:()=>{
        return axios.get("/author");
    },

    fetchCategories:()=>{
        return axios.get("/categories");
    },

    addBook : (name, category, authorName, authorSurname, countryName, copies) => {
        //console.log(name);
        return axios.post(`/books/add?name=${name}&category=${category}&authorName=${authorName}&authorSurname=${authorSurname}
        &countryName=${countryName}&copies=${copies}`)
    },

    editBook:(id, name, category, authorName, authorSurname, countryName, copies) => {
        console.log(name);
        return axios.put(`/books/edit/${id}?name=${name}&category=${category}&authorName=${authorName}&authorSurname=${authorSurname}
        &countryName=${countryName}&copies=${copies}`)
    },

    deleteBook:(id) => {
        return axios.delete(`/books/delete/${id}`)
    },

    getBook: (id) => {
        return axios.get(`/books/${id}`)
    },

    getCopyoftheBook: (id) => {
        return axios.put(`books/rented/${id}`)
    }
}


export default libraryService;
