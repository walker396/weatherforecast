import React, { Component } from 'react'
import './index.css';
export default class index extends Component {
  handleCheckAll = (event) => {
    // console.log(event.target)
    // const {done} = event.target.checked
    // console.log(done)
    this.props.handleCheckAll(event.target.checked)
  }

//   deleteAllChecked=() => {
//     // const {addItem} = this.props
//     const todoObj = {id:nanoid(),name:target.value,done:false}
//     this.props.addItem(todoObj)
//     target.value=''
    
//     this.props.deleteAllChecked()
//   }

  render() {
    const { todos } = this.props
    const doneCount = todos.reduce((pre,todo)=> pre + (todo.done ? 1 : 0),0)
    const total = todos.length
        return (
            <div className="todo-footer">
              <label>
                <input type="checkbox" onChange={this.handleCheckAll} checked={ doneCount===total && total!==0 ? true : false}/>
              </label>
              {/* <span>
                <span>已完成 {doneCount}</span> / 全部 {total}
              </span> */}
              <button className="btn btn-danger" onClick={this.deleteAllChecked}>Add To Itinerary</button>
            </div>
        )
    }
}
