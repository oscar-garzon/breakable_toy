import './App.css';
import  AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import ToDolist from './components/todolist/ToDolist';
import Search from './components/search/Search';
import Metrics from './components/metrics/Metrics';

function App() {
  return (
    <div className="App">
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">
            To Do list
          </Typography>
        </Toolbar>
      </AppBar>
      <ToDolist/>
      <Metrics/>
    </div>
  );
}

export default App;
