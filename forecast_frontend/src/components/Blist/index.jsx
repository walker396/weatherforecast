import React, { Component, Fragment } from 'react'
import {nanoid} from 'nanoid'
import PubSub from 'pubsub-js'
// import Table from 'react-bootstrap/Table';
// import Button from 'react-bootstrap/Button'

import { Table, Button } from 'antd';
// import '../Bitinerary/node_modules/bootstrap/dist/css/bootstrap.min.css';
// import './index.css';


var columns = [
    {
      title: 'city',
      dataIndex: 'city',
    },
    {
      title: 'country',
      dataIndex: 'country',
    },
    {
      title: 'weatherDate',
      dataIndex: 'weatherDate',
    },
    {
        title: 'weatherTime',
        dataIndex: 'weatherTime',
    },
    {
        title: 'tempture',
        dataIndex: 'tempture',
    },
    {
        title: 'weather',
        dataIndex: 'weather',
    },
  ];
  
  var data = [];
  for (let i = 0; i < 20; i++) {
    data.push({
      key: i,
      name: `Edward King ${i}`,
      age: 32,
      address: `London, Park Lane no. ${i}`,
    });
  }
  
export default class index extends Component {

    state = { mouse: false }
    updateTodo = (id) => {
        const { todo, updateItem } = this.props
        const name = todo.name
        return (event) => {
            const todoObj = { id: id, name: name, done: event.target.checked }
            console.log(todoObj)
            updateItem(todoObj)
        }

    }

    deleteItem = (tid) => {
        console.log(this.props)
        if (window.confirm("confirm to delete!"))
            this.props.deleteItem(tid)
    }

    handleMouseEnter = (flag) => {
        return () => {
            this.setState({ mouse: flag })
        }
    }

    addToItinerary=() => {
        const {weathers} = this.props
        const todoObj = {id:nanoid(),name:weathers[0].city,done:false}
        console.log(todoObj)
        this.props.addItem(todoObj)
        PubSub.publish('allWeathers',{weathers})
        // target.value=''
        
        // this.props.deleteAllChecked()
      }


      state = {
        selectedRowKeys: [], // Check here to configure the default column
        loading: false,
      };
    
      start = () => {
        this.setState({ loading: true });
        // ajax request after empty completing
        setTimeout(() => {
          this.setState({
            selectedRowKeys: [],
            loading: false,
          });
        }, 1000);
      };
    
      onSelectChange = selectedRowKeys => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
      };
    
      render1() {


      }

    render() {
        const { todos, updateItem, deleteItem, isFirst, isLoading, err, weathers, mouse, done } = this.props
        
        data =this.props.weathers
        console.log(data)
        const { loading, selectedRowKeys } = this.state;
        const rowSelection = {
          selectedRowKeys,
          onChange: this.onSelectChange,
        };
        const hasSelected = selectedRowKeys.length > 0;
        return (
            <Fragment>
                <div>
                <div style={{ marginBottom: 10, marginTop: 6, align: "right" }}>
                <Button type="primary" onClick={this.addToItinerary}  loading={loading}>
                    2. Add To Itinerary
                </Button>

                </div>
                <Table columns={columns} dataSource={data} />
            </div>
            {/* <button className="btn btn-danger" onClick={this.addToItinerary}>Add To Itinerary</button> */}
            </Fragment>
        
        )
    }
}
