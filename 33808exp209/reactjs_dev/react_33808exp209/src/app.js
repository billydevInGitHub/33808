'use strict';
const axios = require('axios');
const React = require('react');
const ReactDOM = require('react-dom');



const root = '/api';

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employees:[]};
	}
//state={
//    employees:[]
//}

	componentDidMount() {
	        axios.get('http://localhost:8080/api/employees')
                .then(ee => {
                console.log("employees:"+ee);
                    this.setState({
						employees: ee
					});
                })
                .catch(function (error) {
                    console.log(error);
                })

	}

	render() {
    const {employees}=this.state;

//   const result=employees.map(employee=>{
//       return    <li> {employee.firstName}</li>
//     }) ;

   return JSON.stringify(employees);

	}
}


export default App;

