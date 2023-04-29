package com.example.lab_emt.web;

import com.example.lab_emt.model.Book;
import com.example.lab_emt.model.enumerations.Category;
import com.example.lab_emt.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins =  {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listBooks(){
//        ArrayList<String> novi=new ArrayList<>();
//        List<Book> books=bookService.listAll();
//        for (Book book : books) {
//            novi.add("Name: " + book.getName() + " Category: " + book.getCategory() + " AuthorName: " + book.getAuthor().getName()
//                    + " AuthorSurname: " + book.getAuthor().getSurname() + " AvailableCopies: " + book.getAvailableCopies());
//        }
//        return novi;
        return bookService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "category", required = false) Category category, @RequestParam(value = "authorName", required = false) String authorName, @RequestParam(value = "authorSurname", required = false) String authorSurname, @RequestParam(value = "countryName", required = false) String countryName, @RequestParam(value = "copies", required = false) Integer copies){
        //System.out.println(name+" "+category);
        return bookService.create(name,category,authorName,authorSurname,countryName,copies).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "category", required = false) Category category, @RequestParam(value = "authorName", required = false) String authorName, @RequestParam(value = "authorSurname", required = false) String authorSurname, @RequestParam(value = "countryName", required = false) String countryName, @RequestParam(value = "copies", required = false) Integer copies){
        return bookService.update(id,name,category,authorName,authorSurname,countryName,copies).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        bookService.delete(id);
        if(bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/rented/{id}")
    public ResponseEntity rentedBook(@PathVariable Long id){
        bookService.rentedBook(id);
        return ResponseEntity.ok().build();
    }
}
