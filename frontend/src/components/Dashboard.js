import React from 'react';
import './Dashboard.css';

const Dashboard = ({ currentUser, balances, summary }) => {
  const recentExpenses = [
    {
      id: 1,
      title: 'Dinner at Italian Restaurant',
      group: 'Roommates',
      paidBy: 'You',
      amount: 2500,
      yourShare: 833,
      icon: '🍽️'
    },
    {
      id: 2,
      title: 'Grocery Shopping',
      group: 'Roommates',
      paidBy: 'Alice',
      amount: 1800,
      yourShare: 600,
      icon: '🛒'
    },
    {
      id: 3,
      title: 'Ola to Airport',
      group: 'Trip to Goa',
      paidBy: 'Bob',
      amount: 350,
      yourShare: 175,
      icon: '🚗'
    }
  ];

  const groups = [
    { name: 'Roommates', members: 3, balance: 925, type: 'positive' },
    { name: 'Trip to Goa', members: 4, balance: -420, type: 'negative' },
    { name: 'Office Lunch', members: 6, balance: 255, type: 'positive' }
  ];

  const formatCurrency = (amount) => {
    return `₹${Math.abs(amount).toLocaleString('en-IN')}`;
  };

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <div>
          <h1>Dashboard</h1>
          <p>Track your shared expenses and balances</p>
        </div>
        <button className="btn btn-dark">
          <span>+</span> Add Expense
        </button>
      </div>

      <div className="summary-cards">
        <div className="summary-card total-balance">
          <div className="card-icon">📈</div>
          <div className="card-content">
            <h3>Total Balance</h3>
            <div className={`amount ${summary.netBalance >= 0 ? 'positive' : 'negative'}`}>
              {summary.netBalance >= 0 ? '+' : ''}{formatCurrency(summary.netBalance)}
            </div>
            <p>{summary.netBalance >= 0 ? 'You are owed overall' : 'You owe overall'}</p>
          </div>
        </div>

        <div className="summary-card you-owe">
          <div className="card-icon">📉</div>
          <div className="card-content">
            <h3>You Owe</h3>
            <div className="amount negative">{formatCurrency(summary.totalOwed)}</div>
            <p>Total amount you owe</p>
          </div>
        </div>

        <div className="summary-card you-are-owed">
          <div className="card-icon">📊</div>
          <div className="card-content">
            <h3>You Are Owed</h3>
            <div className="amount positive">{formatCurrency(summary.totalOwedTo)}</div>
            <p>Total amount owed to you</p>
          </div>
        </div>
      </div>

      <div className="dashboard-content">
        <div className="left-section">
          <div className="card">
            <div className="card-header">
              <div>
                <h3>Recent Expenses</h3>
                <p>Your latest shared expenses</p>
              </div>
            </div>
            <div className="expenses-list">
              {recentExpenses.map(expense => (
                <div key={expense.id} className="expense-item">
                  <div className="expense-icon">{expense.icon}</div>
                  <div className="expense-details">
                    <h4>{expense.title}</h4>
                    <p>{expense.group} • Paid by {expense.paidBy}</p>
                  </div>
                  <div className="expense-amount">
                    <div className="total-amount">{formatCurrency(expense.amount)}</div>
                    <div className="your-share">Your share: {formatCurrency(expense.yourShare)}</div>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="card">
            <div className="card-header">
              <div>
                <h3>Your Groups</h3>
                <p>Active expense groups</p>
              </div>
            </div>
            <div className="groups-grid">
              {groups.map((group, index) => (
                <div key={index} className="group-card">
                  <h4>{group.name}</h4>
                  <p>{group.members} members</p>
                  <div className={`group-balance ${group.type}`}>
                    {group.balance >= 0 ? '+' : ''}{formatCurrency(group.balance)}
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        <div className="right-section">
          <div className="card">
            <div className="card-header">
              <div>
                <h3>Balances</h3>
                <p>Who owes what</p>
              </div>
            </div>
            <div className="balances-list">
              {balances.map(balance => (
                <div key={balance.id} className="balance-item">
                  <div className="person-avatar">{balance.name.charAt(0)}</div>
                  <div className="person-name">{balance.name}</div>
                  <div className="balance-amount">
                    {balance.type === 'owes_you' ? (
                      <>
                        <span className="owes-tag">owes you</span>
                        <span className="amount positive">{formatCurrency(balance.amount)}</span>
                      </>
                    ) : (
                      <>
                        <span className="owes-tag">you owe</span>
                        <span className="amount negative">{formatCurrency(balance.amount)}</span>
                      </>
                    )}
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
