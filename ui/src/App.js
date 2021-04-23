
import './App.css';
import React from 'react';
import {Route,Switch} from 'react-router-dom';
import Shorten from './components/Shorten.jsx';


export default function App(){
  return(
    <>
       <Switch>
         <Route path="" component={Shorten} exact />
       </Switch> 
    </>
  );
}
 