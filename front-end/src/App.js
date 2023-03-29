import './App.css';

import ToDolist from './components/todolist/ToDolist';
import Metrics from './components/metrics/Metrics';

function App() {
  return (
    <div className="App">
      <ToDolist/> 
      <Metrics/>
    </div>
  );
}

export default App;
