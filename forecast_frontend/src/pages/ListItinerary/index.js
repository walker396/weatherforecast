import React, { Fragment } from 'react'
// import { Layout, Menu, Breadcrumb } from 'antd';
import { Form, Input, Button, Dropdown,Checkbox,Space } from 'antd';
import { DownOutlined } from '@ant-design/icons';
import { Table } from 'antd';
import {nanoid} from 'nanoid'
import axios from 'axios'


var columns = [
    {
        title: 'id',
        dataIndex: 'id',
        key: 'id',

        // render: (text, record) => (

        //     <Space size="middle">
        //       <a >{record.id}</a>
        //     </Space>
        // )
      },
    {
      title: 'name',
      dataIndex: 'name',
      key: 'name',

    },
    {
      title: 'userId',
      dataIndex: 'userId',
      key: 'userId',
    },
    {
      title: 'status',
      dataIndex: 'status',
      key: 'status',
    },
    {
        title: 'createTime',
        dataIndex: 'createTime',
        key: 'createTime',
    }
  ];
  
  var data = [];
  var columns1 = [
    {
        title: 'id',
        dataIndex: 'id',
      },
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
    {
        title: 'orderId',
        dataIndex: 'orderId',
    },
  ];
  var data1 = [];
  // for (let i = 0; i < 3; ++i) {
  //   data1.push({
  //     key: i,
  //     city: 'Screem',
  //     country: 'iOS',
  //     weatherDate: '10.3.4.5654',
  //     upgradeNum: 500,
  //     creator: 'Jack',
  //     createdAt: '2014-12-24 23:12:00',
  //   });
  // }

export default class index extends React.Component{
  state = {//initialization state
    weathers:[], //The initial value of users is an array
    isFirst:true, //Is the page opened for the first time
    isLoading:false,//Identifies whether it is loading
    err:'',//error information related to storage request
    todos:[],
    data : [],
    subTabData : [],
    expandedRowKeys:'',
    pagination: {
        current: 1,
        pageSize: 10,
      },
      loading: false,

  }





    getDetailById = (event)=>{
        console.log(event);
    };
  

  onFinish = (values) => {
    // values.preventDefault()
    console.log('Success:', values);
    const {cityName,country} = values
    //发送请求前通知App更新状态
    this.props.updateAppState({isFirst:false,isLoading:true})

    //发送网络请求
    axios.get(`/api/travelPlan/getAll/${0}/${10}`).then(
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



    componentDidMount(){
        const { data,pagination } = this.state;

        //发送网络请求
        axios.get(`/api/travelPlan/getAll/${pagination.current}/${pagination.pageSize}`).then(
            response => {
                console.log({response})
                let tdata = response.data.list
                let newdata = []
                newdata = tdata.map((item, index) => {
                  return {key:item.id,...item}
                })
                console.log('888888',newdata)
                this.setState({data:newdata})
                console.log(this.state.data)
                //请求成功后通知App更新状态
                // this.props.updateAppState({isLoading:false,weathers:response.data})
            },
            error => {
                console.log('123',error.response.data)
                //请求失败后通知App更新状态
                this.props.updateAppState({isLoading:false,err:error.response.data})
            }
        )
    }

    expandedRowRender = (expend,record) => {
      console.log('11111111111111111111111111',this.state.subTabData)
      
      return <Table bordered={true}  size="small" columns={columns1} dataSource={this.state.subTabData[expend.id]} pagination={false} />;
    };

  onExpand = (expanded, record) => {
    if (expanded === false) {
      // 因为如果不断的添加键值对，会造成数据过于庞大，浪费资源，
      // 因此在每次合并的时候讲相应键值下的数据清空
      console.log("合并！");
      this.setState({
        subTabData: {
          ...this.state.subTabData,
          [record.open_id]: [] ,
        }
      });
    } else {
      console.log("展开！");
      this.setState({expandedKey: [record.id]})
      axios.get(`/api/travelPlan/getDetailById/${record.id}/0/10`).then(
        response => {
            console.log({response})
            let tdata = response.data.list

            let newdata = tdata.map(td =>{
              return {key:td.id,...td}
            })
            console.log('888888',newdata)
            // this.setState({subTabData:newdata})
            // let tem = this.state.subTabData
            console.log('nnnnnnnnn',expanded)
            this.setState({
              subTabData: {
                ...this.state.subTabData,
                [record.id]: newdata ,
              }
            })
          },
        error => {
            console.log('123',error.response.data)
            //请求失败后通知App更新状态
            // this.props.updateAppState({isLoading:false,err:error.response.data})
        }
      )
    }
      console.log('33333333',expanded,record)
  }

  render(){
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


      const { todos, updateItem, deleteItem, isFirst, isLoading, err, weathers, mouse, done } = this.props
      let { data, pagination, loading } = this.state;


    //   data =this.props.weathers
    //   console.log(data)
    //   const { loading, selectedRowKeys } = this.state;
    //   const rowSelection = {
    //     selectedRowKeys,
    //     onChange: this.onSelectChange,
    //   };

      return (

        <Fragment>
              <h3>Itinerary List</h3>
                
                <Table 
                      className="components-table-demo-nested"
                      columns={columns}
                      dataSource={data}
                    expandedRowKeys={this.state.expandedRowKeys}
                    expandable={{expandedRowRender:this.expandedRowRender,}}
                    onExpand={this.onExpand.bind(this)}
                    // scroll={{x:2500,y:'calc(100vh -330px)'}}
                    />
            {/* </div> */}

          
        </Fragment>
      );
  }
  
}