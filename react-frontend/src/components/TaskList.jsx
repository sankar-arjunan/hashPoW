import { useState } from 'react';
import api from '../api';

export default function TaskList({ tasks, fetchTasks }) {
  const [expandedId, setExpandedId] = useState(null);

  const deleteTask = async (id) => {
    if (window.confirm('Delete this task?')) {
      await api.delete(`/task/${id}`);
      fetchTasks();
    }
  };

  return (
    <div style={{ margin: '32px auto'}}>
      <h3 style={{ color: 'black', marginBottom: '32px' }}>List of Tasks</h3>
      <ul style={{ listStyle: 'none', padding: 0 }}>
        {tasks.map(t => (
          <li key={t.id} style={{ borderRadius: '8px', border: '1px solid rgba(0,0,0,0.1)', background: '#fff', padding:"16px", margin:"24px 0 " }}>
            
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap' }}>
              
              <div style={{display:"flex", flexDirection:"row", gap:"12px", alignItems:"center"}}>
                <b><span style={{color:"grey"}}>#</span>{t.id}</b> 
                <span style={{ marginLeft: '8px', padding: '2px 6px', borderRadius: '4px', background: t.state === 'COMPLETED' ? 'rgb(0 121 0)' : t.state === 'PROCESSING' ? 'rgb(181 133 0)' : 'rgb(136 136 136)', color: 'white', fontSize: '12px', padding: "4px 8px", borderRadius:"32px" }}>
                  {t.state}
                </span>
              </div>

              <div style={{ display: 'flex', gap: '8px' }}>
                <button style={{ padding: '4px 10px', borderRadius: '4px', border: '1px solid #ccc', background: '#fff', cursor: 'pointer' }} onClick={() => setExpandedId(expandedId === t.id ? null : t.id)}>{expandedId !== t.id ? 'View' : "Collapse"}</button>
                <button style={{ padding: '4px 10px', borderRadius: '4px', border: '1px solid #d9534f', background: '#fff0f0', color: '#d9534f', cursor: 'pointer' }} onClick={() => deleteTask(t.id)}>Delete</button>
              </div>

            </div>

            {expandedId === t.id && (
              <div style={{ marginTop: '10px', background: '#fff', padding: '10px', borderRadius: '6px', border: '1px solid #ddd' }}>
                <p><b>Input:</b> {t.inputText}</p>
                <p><b>Output Hash:</b> {t.outputHash || 'N/A'}</p>
                <p><b>Created At:</b> {t.createdAt}</p>
              </div>
            )}

          </li>
        ))}
      </ul>
    </div>
  );
}
