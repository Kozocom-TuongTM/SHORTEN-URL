import React, { Component } from 'react'
import Client from "../Client";

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

    const container = {
      width: '100%',
      height: '130px',
      backgroundColor: '#0B1736'
    }
   const container_input = {
      width: '800px' ,
      marginLeft: '120px',
      height: '60px',
    }
    const container_button = {
      marginTop: '-60px',
      marginRight:'116px',
      float: 'right',
      width: '284px',
      height: '60px'
    }
    
  
  return(
    <div style = {container}> 
      <form>
            <label>Name: </label> 
            <input style={container_input} type = "text" name ="long_url" className="form-control" value= {this.state.long_url} onChange={this.changeUrlNameHandler} placeholder="Shorten your link"/>       
            <input  style={container_button} type = "submit" onClick={this.saveUrl} value = "Shorten" className="btn btn-primary"/>  
           <div></div>
      </form> 
    </div>
    
   
  );
}
}
export default Shorten;
