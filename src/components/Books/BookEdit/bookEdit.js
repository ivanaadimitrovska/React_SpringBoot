import React from 'react';
import {useNavigate} from 'react-router-dom';

const BookEdit = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name:"",
        category:"",
        authorName:"",
        authorSurname:"",
        countryName:"",
        author: { name: "", surname: "", country: { name: "" } },
        copies:0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim(),
            author: {
                ...formData.author,
                [e.target.name]: e.target.value.trim()
            }
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();

        // const name = formData.name !== "" ? formData.name : props.book.name;
        // const category = formData.category !== "" ? formData.category : props.book.category;
        // const author=formData.author !== "" ? formData.author : props.book.author;
        // const authorName = formData.authorName !== "" ? formData.authorName : props.book.authorName;
        // const authorSurname = formData.authorSurname !== "" ? formData.authorSurname : props.book.authorSurname;
        // const countryName = formData.countryName !== "" ? formData.countryName : props.book.countryName;
        // const copies = formData.copies !== "" ? formData.copies : props.book.copies;

        const updatedBook = {
            id: props.book.id,
            name: formData.name !== "" ? formData.name : props.book.name,
            category: formData.category !== "" ? formData.category : props.book.category,
            author: {
                name: formData.authorName !== "" ? formData.authorName : props.book.author.name,
                surname: formData.authorSurname !== "" ? formData.authorSurname : props.book.author.surname,
                country: {
                    name: formData.countryName !== "" ? formData.countryName : props.book.author.country.name
                }
            },
            availableCopies: formData.copies !== 0 ? formData.copies : props.book.availableCopies
        };

        props.onEdit(updatedBook.id, updatedBook.name, updatedBook.category, updatedBook.author.name, updatedBook.author.surname, updatedBook.author.country.name, updatedBook.availableCopies);
        history("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text" placeholder={props.book.name} id="name" name="name"
                               className="form-control" onChange={handleChange}
                        />
                        <label htmlFor="authorName">Author Name</label>
                        <input type="text" placeholder={props.book.author ? props.book.author.name : ""} id="authorName" name="authorName"
                               className="form-control"  onChange={handleChange}
                        />
                        <label htmlFor="authorSurname">Author Surname</label>
                        <input type="text" placeholder={props.book.author ? props.book.author.surname : ""} id="authorSurname" name="authorSurname"
                               className="form-control"  onChange={handleChange}
                        />
                        <label htmlFor="countryName">Author Country</label>
                        <input type="text" placeholder={props.book.author ? props.book.author.country.name : ""} id="countryName" name="countryName"
                               className="form-control"  onChange={handleChange}
                        />
                        <label htmlFor="copies">Available Copies</label>
                        <input type="text" placeholder={props.book.availableCopies} id="copies" name="copies"
                               className="form-control"  onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-group" required onChange={handleChange} style={{marginTop: '10px', marginLeft: '10px'}}>
                            {props.book.category ? (
                                props.categories.map((term) =>
                                    <option value={term} selected={props.book.category === term}>{term}</option>
                                )
                            ) : (<>
                                    <option selected=""></option>
                                    {props.categories.map((term) =>
                                        <option value={term}>{term}</option>
                                    )}
                                </>
                            )}
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary" id="submit" style={{marginTop: '10px'}}>Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookEdit;
