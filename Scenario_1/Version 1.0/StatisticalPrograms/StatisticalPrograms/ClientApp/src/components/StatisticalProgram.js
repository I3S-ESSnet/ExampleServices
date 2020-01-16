import React, { Component } from 'react';

export class StatisticalProgram extends Component {
    static displayName = StatisticalProgram.name;

    constructor(props) {
        super(props);

        this.state = { statisticalPrograms: [], loading: true, counter: 1, itemName: "", itemValue: "" };
        this.delete = this.delete.bind(this);
        this.add = this.add.bind(this);
        this.renderStatisticalProgramTable = this.renderStatisticalProgramTable.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.populateStatisticalPrograms();
    }

    renderStatisticalProgramTable(state) {
        return (
            <div>
                <form>
                    <table className='table table-striped' aria-labelledby="tabelLabel">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Date Initiated</th>
                                <th>Date Ended</th>
                            </tr>
                        </thead>
                        <tbody>
                            {state.statisticalPrograms.map(statisticalProgram =>
                                <tr key={statisticalProgram.id}>
                                    <td>{statisticalProgram.id}</td>
                                    <td>{statisticalProgram.name}</td>
                                    <td>{statisticalProgram.dateInitiated}</td>
                                    <td>{statisticalProgram.dateEnded}</td>
                                    <td><button className="btn btn-primary" onClick={() => this.delete(statisticalProgram.id)}>Delete</button></td>
                                </tr>
                            )}
                            <tr>
                                <td></td>
                                <td><input type="text" name="itemName" value={this.state.itemName} onChange={this.handleInputChange} /></td>
                                <td><input type="text" name="itemValue" value={this.state.itemValue} onChange={this.handleInputChange} /></td>
                                <td></td>
                                <td><button className="btn btn-primary" onClick={() => this.handleSubmit}>Add</button></td>
                            </tr>
                        </tbody>
                    </table>
                    <div>
                        <button className="btn btn-primary" onClick={() => this.add()}>Add</button>
                    </div>
                </form>
            </div>
        );
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    render() {
        let contents = this.state.loading
            ? <p><em>Still loading....</em></p>
            : this.renderStatisticalProgramTable(this.state);

        return (
            <div>
                <h1 id="tabelLabel" >Statistical programs</h1>
                <p>This component demonstrates fetching data from the server.</p>
                {contents}
            </div>
        );
    }

    async add() {
        const response = await fetch('api/statisticalProgram', { method: 'POST' });
    }

    handleSubmit(event) {
        alert('Updating: ' + this.state.itemName + ' => value: ' + this.state.it);
        event.preventDefault();
    }

    async populateStatisticalPrograms() {
        const response = await fetch('api/statisticalProgram');
        const data = await response.json();
        this.setState({ statisticalPrograms: data, loading: false });
    }

    async delete(id) {
        let action = 'api/statisticalProgram/' + id;
        const response = await fetch(action, { method: 'DELETE' });
        if (response.status === '202') {
            //delete ok
        }

        this.setState({
            statisticalPrograms: this.state.statisticalPrograms.filter(el => el.id !== id)
        });
    }
}
