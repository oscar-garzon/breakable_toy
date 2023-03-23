import React, {useState} from 'react';

function DoneToDo(props) {
    const [open, setOpen] = useState(!props.done);

    const handleChange = () => {
        setOpen(!open)
        if(open){
            props.doneTodo(props.link);
        }
        else{
            props.undoneTodo(props.link);
        }
    }

    return(
        <div>
            {open ? <input  id="isDone"
                    type="checkbox"
                    name="done"
                    value="done"
                    onChange={handleChange}/>
                    :
                    <input  id="isDone"
                    checked
                    type="checkbox"
                    name="done"
                    value="done"
                    onChange={handleChange}/>}
        </div>
    );

}

export default DoneToDo;

