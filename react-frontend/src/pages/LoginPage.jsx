import { useState } from 'react';
import api from '../api';

export default function LoginPage({ setToken }) {
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');

  const login = async () => {
    try {
      const res = await api.post('/auth/login', { userId, password });
      localStorage.setItem('userId', userId);
      localStorage.setItem('token', res.data);
      setToken(res.data);
    } catch {
      alert('Login failed');
    }
  };

  return (
    <div style={{
      fontFamily: 'monospace',
      maxWidth: 300,
      margin: '100px auto',
      padding: 20,
      borderRadius: 12,
      textAlign: 'center'
    }}>
      <h2 style={{ color: '#202124', marginBottom: 20 }}>Login</h2>

      <input
        placeholder="User ID"
        value={userId}
        onChange={e => setUserId(e.target.value)}
        style={{
          fontFamily: 'monospace',
          width: '100%',
          padding: 14,
          margin: '10px 0',
          borderRadius: 32,
          border: '1px solid #dadce0'
        }}
      />

      <input
        placeholder="Password"
        type="password"
        value={password}
        onChange={e => setPassword(e.target.value)}
        style={{
          fontFamily: 'monospace',
          width: '100%',
          padding: 14,
          margin: '10px 0',
          borderRadius: 32,
          border: '1px solid #dadce0'
        }}
      />

      <button
        onClick={login}
        style={{
          marginTop: 20,
          fontFamily: 'monospace',
          background: '#4285F4',
          color: '#fff',
          padding: '10px 24px',
          borderRadius: 32,
          border: 'none',
          cursor: 'pointer',
          fontSize: 14
        }}
      >
        Login
      </button>
    </div>
  );
}
