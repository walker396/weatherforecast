import React, { Component } from 'react'
import './index.css';
export default class index extends Component {
    state = {mouse:false}
    updateTodo =(id)=>{
        const {todo,updateItem} = this.props
        const name = todo.name
        return (event)=> {
            const todoObj = {id:id,name:name,done:event.target.checked}
            console.log(todoObj)
            updateItem(todoObj)
        }

    }

    deleteItem = (tid) => {
        console.log(this.props)
        if(window.confirm("confirm to delete!"))
            this.props.deleteItem(tid)
    }

    handleMouseEnter = (flag)=>{
        return ()=>{
            this.setState({mouse:flag})
        }
    }
    render() {
        const {id,name,done} = this.props.todo
        const {mouse}  = this.state
        return (
            
            <li style={{backgroundColor: mouse ? "#ddd" : "white"}} onMouseEnter={this.handleMouseEnter(true)} onMouseLeave={this.handleMouseEnter(false)} >
                <label>
                <input type="checkbox" checked={done} onChange={this.updateTodo(id)}/>
                <span>{name}</span>
                </label>
                <button className="btn btn-danger" onClick={() => this.deleteItem(id)} style={{display: mouse ? "block": "none"}}>删除</button>
            </li>
        )
    }
}
