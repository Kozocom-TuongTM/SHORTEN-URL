import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {I18nextProvider} from "react-i18next";
import i18next from "i18next";
import { BrowserRouter} from 'react-router-dom';

import common_vi from "./translations/vi/common.json";
import common_en from "./translations/en/common.json";

i18next.init({
  interpolation: { escapeValue: false },  // React already does escaping
  lng: 'en',                              // language to use
  resources: {
      en: {
          common: common_en               // 'common' is our custom namespace
      },
      vi: {
          common: common_vi
      },
  },
});

ReactDOM.render(
    <BrowserRouter>  
    <React.StrictMode>
        <I18nextProvider i18n={i18next}>
            <App/>
        </I18nextProvider>
    </React.StrictMode>
    </BrowserRouter>,
    document.getElementById('root')
  );
serviceWorker.unregister();
