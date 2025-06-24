import React from 'react';
import './Sidebar.css';

const Sidebar = ({ currentUser }) => {
  return (
    <div className="sidebar">
      <div className="sidebar-header">
        <div className="logo">
          <span className="logo-icon">₹</span>
          <span className="logo-text">GoDutch</span>
        </div>
        <div className="country-flag">
          🇮🇳 India
        </div>
      </div>
      
      <nav className="sidebar-nav">
        <ul>
          <li className="nav-item active">
            <span className="nav-icon">🏠</span>
            <span className="nav-text">Dashboard</span>
          </li>
          <li className="nav-item">
            <span className="nav-icon">📄</span>
            <span className="nav-text">All Expenses</span>
          </li>
          <li className="nav-item">
            <span className="nav-icon">👥</span>
            <span className="nav-text">Groups</span>
          </li>
          <li className="nav-item">
            <span className="nav-icon">👤</span>
            <span className="nav-text">Friends</span>
          </li>
        </ul>
      </nav>
      
      <div className="sidebar-footer">
        <div className="user-profile">
          <div className="user-avatar">
            {currentUser.name.charAt(0)}
          </div>
          <div className="user-info">
            <div className="user-name">{currentUser.name}</div>
            <div className="user-email">{currentUser.email}</div>
          </div>
          <div className="settings-icon">
            ⚙️
          </div>
        </div>
      </div>
    </div>
  );
};

export default Sidebar;
