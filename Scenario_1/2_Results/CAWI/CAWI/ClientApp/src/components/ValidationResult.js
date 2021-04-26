import React, { Component } from "react";

export class ValidationResult extends Component {
    static displayName = ValidationResult.name;

    constructor(props) {
        super(props);
    }

    render() {

        return (
            this.props.response.length === 0

                ? <div>No validation error, your weather seems ok. Thank you for participating!</div>
                : <div>
                    <div>Validation error found:</div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Error code</th>
                                <th>Reason</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.props.response.map(result =>
                                <tr key={result.errorNumber}>
                                    <td>{result.errorNumber}</td>
                                    <td>{result.value}</td>
                                    <td>{result.errorDescription}</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
        );
    }
}