import './App.css';
import React, { Suspense} from 'react';
import {Route,Switch} from 'react-router-dom';
import Shortenlink from './components/Shortenlink.jsx';
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
const HighOrderComponentTranslated = withTranslation('common')(Shortenlink)

function App(){
  return(
    <Switch>
       <Suspense fallback="loading">
            <div>
                <HeaderComponent/>
                <HighOrderComponentTranslated/>  
            </div>    
        </Suspense>
        <Route path="/" component={Shortenlink} exact />
    </Switch>
  );
}
export default App;
 
