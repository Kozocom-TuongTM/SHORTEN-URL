import React, { Component } from 'react'
import Client from "../Client";
import MediaQuery from 'react-responsive'
 
class Shorten extends Component{

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
    console.log('long_url =>'+JSON.stringify(long_url));
    Client.shorten(long_url).then(res => {
      console.log(res);
      this.setState(
        {long_url: res }
      );
    });

}
  render(){

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
            <input style={container_input} type = "text" name ="long_url" className="form-control" value= {this.state.long_url} onChange={this.changeUrlNameHandler} placeholder="Shorten your link"/>   
            <input  style={container_button_isDeskop} type = "submit" onClick={this.saveUrl} value = "Shorten" className="btn btn-primary"/>    
      </form> 
      </MediaQuery>

      <MediaQuery maxDeviceWidth={1155}> 
      <form style = {container_isMobile}>
            <label>Name: </label> 
            <input style={container_input} type = "text" name ="long_url" className="form-control" value= {this.state.long_url} onChange={this.changeUrlNameHandler} placeholder="Shorten your link"/>   
            <input  style={container_button_isMobile} type = "submit" onClick={this.saveUrl} value = "Shorten" className="btn btn-primary"/>    
      </form> 
      </MediaQuery>
    </div>

    
   
  );
}
}
export default Shorten;
