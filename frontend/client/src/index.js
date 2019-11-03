import React from 'react'
import { render } from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import App from './components/App';

import './styles.scss';

/**
  * Application entrypoint
  */
render((
  <BrowserRouter>
    <App apiUrl="http://localhost:8080"/>
  </BrowserRouter>
), document.getElementById("openflorian-app"));

module.hot.accept();