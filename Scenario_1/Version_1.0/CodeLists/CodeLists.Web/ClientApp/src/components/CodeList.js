import React, { React, Component } from 'react'
import Code from '/Code.js'

export class CodeList extends Component {
  static displayName = CodeList.name;

  constructor(props) {
    super(props);

    this.state = {
      codeList: {
        name: "codeList1",
        codes:
          [
            {
              value: "first",
              description: "the first one"
            },
            {
              value: "second",
              description: "the second one"
            }
          ]
      }
    }
  };

  render() {
    return (
      <div>
        <p>{this.state.codeList.name}</p>
        <table className='table table-striped' aria-labelledby="tabelLabel">
          <thead>
            <tr>
              <th>Date</th>
              <th>Temp. (C)</th>
              <th>Temp. (F)</th>
              <th>Summary</th>
            </tr>
          </thead>
          <tbody>
            {this.state.codeList.codes.map(code =>
              <Code code={code} />
            )}
          </tbody>
        </table>
      </div>
    )
  }
}