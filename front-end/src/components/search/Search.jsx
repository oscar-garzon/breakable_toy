import React, { useState } from "react";
import './Search.css'

function Search({onSearch}){
    const [text, setText] = useState('all');
    const [priority, setPriority] = useState('all');
    const [done, setDone] = useState('all');

    const handleSearch = () => {
        onSearch(`?filterByText=${text}&filterByPriority=${priority}&filterByDone=${done}`);
    }
    
    const handleText = (event) => {
        setText(event.target.value);
        console.log(text);
    }
    const handlePriority = (event) => {
        setPriority(event.target.value);
    }
    const handleDone = (event) => {
        setDone(event.target.value);
    }
 return(
    <>
            <div className="search-container">
                <div className="item-1">
                    <label for="text">Name: </label>
                    <input type="text"  
                        name="text" id="text" 
                        onChange={handleText} required/>
                </div>
                <div className="item-2">
                    <label for="priority">Priority</label>
                    <select onChange={handlePriority} name="priority" id="priority">
                        <option value="all">All</option>
                        <option value="high">High</option>
                        <option value="medium">Medium</option>
                        <option value="low">Low</option>
                    </select>
                </div>
                <div className="item-3">
                    <label for="done">State</label>
                    <select onChange={handleDone} name="done" id="done">
                        <option value="all">All</option>
                        <option value="true">Done</option>
                        <option value="false">Undone</option>
                    </select>
                </div>
            <div className="item-4">
                <button onClick={handleSearch}>Search</button>
            </div>
            </div>
    </>
 );
}

export default Search;