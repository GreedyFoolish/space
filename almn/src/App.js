import React, { Component } from 'react'
import { Layout, Menu } from 'antd';
import {connect} from 'react-redux'
import {
	UserOutlined,
	VideoCameraOutlined,
	UploadOutlined,
} from '@ant-design/icons';
import '../App.css'
import {switchSilderBar} from './reducers/SilderBar'

const { Header, Sider, Content } = Layout;

@connect(
	state=>({
		theme:state.theme.name,
		collapsed:state.SilderBar.SilderCollapsed
	}),
	{switchSilderBar},
	undefined, { pure: false }
)
class App extends Component {
	state = {
		collapsed: false,
	};

	render() {
		return (
			<Layout>
				<Sider theme={this.props.theme} collapsible collapsed={this.props.collapsed} onCollapse={this.props.switchSilderBar}>
					<div className="logo" />
					<Menu theme={this.props.theme} mode="inline" defaultSelectedKeys={['1']}>
						<Menu.Item key="1" icon={<UserOutlined />}>
							nav 1
						</Menu.Item>
						<Menu.Item key="2" icon={<VideoCameraOutlined />}>
							nav 2
						</Menu.Item>
						<Menu.Item key="3" icon={<UploadOutlined />}>
							nav 3
						</Menu.Item>
					</Menu>
				</Sider>
				<Layout className="site-layout">
					<Header className="site-layout-background" style={{ padding: 0 }}>
					</Header>
					<Content
						className="site-layout-background"
						style={{
							margin: '24px 16px',
							padding: 24,
							minHeight: 280,
						}}
					>
						Content
					</Content>
				</Layout>
			</Layout>
		);
	}
}

export default App;