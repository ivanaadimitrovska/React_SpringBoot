import './App.css';
import React,{Component} from "react";
import libraryService from "../../libraryRepository/libraryService";
import {BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BookList from "../Books/BookList/bookList";
import Authors from "../Authors/authors";
import Header from "../Header/header";
import Categories from "../Categories/categories";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";


class App extends Component{


  constructor(props) {
    super(props);
    this.state={
      books: [],
      authors:[],
      categories:[],
      selectedBook:{}
    }
  }

  render() {
    return(
        <Router>
            <Header/>
          <main>
            <div className="container">
              <Routes>
                <Route path={"/books"} exact element={<BookList books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook} ivana={this.getCopyoftheBook}/>}/>
                <Route path={"/"} exact element={<BookList books={this.state.books}/>}/>
                <Route path={"/authors"} exact element={<Authors authors={this.state.authors}/>}/>
                <Route path={"/categories"} exact element={<Categories categories={this.state.categories}/>}/>
                <Route path={"/books/add"} exact element={<BookAdd onAdd={this.addBook} categories={this.state.categories}/>}/>
                <Route path={"/books/edit/:id"} exact element={<BookEdit onEdit={this.editBook} categories={this.state.categories} book={this.state.selectedBook}/>}/>
              </Routes>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    this.loadBooks();
    this.loadAuthors();
    this.loadCategories();
  }

  loadBooks=() => {
    libraryService.fetchBooks().then(
        (data) => {
          this.setState({
            books: data.data
          })
        });
  }

  loadAuthors=() => {
      libraryService.fetchAuthors().then(
          (data) => {
              this.setState({
                  authors: data.data
              })
          });
    }

    loadCategories=() => {
        libraryService.fetchCategories().then(
            (data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    addBook=(name, category, authorName, authorSurname, countryName, copies) => {
        libraryService.addBook(name, category, authorName, authorSurname, countryName, copies).then(() => {
            //console.log(name);
            this.loadBooks();
        })
    }

    editBook=(id, name, category, authorName, authorSurname, countryName, copies) => {
      libraryService.editBook(id, name, category, authorName, authorSurname, countryName, copies).then(() => {
          console.log(name);
          this.loadBooks();
      })
    }

    deleteBook=(id) => {
      libraryService.deleteBook(id).then(() => {
            this.loadBooks();
      })
    }

    getBook=(id) => {
        libraryService.getBook(id).then((data) => {
            this.setState({
                selectedBook: data.data
            })
        })
    }

    getCopyoftheBook = (id) => {
      libraryService.getCopyoftheBook(id).then(() => {
          this.loadBooks();
      })
    }

}

export default App;
