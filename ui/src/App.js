import './App.css';
import React, {Component, Suspense} from 'react';
import {Route,Switch} from 'react-router-dom';
import Shorten from './components/Shorten.jsx';
import {useTranslation, withTranslation} from "react-i18next";

function HeaderComponent()
{
    const [t, i18n] = useTranslation('common');
    return <div>
      <div className="App">
        <button onClick={() => i18n.changeLanguage('vi')}>vi</button>
        <button onClick={() => i18n.changeLanguage('en')}>en</button>
      </div>
    </div>
}
const HighOrderComponentTranslated = withTranslation('common')(Shorten)

function App(){
  return(
    <Switch>
       <Suspense fallback="loading">
            <div>
                <HeaderComponent/>
                <HighOrderComponentTranslated/>  
            </div>    
        </Suspense>
        <Route path="/" component={Shorten} exact />
    </Switch>
  );
}
export default App;
 
