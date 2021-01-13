import React, { Component } from "react";
import { Questions } from "./Questions";
import { ValidationResult } from "./ValidationResult";

export class Survey extends Component {
    static displayName = Survey.name;

    constructor(props) {
        super(props);
        this.state = {
            questions: [],
            isLoading: true,
            hasResult: false,
            validationResult: [],
            isToggleOn: true
        };
        this.postAnswers = this.postAnswers.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        this.populateQuestions();
    }

    handleChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });

    }

    static renderQuestionTable(questions) {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <table>
                        <tbody>
                            {questions.map(question =>
                                <tr>
                                    <td>{question.caption}</td>
                                    <td><select>
                                        {question.options.map(option =>
                                            <option key={option.value} name={question.caption} value="{option.value}" onChange={this.handleChange}>{option.caption}</option>
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

    handleClick() {
        this.setState(state => ({
            isToggleOn: !state.isToggleOn
        }));
    }

    //extract validation result to its own component
    static renderResultTable(validationResult) {
        return (
            <table>
                <tbody>
                    {validationResult.map(result =>
                        <tr key={result.variable}>
                            <td>{result.variable}</td>
                        </tr>
                    )}
                </tbody>
            </table>
        )
    }

    render() {
        let contents = this.state.loading
            ? <p><em>Loading...</em></p>
            : <Questions
                handleSubmit={this.handleSubmit}
                handleChange={this.handleChange}
                questions={this.state.questions}/>;

        let surveyResult = this.state.hasResult
            ? <ValidationResult response={this.state.validationResult} /> //Survey.renderResultTable(this.state.validationResult)
            : <p><em>No answer submitted</em></p>;

        return (
            <div>
                <h3 id="tabelLabel" >Survey</h3>
                <p>This is the fascinating Weather Survey! Please respond to the following questions:</p>
                {contents}
                <div>
                    {surveyResult}
                    {this.state.Weather}
                </div>
            </div>
        );
    }

    async populateQuestions() {
        const response = await fetch('api/survey');
        const data = await response.json();
        this.setState({ questions: data, loading: false });
    }

    postAnswers(event) {
        //redundant, trouble shooting
        alert('A name was submitted: ' + this.state.value);
        event.preventDefault();

        // const requestOptions = {
        //     method: 'POST',
        //     headers: { 'Content-Type': 'application/json' },
        //     body: JSON.stringify({ title: 'React POST Request Example' })
        // };
        this.setState({ validationResult: "test", hasResult: true });
        // fetch('api/survey', requestOptions)
        //     .then(response => response.json())
        //     .then(data => this.setState({ validationResult: data, hasResult: true }));
        event.preventDefault();
    }

    handleSubmit = (event) => {
        //current state: trouble shooting!

        alert('A form was submitted: ' + this.state);

        // fetch('api/survey', {
        //     method: 'POST',
        //     // We convert the React state to JSON and send it as the POST body
        //     // body: JSON.stringify(this.state)
        //     body: [{variable: "weather", value: "fog"}]
        // }).then(function (response) {
        //     console.log(response)
        //     return response.json();
        // });

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify([{ variable: "weather", value: "fog" }])
        };
        // this.setState({ validationResult: "test", hasResult: true });
        fetch('api/survey', requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
        // .then(data => this.setState({ validationResult: data, hasResult: true }));

        // this.setState({ validationResult: "debug", hasResult: true });
        event.preventDefault();
    }

}