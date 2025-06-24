import React, { useState, useEffect } from 'react';
import './App.css';
import Sidebar from './components/Sidebar';
import Dashboard from './components/Dashboard';

function App() {
  const [currentUser] = useState({
    id: 1,
    name: 'John Doe',
    email: 'john@example.com'
  });

  const [balances, setBalances] = useState([]);
  const [summary, setSummary] = useState({
    totalOwed: 0,
    totalOwedTo: 0,
    netBalance: 0
  });

  useEffect(() => {
    // Load mock data - in real app, this would come from API
    loadMockData();
  }, []);

  const loadMockData = () => {
    // Mock balance data
    setBalances([
      { id: 1, name: 'Alice', amount: 525, type: 'owes_you' },
      { id: 2, name: 'Bob', amount: 315, type: 'owes_you' },
      { id: 3, name: 'Charlie', amount: 600, type: 'you_owe' }
    ]);

    setSummary({
      totalOwed: 840,
      totalOwedTo: 1690,
      netBalance: 850
    });
  };

  return (
    <div className="app">
      <Sidebar currentUser={currentUser} />
      <main className="main-content">
        <Dashboard 
          currentUser={currentUser}
          balances={balances}
          summary={summary}
        />
      </main>
    </div>
  );
}

export default App;
