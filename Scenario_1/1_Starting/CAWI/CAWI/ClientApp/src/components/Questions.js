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
                    <table>
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
                        </tbody>
                    </table>
                    <input type="submit" value="Submit" />
                </form>                
            </div>
        );
    }

}