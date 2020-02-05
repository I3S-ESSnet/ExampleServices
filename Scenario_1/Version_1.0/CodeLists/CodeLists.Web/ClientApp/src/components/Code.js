import React, { Component } from 'react'

export class Code extends Component{
    static displayName = Code.name;

    constructor(props) {
        super(props)
        
        this.state = { code = props.code}
    }

    render(){
        return(
            <tr>
                <td>{this.props.value}</td>
                <td></td>
                <td></td>
            </tr>
        )
    }
}