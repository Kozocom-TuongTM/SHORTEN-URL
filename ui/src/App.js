import React, {Component} from 'react';
import {
  BrowserRouter as Router,
} from 'react-router-dom';

import Client from "./Client";

import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {title: ''};
  }

  async componentDidMount() {
    Client.getSummary(summary => {
      this.setState({
        title: summary.content
      });
    });
  }

  render() {
    return (
      <Router>
        <div className="App">
          <h1>Welcome to {this.state.title}</h1>
        </div>
      </Router>
    );
  }
}

export default App;
