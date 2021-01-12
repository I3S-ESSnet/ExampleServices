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
            <div>
                {/* <form></form> */}
                {questions.map(question =>
                    <td>
                        <h1>{question.caption}</h1>
                        <select>
                            {question.options.map(option =>
                            <option value="{option.value}">{option.caption}</option>    
                            )}                            
                        </select>
                    </td>)}
            </div>
            // <table className='table table-striped' aria-labelledby="tabelLabel">
            //     <thead>
            //         <tr>
            //             <th>Date</th>
            //             <th>Temp. (C)</th>
            //             <th>Temp. (F)</th>
            //             <th>Summary</th>
            //         </tr>
            //     </thead>
            //     <tbody>
            //         {questions.map(forecast =>
            //             <tr key={forecast.date}>
            //                 <td>{forecast.date}</td>
            //                 <td>{forecast.temperatureC}</td>
            //                 <td>{forecast.temperatureF}</td>
            //                 <td>{forecast.summary}</td>
            //             </tr>
            //         )}
            //     </tbody>
            // </table>
        );
    }

    render() {
        let contents = this.state.loading
            ? <p><em>Loading...</em></p>
            : Survey.renderQuestionTable(this.state.questions);

        return (
            <div>
                <h1 id="tabelLabel" >Survey</h1>
                <p>This component demonstrates fetching data from the server.</p>
                {contents}
            </div>
        );
    }

    async populateQuestions() {
        const response = await fetch('survey');
        const data = await response.json();
        this.setState({ questions: data, loading: false });
    }
}