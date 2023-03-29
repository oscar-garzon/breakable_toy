import React, { useEffect, useState} from 'react';

import AddToDo from '../addTodo/AddToDo.jsx';
import { SERVER_URL } from '../../constants.js'
import Edit from '../edit/Edit.jsx';
import Pagination from '../pagination/Pagination';
import './ToDoList.css';
import DoneToDo from '../doneTodo/DoneToDo.jsx';
import Search from '../search/Search.jsx';

function ToDolist() {
    const [todos, setTodos] = useState([]);
    const [openEdit, setOpenEdit] = useState(false);
    const [selectedRow, setSelectedRow] = useState({});
    const [openAdd, setOpenAdd] = useState(false);
    const [done, setDone] = useState(true);
    const [orderByPriorityASC, setOrderByPriorityASC] = useState(true);
    
    // Fetch todoos front api the first the app is rendered
    useEffect(() => {
        fetchToDos();
    }, []);       
      
    // Makes a GET request to the API
    const fetchToDos = () => {
    
    fetch(SERVER_URL + 'todos')
    .then(response => response.json())
    .then(data => setTodos(data._embedded.toDoList))
    .catch(err => console.error(err));    
    }

    // Calls the api with a DELETE request for a task
    const onDelClick = (url) => {
        if(window.confirm('Are you sure to delete?')) {
            fetch(url, {method: 'DELETE'})
            .then(response => {
                if(response.ok){
                    fetchToDos();
                }
                else {
                    alert('Somehting went wrong!');
                }
            })
            .catch( err => console.error(err));
        }
    }

    // Add a new todo
    const addTodo = (todo) => {
        fetch(SERVER_URL + 'todos',
        {
            method: 'POST',
            headers: { 'Content-Type':'application/json' },
            body: JSON.stringify(todo)
        })
        .then(response => {
            if(response.ok) {
                fetchToDos();
            }
            else{
                alert("Something went wrong!");
            }
        })
        .catch(err => console.error(err))
    }

    // Calls the API to update a todo
    const updateTodo = (todo, link) => {
        console.log(todo);
        fetch(link,
            {
                method: 'PUT',
                headers: { 'Content-Type':  'application/json' },
                body: JSON.stringify(todo)
            })
            .then(response => {
                if(response.ok) {
                    fetchToDos();
                }
                else {
                    alert('Something went wrong!');
                }
            })
            .catch(err => console.error(err))
    }

    const doneTodo = (link) => {
        fetch(link + '/done',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' }
            })
            .then(response => {
                if(response.ok){
                    fetchToDos();
                }
                else {
                    alert('Something went wrong!');
                }
            })
            .catch(err => console.error(err))
    }

    const undoneTodo = (link) => {
        fetch(link + '/undone',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' }
            })
            .then(response => {
                if(response.ok){
                    fetchToDos();
                }
                else {
                    alert('Something went wrong!');
                }
            })
            .catch(err => console.error(err))
    }

    const sortTodos = (sortBy, sortOrder) => {
        console.log(sortBy + ' ' + sortOrder);
        //setOrderByPriorityASC(!orderByPriorityASC);
        // if(orderByPriorityASC){
        //     fetch(SERVER_URL + 'todos?')
        // }
    }

    const filterTodos = (params) => { 
        fetch(SERVER_URL + 'todos' + params)
        .then(response => response.json())
        .then(data => setTodos(data._embedded.toDoList))
        .catch(err => console.error(err));    
        }

    return(
        <React.Fragment>
            <Search onSearch={filterTodos}/>
            <button className="primaryBtn left" onClick={() => {
                setOpenAdd(true);
            }}> + New To Do</button>
            <div className='ToDoList'>
                <table>
                    <tr>
                        <th>    </th>
                        <th>Task</th>
                        <th>Priority <button onClick={() => sortTodos('Priority', 'ASC')}>&uarr;</button>
                        </th>
                        <th>Due Date</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                        {todos.map((val, key) => {
                            return (
                                <tr id={val.id} key={val.id}>
                                    <td> 
                                        <DoneToDo done={val.done}
                                                link={val._links.self.href}
                                                doneTodo={doneTodo}
                                                undoneTodo={undoneTodo}  />
                                    </td>
                                    <td>{val.text}</td>
                                    <td>{val.priority}</td>
                                    <td>{val.due_date}</td>
                                    <td>
                                        <button className="primaryBtn"  onClick={() => {
                                                        setOpenEdit(true);
                                                        setSelectedRow(val);
                                        }}
                                        >Edit</button>
                                    </td>
                                    <td>
                                        <button className="primaryBtn" onClick={ () => onDelClick(val._links.self.href) } > 
                                            Delete 
                                        </button>
                                    </td>
                                </tr>
                            )
                        })}
                </table>
                <br />
                <Pagination/>
                {openAdd && <AddToDo addTodo={addTodo}
                                    setOpen={setOpenAdd} />}
                {openEdit && <Edit data={ selectedRow } 
                                    setOpen={setOpenEdit}
                                    updateTodo={updateTodo} />}
            </div>
        </React.Fragment>
    );
}

export default ToDolist;
