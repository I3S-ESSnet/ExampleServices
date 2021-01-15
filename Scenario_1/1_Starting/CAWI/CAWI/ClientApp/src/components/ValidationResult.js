import React, { Component } from "react";

export class ValidationResult extends Component {
    static displayName = ValidationResult.name;

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <table>
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
        );
    }
}