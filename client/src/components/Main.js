import React from 'react'
import { withRouter, Switch, Route } from 'react-router-dom'
import WeatherView from './WeatherView'
import AlarmView from './AlarmView'

import history from './history';

/**
  * Main Component
  *
  * A path based Router component is used to build the page structure and enable
  * navigation between the different UIs:
  * - Mapraw Data Browser
  * - Media Data Browser
  * - Upload Dialog
  */
class Main extends React.Component {

  componentDidMount() {
    this.fetchAlarm();
    this.interval = setInterval(() => {
          console.log("Checking for current alarm...");
          this.fetchAlarm();
        }
        , 10000);
  }

  fetchAlarm = () => {
    fetch(this.state.apiUrl + '/alarm/current')
        .then(result => {
          if (result.status === 200) {
            if (window.location.pathname === '/') {
              this.props.history.push('/alarm');
            }
          } else {
            throw new Error("No alarm incurred...");
          }
        })
        .catch(error => {
          if (window.location.pathname === '/alarm') {
              this.props.history.push('/');
          }
          console.log(error.toString())
        })
  }

  state = {
    weather: null,
    apiUrl: this.props.apiUrl
  }

  render () {
    return(
      <main>
        <Switch>
          <Route exact path='/' render={(props) => <WeatherView apiUrl={this.state.apiUrl} {...props}/>}/>
          <Route exact path='/alarm' render={(props) => <AlarmView apiUrl={this.state.apiUrl} {...props}/>}/>
        </Switch>
      </main>
    )
  }
}

export default withRouter(Main)
