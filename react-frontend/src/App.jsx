import { useState } from 'react';
import LoginPage from './pages/LoginPage';
import Dashboard from './pages/Dashboard';

export default function App() {
  const [token, setToken] = useState(localStorage.getItem('token'));

  return token ? <Dashboard setToken={setToken} /> : <LoginPage setToken={setToken} />;
}
