import React, {useState} from "react";
import "../modal/Modal.css"

function Edit({data,setOpen, updateTodo}) {
    console.log(data);
    const [todo, setTodo] = useState({
        text: data.text,
        priority: data.priority,
        due_date: data.due_date
        });
 

    const handleChange = (event) => {
        setTodo({...todo,
            [event.target.name]: event.target.value});
        }
    
    const handleSave = () => {
        updateTodo(todo, data._links.self.href);
        setOpen(false);
    }

    return (
        <React.Fragment>
            <div className="darkBG" onClick={() => setOpen(false)} />
                <div className="centered">
                    <div className="modal">
                        <div className="modalHeader">
                            <h5 className="heading">Edit task</h5>
                        </div>
                        <div className="modalContent">
                            {console.log(todo.text)}
                            <input placeholder='Text' name='text' 
                                value={todo.text} onChange={handleChange} /><br/>
                            <input placeholder='Priority' name='priority'
                                value={todo.priority} onChange={handleChange} /><br/>
                            <input placeholder='Due date' name='due_date'
                                value={todo.due_date} onChange={handleChange} /><br/>
                        </div>
                        <div className="modalActions">
                            <div className="actionsContainer">
                                <button className="deleteBtn" onClick={handleSave}>
                                    Save
                                </button>
                                <button className="cancelBtn"
                                    onClick={() => setOpen(false)}>    
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
        </React.Fragment>
        
    );
}

export default Edit;