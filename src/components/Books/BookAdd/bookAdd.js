import React from "react";
import {useNavigate} from 'react-router-dom';

const BookAdd=(props) => {

    const history=useNavigate();

    const[formData, updateFromData]=React.useState({
        name:"",
        category:"",
        authorName:"",
        authorSurname:"",
        countryName:"",
        copies:0
    })

    const handleChange = (e) => {
        updateFromData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (event) => {
        event.preventDefault();

        const name = formData.name;
        const category = formData.category;
        const authorName = formData.authorName;
        const authorSurname = formData.authorSurname;
        const countryName = formData.countryName;
        const copies = formData.copies;

        props.onAdd(name, category, authorName, authorSurname, countryName, copies);
        history("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text" placeholder="Enter book name" id="name" name="name" required
                               className="form-control" onChange={handleChange}
                        />
                        <label htmlFor="authorName">Author Name</label>
                        <input type="text" placeholder="Enter author name here" id="authorName" name="authorName"
                               className="form-control" required onChange={handleChange}
                        />
                        <label htmlFor="authorSurname">Author Surname</label>
                        <input type="text" placeholder="Enter author surname here" id="authorSurname" name="authorSurname"
                               className="form-control" required onChange={handleChange}
                        />
                        <label htmlFor="countryName">Author Country</label>
                        <input type="text" placeholder="Enter author country here" id="countryName" name="countryName"
                               className="form-control" required onChange={handleChange}
                        />
                        <label htmlFor="copies">Available Copies</label>
                        <input type="text" placeholder="Enter available copies here" id="copies" name="copies"
                               className="form-control" required onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-group" required onChange={handleChange} style={{marginTop: '10px', marginLeft: '10px'}}>
                            <option value="" selected="selected"></option>
                            {props.categories.map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary" id="submit" style={{marginTop: '10px'}}>Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookAdd;