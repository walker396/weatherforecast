import React, { Component, Fragment } from 'react'
import { List,Form ,Input,message } from 'antd';
import { UserOutlined } from '@ant-design/icons';
// import Item from '../Item'

import Button from 'react-bootstrap/Button'
import PubSub from 'pubsub-js'
import axios from 'axios'
import { SearchOutlined } from '@ant-design/icons';

// import './index.css';


export default class index extends Component {


    state = { mouse: false,userId:'', allWeathers:new Map(),data: []}

    
    // updateTodo = (id) => {
    //     const { todo, updateItem } = this.props
    //     const name = todo.name
    //     return (event) => {
    //         const todoObj = { id: id, name: name, done: event.target.checked }
    //         console.log(todoObj)
    //         updateItem(todoObj)
    //     }

    // }
	componentDidMount(){
        const {allWeathers} = this.state
		this.token = PubSub.subscribe('allWeathers',(_,stateObj)=>{
            console.log('--------',stateObj.weathers[0].user_id)
            let city = stateObj.weathers[0].city;
            // let country = stateObj.weathers[0].country;
            allWeathers.set(city ,stateObj.weathers)
            // let cWeathers = {: stateObj};

			this.setState({userId: stateObj.weathers[0].user_id,allWeathers: allWeathers})
		})
	}

    deleteItem = (tid) => {
        console.log(this.props)
        if (window.confirm("confirm to delete!"))
            this.props.deleteItem(tid)
    }

    handleMouseEnter = (flag) => {
        return () => {
            this.setState({ mouse: flag })
            // console.log(flag)
        }
    }
    onFinish = (values) => {
        // values.preventDefault()
        console.log('Success:', values);
        const {itineraryName} = values

        const {userId,allWeathers} = this.state
        const _this = this
        let planDetails = []
        allWeathers.forEach(function(key){
            // planDetails = 
            planDetails = [...planDetails,...key]
        　　console.log("key",planDetails)  //输出的是map中的value值
        })
            
        var params = {"name":itineraryName,"userId": userId,"status": "1","planDetails": planDetails};
        console.log("======",params)
        axios.post("/api/travelPlan/add",params
        ).then(function (response) {
            console.log(_this)
            _this.setState({ data: [],itineraryName:''})
            message.success('Itinerary save success!',3);
            console.log(response);
        }).catch(function (error) {
            message.error('Save fail!',3);
            console.log(error);
        })
        
      };
    
     onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
      };
    // saveItinerary = ()=>{
    //     const {itName,userId,allWeathers} = this.state
    //     let planDetails = []
    //     allWeathers.forEach(function(key){
    //         // planDetails = 
    //         planDetails = [...planDetails,...key]
    //     　　console.log("key",planDetails)  //输出的是map中的value值
    //     })

    //     //设置全局的
    //         // axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
            
    //     var params = {"name":itName,"userId": userId,"status": "1","planDetails": planDetails};
    //     console.log("======",params)
    //         axios.post("/api/travelPlan/add",params
    //         ).then(function (response) {
                
    //             // console.log(response);
    //         }).catch(function (error) {
    //             console.log(error);
    //         })
	// }
			//保存用户名到状态中
	saveItineraryname = (event)=>{
	    this.setState({itName:event.target.value})
	}


    render() {
        const { todos, updateItem, deleteItem, isFirst, isLoading, err, weathers, done } = this.props
        let { mouse, data } = this.state
        const doneCount = todos.reduce((pre,todo)=> pre + (todo.done ? 1 : 0),0)
        const total = todos.length
        // console.log(deleteItem)
        data = [...todos];

        return (
            <Fragment>
                {/* <input type="text" name="itineraryName" onChange={this.saveItineraryname}  placeholder="please input the itinerary name!"/> */}
                <Form initialValues={{
                    remember: true,
                }}
                
                onFinish={this.onFinish.bind(this)}
                onFinishFailed={this.onFinishFailed.bind(this)}>
                    <Form.Item 
                        name="itineraryName"
                        rules={[
                            {
                                required: true,
                                message: 'Please input itinerary name!',
                            },
                            ]} >
                    <Input size="large"                    
                    placeholder="please input the itinerary name!" 
                    prefix={<UserOutlined />} />
                    </Form.Item>
                
                
                
                <List bordered
                    split="true"
                    itemLayout="horizontal"
                    dataSource={data}
                    renderItem={item => (
                    <List.Item actions={[<a key="list-loadmore-edit" onClick={() => this.deleteItem(item.id)}>Delete</a>]}>
                        <List.Item.Meta
                        
                        title={<a href="https://ant.design">{item.name}</a>}
                        description=""
                        />
                    </List.Item>
                    )}
                />
                <br/>
                <span >total {total}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <Form.Item>
                        <Button type="primary" htmlType="submit" icon={<SearchOutlined/>}>
                            3.Save Itinerary
                        </Button>
                    </Form.Item>
                </Form>
                {/* <button className="btn btn-danger" onClick={this.saveItinerary} align="right">Save Itinerary</button> */}
            </Fragment>
        )
    }
}
