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
    const [sorting, setSorting] = useState({
        principalSortBy: '',
        principalSortOrder: '',
        secondarySortBy: '',
        secondarySortOrder: '',
        });
    
    // Fetch todoos front api the first the app is rendered
    useEffect(() => {
        fetchToDos();
    }, []);       
      
    // Makes a GET request to the API. If params it's not included in the function
    // call then the function will call "SERVER_URL + 'todo'"
    // If params it's included in the function call
    // the api call will be "SERVER_URL + 'todo?'+ params"
    const fetchToDos = (params="none") => {
        if(params == "none"){
            var api_response = fetch(SERVER_URL + 'todos');
        }
        else{
            api_response = fetch(SERVER_URL + 'todos?' + params);
        }

    api_response
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
        if(sorting.principalSortBy == sortBy && sorting.principalSortOrder == sortOrder){
            setSorting({
                ...sorting,
                principalSortBy: '',
                principalSortOrder: ''
            })
            fetchToDos();
        }
        else{
            setSorting({
                ...sorting,
                principalSortBy: sortBy,
                principalSortOrder: sortOrder
                });
            fetchToDos(`principalSortBy=${sortBy}&principalSortOrder=${sortOrder}&secondarySortBy=&secondarySortOrder=`);
        }

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
                        <th>Priority 
                            <button onClick={() => sortTodos('priority', 'ASC')}>&uarr;</button>
                            <button onClick={() => sortTodos('priority', 'DESC')}>&darr;</button>
                        </th>
                        <th>Due Date
                            <button onClick={() => sortTodos('due_date', 'ASC')}>&uarr;</button>    
                            <button onClick={() => sortTodos('due_date', 'DESC')}>&darr;</button>
                        </th>
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
