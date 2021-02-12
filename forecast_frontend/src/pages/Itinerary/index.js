import React, { Fragment } from 'react'
// import { Layout, Menu, Breadcrumb } from 'antd';

import { Row, Col } from 'antd';
// import './App.css';

import Bsearch from '../../components/Bsearch'
import Blist from '../../components/Blist'
import Bitinerary from '../../components/Bitinerary'


export default class index extends React.Component{
  state = {//initialization state
    weathers:[], //The initial value of users is an array
    isFirst:true, //Is the page opened for the first time
    isLoading:false,//Identifies whether it is loading
    err:'',//error information related to storage request
    todos:[]
  }

	//update App state
	updateAppState = (stateObj)=>{
		this.setState(stateObj)
	}



  updateItem = (todoObj)=>{
      const {todos} = this.state
      const newTodos = todos.map(todo =>{
          if(todoObj.id === todo.id)
            return {...todo,done:todoObj.done}
          else
            return todo
      })
      console.log(newTodos)
      this.setState({todos:newTodos})
  }
  addItem = (todoObj)=>{
      const {todos} = this.state
      const newTodos =[todoObj,...todos]
      this.setState({todos:newTodos})
      console.log(this.state)
  }

  deleteItem = (tid) => {
    const {todos} = this.state
    const newTodos = todos.filter(todoObj =>{
      return todoObj.id !==tid
    })
    this.setState({todos:newTodos})
  }
  handleCheckAll=(checked) => {
    const {todos} = this.state
    const newTodos = todos.map(todoObj =>{
      return {...todoObj,done:checked}
    })
    this.setState({todos:newTodos})
  }

  deleteAllChecked=() => {
    const {todos} = this.state
    const newTodos = todos.filter(todoObj =>{
      return todoObj.done ===false
    })
    this.setState({todos:newTodos})
  }

  render(){
      const {todos} = this.state;
      return (
        <Fragment>
          <Row gutter={[8, 8]}>
            <Col span={17}>
              <Row gutter={[4, 4]}>
              <Bsearch addItem={this.addItem } updateAppState={this.updateAppState}/>
              </Row>
              <Row gutter={[4, 4]}>
              <Blist {...this.state} todos={todos} addItem={this.addItem } updateItem={this.updateItem} deleteItem={this.deleteItem}/>
              </Row>
            </Col>
            <Col span={7} >
              <Row gutter={[1, 1]}>
                <div className="todo-container">
                  <div className="todo-wrap">
                    <Bitinerary {...this.state} todos={todos} updateItem={this.updateItem} deleteItem={this.deleteItem}/>
                  </div>
                </div>
              </Row>
            </Col>
          </Row>
          
        </Fragment>
            



      );
  }
  
}