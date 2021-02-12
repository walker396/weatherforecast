import React, { Component,Fragment } from 'react'
import {nanoid} from 'nanoid'
import axios from 'axios'
import { Form, Input, Button, Checkbox,Space } from 'antd';


// import './index.css';
export default class index extends Component {
// Set the state in the constructor
    constructor(props){
        super(props)
        this.state ={
        name : '',
        errors:{},//User illegal information prompt
        }

          
    }

    onChange(e){
        //e.target.name represents the Name you currently enter into the Input box, such as email, password
      // e.target.value represents the current input value
      this.setState({
        [e.target.name]: e.target.value
      })
    }


    inputValue =(event) =>{
        const {keyCode,target} = event
        if(keyCode!==13)
            return
        
        if(target.value.trim() ===''){
            alert('Not allow input empty string')
            return;
        }
        // const {addItem} = this.props
        const todoObj = {id:nanoid(),name:target.value,done:false}
        this.props.addItem(todoObj)
        target.value=''
    }

    search = ()=>{
		//获取用户的输入(连续解构赋值+重命名)
		const {cityName:{value:cname},country:{value:couCode}} = this
		//发送请求前通知App更新状态
		this.props.updateAppState({isFirst:false,isLoading:true})
		//发送网络请求
		axios.get(`/api/weather/5daysWeather?city=${cname}&country=${couCode}`).then(
			response => {
                console.log({response})
				//请求成功后通知App更新状态
				this.props.updateAppState({isLoading:false,weathers:response.data})
			},
			error => {
                console.log('123',error.message)
				//请求失败后通知App更新状态
				this.props.updateAppState({isLoading:false,err:error.message})
			}
		)
	};

    onFinish = (values) => {
        // values.preventDefault()
        console.log('Success:', values);
        const {cityName,country} = values
        //发送请求前通知App更新状态
        this.props.updateAppState({isFirst:false,isLoading:true})

        //发送网络请求
		axios.get(`/api/weather/5daysWeather?city=${cityName}&country=${country}`).then(
			response => {
                console.log({response})
				//请求成功后通知App更新状态
				this.props.updateAppState({isLoading:false,weathers:response.data})
			},
			error => {
                console.log('123',error.response.data)
				//请求失败后通知App更新状态
				this.props.updateAppState({isLoading:false,err:error.response.data})
			}
		)
        
      };
    
     onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
      };
    


    render() {
        const {errors} = this.state;
        const layout = { 
            labelCol: {
              span: 50,
            },
            wrapperCol: {
              span: 50,
            },
          };
          const tailLayout = {
            wrapperCol: {
              offset: 8,
              span: 16,
            },
          };
        return (
            <Fragment>
            <div>
            <Form {...layout} layout="inline" name="basic"
                initialValues={{
                    remember: true,
                }}
                
                onFinish={this.onFinish.bind(this)}
                
                onFinishFailed={this.onFinishFailed.bind(this)}
                >
                <Space>
                <Form.Item
                    label="City"
                    name="cityName"
                    rules={[
                    {
                        required: true,
                        message: 'Please input City!',
                    },
                    ]}
                >
                    <Input placeholder="please input the city name!" />
                </Form.Item>
            
                <Form.Item
                    label="Country"
                    name="country"
                    rules={[
                    {
                        required: true,
                        message: 'Please input Country!',
                    },
                    ]}
                >
                    <Input placeholder="please input the country code!" />
                </Form.Item>
            
                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">
                    1. Query
                    </Button>
                </Form.Item>
                </Space>
                </Form>
            </div>
            </Fragment>
        )
    }
}