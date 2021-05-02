import React, { Component } from 'react';
import MediaQuery from 'react-responsive';


function shortenlink(long_url) {
  long_url = long_url.replaceAll("/", "");
  if(long_url.slice(0,10) !== "https:papa" && 
  (long_url.slice(0,4) == "http" || long_url.slice(0,5) == "https"))
  {
    return fetch('/'+ long_url, {

    accept: "application/json"
    })
      .then(checkStatus)
      .then(parseJSON);
  }else
  {
      return fetch('/url/error', {
        accept: "application/json"
      })
      .then(checkStatus)
      .then(parseJSON);
  }
}

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  const error = new Error(`HTTP Error ${response.statusText}`);
  error.status = response.statusText;
  error.response = response;
  throw error;
}

function parseJSON(response) {
  return response.json();
}

class Shortenlink extends Component{
  constructor(props){
    super(props)
    this.state = {
      long_url: ''
    }
    this.changeUrlNameHandler = this.changeUrlNameHandler.bind(this);
  }
 

  changeUrlNameHandler = (event) => {
    this.setState({long_url: event.target.value});
  }
  saveUrl = (e) => {
    e.preventDefault();
    let long_url = this.state.long_url;
    shortenlink(long_url).then(res => {
      this.setState(
        {long_url: res}
      );
    });

  }
  render(){
    
    const { t } = this.props;

    const container_isDeskop = {
      width: '100%',
      height: '130px',
      backgroundColor: '#0B1736'

    }
    const container_isMobile = {
      width: '100%',
      height: '200px',
      backgroundColor: '#0B1736'
    }
   const container_input = {
      width: '58.56%' ,
      marginLeft: '8.9%',
      height: '60px',
    }
    const container_button_isDeskop = {
      marginTop: '-60px',
      marginRight:'116px',
      float: 'right',
      width: '21%',
      height: '60px'
    }
    const container_button_isMobile = {
      width: '21%',
      height: '60px',
      float: 'left',
      marginTop: '10px',
      marginLeft: '8.9%'
    }


  return(
    
    <div> 
      <MediaQuery minDeviceWidth={1156}> 
      <form style = {container_isDeskop}>
            <label>Name: </label> 
            <input style={container_input} type = "text" name ="long_url" className="form-control"
             value= {this.state.long_url} onChange={this.changeUrlNameHandler} 
             placeholder={t('shorten.link_shorten')}/>   
            <input  style={container_button_isDeskop} type = "submit" onClick={this.saveUrl}
             value ={t('shorten.shorten')} className="btn btn-primary"/>    
      </form> 
      </MediaQuery>

      <MediaQuery maxDeviceWidth={1155}> 
      <form style = {container_isMobile}>
            <label>Name: </label> 
            <input style={container_input} type = "text" name ="long_url" className="form-control" 
            value= {this.state.long_url} onChange={this.changeUrlNameHandler} 
            placeholder={t('shorten.link_shorten')}/>   
            <input  style={container_button_isMobile} type = "submit" onClick={this.saveUrl} 
            value ={t('shorten.shorten')} className="btn btn-primary"/>    
      </form> 
      </MediaQuery>
    </div>
  );
}}
export default Shortenlink;
