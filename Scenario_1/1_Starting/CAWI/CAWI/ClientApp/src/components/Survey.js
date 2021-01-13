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
            isToggleOn: true,
            country: null,
            weather: null,
            answers: []
        };

        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        this.populateQuestions();
    }

    locateAnswer(answer, variable) {
        return answer.variable == variable;
    }

    handleChange = (event) => {
        const updatedAnswer = { variable: event.target.name, value: event.target.value };        
        const elementsIndex = this.state.answers.findIndex(element => element.variable == updatedAnswer.variable )        
        let newAnswers = [...this.state.answers];
        
        if(elementsIndex >= 0)
        {
            newAnswers[elementsIndex] = updatedAnswer;            
        }
        else{
            newAnswers.push(updatedAnswer)
        }

        this.setState({answers: newAnswers});        
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

    render() {
        let contents = this.state.loading
            ? <p><em>Loading...</em></p>
            : <Questions
                handleSubmit={this.handleSubmit}
                handleChange={this.handleChange}
                questions={this.state.questions} />;

        let surveyResult = this.state.hasResult
            ? <ValidationResult response={this.state.validationResult} />
            : <p><em>No answer submitted</em></p>;

        return (
            <div>
                <h3 id="tabelLabel" >Survey</h3>
                <p>This is the fascinating Weather Survey! Please respond to the following questions:</p>
                {contents}
                <div>
                    <br/>
                    {surveyResult}
                </div>
            </div>
        );
    }

    async populateQuestions() {
        const response = await fetch('api/survey');
        const data = await response.json();
        this.setState({ questions: data, loading: false });
    }

    handleSubmit = (event) => {
        console.log(this.state.answers);
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(this.state.answers)
        };

        fetch('api/survey', requestOptions)
            .then(response => response.json())
            .then(data => { this.setState({ validationResult: data, hasResult: true }) });

        event.preventDefault();
    }

}