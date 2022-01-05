import React from 'react';
import ReactDom from 'react-dom';
import App from './App';
import './index.css';

// ReactDom.render(<UserList />, document.getElementById('root'));
ReactDom.render(
  <React.StrictMode>
    <App></App>
  </React.StrictMode>,
  document.getElementById('root'),
);
