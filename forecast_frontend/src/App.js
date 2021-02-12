import React from 'react';
import ReactDOM from 'react-dom';
// import './index.css';
import Itinerary from './pages/Itinerary';
import ListItinerary from './pages/ListItinerary';
import {Route,Switch,Redirect,NavLink} from 'react-router-dom'

import { Layout, Menu, Breadcrumb } from 'antd';
import 'antd/dist/antd.css'; 
import {
  DesktopOutlined,
  PieChartOutlined,
  FileOutlined,
  TeamOutlined,
  UserOutlined,
} from '@ant-design/icons';

const { Header, Content, Footer, Sider } = Layout;
const { SubMenu } = Menu;

class App extends React.Component {
  state = {
    collapsed: false,
  };

  onCollapse = collapsed => {
    console.log(collapsed);
    this.setState({ collapsed });
  };

  render() {
    const { collapsed } = this.state;
    return (
      <Layout style={{ minHeight: '100vh' }}>
        <Sider collapsible collapsed={collapsed} onCollapse={this.onCollapse}>
          <div className="logo" />
          <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
            <Menu.Item key="1" icon={<PieChartOutlined />}>
            <NavLink to="/new">New</NavLink>
            </Menu.Item>
            <Menu.Item key="1" icon={<PieChartOutlined />}>
              <NavLink to="/list">List</NavLink>
            </Menu.Item>
            
							
          </Menu>
        </Sider>
        <Layout className="site-layout">
          <Header className="site-layout-background" style={{ padding: 0 }} />
          <Content style={{ margin: '0 16px' }}>
            {/* <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>Itinerary</Breadcrumb.Item>
              <Breadcrumb.Item>New Itinerary</Breadcrumb.Item>
            </Breadcrumb> */}
            <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
              <Switch>  
                <Route path="/new" component={Itinerary}/>
								<Route path="/list" component={ListItinerary}/>
                <Redirect to="/new"/>
							</Switch>
            </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer>
        </Layout>
      </Layout>
    );
  }
}

export default App;