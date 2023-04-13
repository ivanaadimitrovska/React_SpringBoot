import React from "react";
import {Link} from "react-router-dom";


const bookTerm=(props) => {
    return(
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.category}</td>
            <td scope={"col"}>{props.term.author.name + " "+ props.term.author.surname}</td>
            <td scope={"col"}>{props.term.availableCopies}</td>
            <td scope={"col"}>
                <a title={"Delete"} className={"btn btn-danger"} onClick={()=>props.onDelete(props.term.id)}>Delete</a>
                <a title={"Rented Book"} className={"btn btn-primary"} onClick={()=>props.mama(props.term.id)}>Rent this Book</a>
                <Link className={"btn btn-info ml-2"} onClick={()=>props.onEdit(props.term.id)} to={`/books/edit/${props.term.id}`}>Edit</Link>
            </td>
        </tr>
    );
}

export default bookTerm;