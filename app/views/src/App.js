import logo from './logo.svg';
import './App.css';
import React from 'react';
import {Route,Switch} from 'react-router-dom';

import Shortend from './components/Shortend';

export default function App(){
  return(
    <>
       <switch>
         <Route path="/" component={Shortend} exact />
       </switch> 
    </>
  )
}
 