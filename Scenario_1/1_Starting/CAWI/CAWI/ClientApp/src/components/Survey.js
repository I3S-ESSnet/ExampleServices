import React, { Component } from "react";

export class Survey extends Component {
    static displayName = Survey.name;

    constructor(props) {
        super(props);
        this.state = { questions: [], isLoading: true };
    }
    
    componentDidMount() {
        this.populateQuestions();
    }

    static renderQuestionTable(questions) {
        return (

            <table>
                {/* <form></form> */}
                {questions.map(question =>
                    <tr>
                        <td>{question.caption}</td>
                        <td><select>
                            {question.options.map(option =>
                                <option value="{option.value}">{option.caption}</option>
                            )}
                        </select>
                        </td>
                    </tr>)}
            </table>
        );
    }

    render() {
        let contents = this.state.loading
            ? <p><em>Loading...</em></p>
            : Survey.renderQuestionTable(this.state.questions);

        return (
            <div>
                <h3 id="tabelLabel" >Survey</h3>
                <p>This is the fascinating Weather Survey! Please respond to the following questions:</p>
                {contents}
            </div>
        );
    }

    async populateQuestions() {
        const response = await fetch('api/survey');
        const data = await response.json();
        this.setState({ questions: data, loading: false });
    }
}