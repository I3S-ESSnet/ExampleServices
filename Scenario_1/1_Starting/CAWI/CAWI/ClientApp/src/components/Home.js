import React, { Component } from 'react';

export class Home extends Component {
  static displayName = Home.name;

  render () {
    return (
      <div>
        <h1>Welcome to CAWI!</h1>
        <p>This site is made for collecting data for the European weather survey.</p>
        <p></p><a href='./survey'>Visit survey to start register your data</a>
      </div>
    );
  }
}