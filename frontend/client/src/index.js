import React from 'react'
import { render } from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import App from './components/App';

import './styles.scss';

console.log("API_BASE_URL: " + process.env.API_BASE_URL);

/**
  * Application entrypoint
  */
render((
  <BrowserRouter>
    <App apiUrl={process.env.API_BASE_URL}/>
  </BrowserRouter>
), document.getElementById("openflorian-app"));

module.hot.accept();