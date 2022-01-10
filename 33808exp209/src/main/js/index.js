import React from 'react';
import ReactDOM from 'react-dom';
import App from './app';
//import 'bootstrap/dist/css/bootstrap.min.css';
//import './index.css';


// setup fake backend
// import { configureFakeBackend } from './_helpers/fake-backend';
// configureFakeBackend();

//ReactDOM.render(<App />, document.getElementById('root'));

ReactDOM.render(
	<App loggedInManager={document.getElementById('managername').innerHTML } />,
	document.getElementById('react')
)