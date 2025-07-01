import { useEffect, useState } from 'react';
import api from '../api';
import TaskList from '../components/TaskList';

export default function Dashboard({ setToken }) {
  const [tasks, setTasks] = useState([]);
  const [inputText, setInputText] = useState('');
  const [selectedTask, setSelectedTask] = useState(null);

  const fetchTasks = async () => {
    const res = await api.get('/task');
    const sorted = res.data.sort((a, b) => b.id - a.id);
    setTasks(sorted);
  };


  useEffect(() => {
    fetchTasks();
    const interval = setInterval(fetchTasks, 5000);
    return () => clearInterval(interval);
  }, []);

  const addTask = async () => {
    if (!inputText.trim()) return;
    await api.post('/task', inputText, { headers: { 'Content-Type': 'text/plain' } });
    setInputText('');
    fetchTasks();
  };

  const logout = async () => {
    await api.post('/auth/logout');
    localStorage.removeItem('token');
    setToken(null);
  };

  return (
    <div style={{
      fontFamily: "monospace",
      fontSize: "14px",
      maxWidth: 600,
      margin: '50px auto',
      padding: 20
    }}>
      <div style={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center'
      }}>
        <h2 style={{ color: '#202124' }}>Dashboard</h2>
        <div style={{ display: "flex", gap: "24px", alignItems: "center" }}>
          <p style={{ fontFamily: "monospace", color: "#5f6368" }}>{localStorage.getItem('userId')}</p>
          <button onClick={logout} style={{
            fontFamily: "monospace",
            background: 'transparent',
            color: '#d93025',
            padding: '8px 24px',
            borderRadius: 32,
            border: '2px solid #d93025',
            cursor: 'pointer'
          }}>
            Logout
          </button>
        </div>
      </div>

      <hr style={{ margin: "24px 0", borderColor: "#e0e0e0" }} />

      <div style={{
        marginTop: 20,
        display: "flex",
        gap: "10px"
      }}>
        <input
          placeholder="Task Input"
          value={inputText}
          onChange={e => setInputText(e.target.value)}
          style={{
            fontFamily: "monospace",
            flex: 1,
            padding: 16,
            borderRadius: 24,
            border: '1px solid #dadce0'
          }}
        />
        <button onClick={addTask} style={{
          fontFamily: "monospace",
          background: '#4285F4',
          color: 'white',
          padding: '10px 20px',
          borderRadius: 32,
          border: 'none',
          cursor: 'pointer'
        }}>
          Add Task
        </button>
      </div>

      <TaskList tasks={tasks} onSelect={setSelectedTask} fetchTasks={fetchTasks} />
    </div>
  );
}
