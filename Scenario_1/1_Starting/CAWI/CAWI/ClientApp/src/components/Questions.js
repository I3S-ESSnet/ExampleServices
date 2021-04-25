import React, { Component } from "react";

export class Questions extends Component {
    static displayName = Questions.name;

    constructor(props) {
        super(props);
        this.state = {
            value: ""
        };

    }

    render() {
        return (
            <div>
                <form onSubmit={this.props.handleSubmit}>
                    <table class="table">
                        <tbody>
                            {this.props.questions.map(question =>
                                <tr key={question.variable}>
                                    <td>{question.caption}</td>
                                    <td><select name={question.variable} onChange={this.props.handleChange}>
                                        {question.options.map(option =>
                                            <option key={option.value} value={option.value}>{option.caption}</option>
                                        )}
                                    </select>
                                    </td>
                                </tr>)}
                                <tr></tr>
                                <tr>
                                    <td></td>
                                    <td height="50"><input type="submit" value="Submit" /></td>
                                </tr>
                        </tbody>
                    </table>                    
                </form>                
            </div>
        );
    }

}