import React, { useState} from 'react';
import "../modal/Modal.css";

function AddToDo({addTodo, setOpen}) {
    const [todo, setTodo] = useState({
        text: '',
        priority: '',
        due_date: ''
    });

    // Close the modal form
    const handleClose = () => {
        setOpen(false);
    }

    const handleChange = (event) => {
        setTodo({...todo, [event.target.name]:
            event.target.value});
    }

    // Save car and close modal form
    const handleSave = () => {
        addTodo(todo);
        handleClose();
    }

    return(
        <React.Fragment>
            <div className="darkBG" onClick={() => setOpen(false)} />
                <div className="centered">
                    <div className="modal">
                        <div className="modalHeader">
                            <h5 className="heading">Add task</h5>
                        </div>
                        <div className="modalContent">
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
                                    onClick={handleClose}>    
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
        </React.Fragment>
        
    );
}
export default AddToDo;